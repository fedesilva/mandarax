/*
 * Copyright 2010 Jens Dietrich Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.mandarax.compiler.impl;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

import org.apache.log4j.Logger;
import org.mandarax.MandaraxException;
import org.mandarax.compiler.*;
import org.mandarax.compiler.Compiler;
import org.mandarax.dsl.*;
import org.mandarax.dsl.parser.ScriptReader;
import org.mandarax.dsl.util.AbstractTypeReasoner;
import org.mandarax.dsl.util.DefaultResolver;
import org.mandarax.dsl.util.Resolver;
import org.mandarax.dsl.util.ResolverException;
import org.mandarax.dsl.util.TypeReasoner;
import org.mandarax.dsl.util.TypeReasoningException;
import org.mvel2.templates.TemplateRuntime;
import static org.mandarax.compiler.impl.Templates.*;
import static org.mandarax.compiler.impl.CompilerUtils.*;


/**
 * Default compiler.
 * @author jens dietrich
 */
public class DefaultCompiler implements Compiler {
	
	public static org.apache.log4j.Logger LOGGER = Logger.getLogger(DefaultCompiler.class);
	
	private Verifier verifier = new VerifyAll();
	private VerificationErrorReporter verificationErrorReporter = new DefaultVerificationErrorReporter();
	private Resolver resolver = new DefaultResolver();
	public static String TYPE_EXTENSION = "Rel";

	@Override
	public void compile(Location target, CompilationMode mode, URL... urls) throws MandaraxException {
		class URLSource implements Source {
			private URL url = null;
			public URLSource(URL url) {
				super();
				this.url = url;
			}
			@Override
			public InputStream openStream() throws IOException {
				return url.openStream();
			}
			@Override
			public String getName() {
				return url==null?"unknown url":url.toString();
			}
		}
		Source[] sources = new Source[urls.length];
		for (int i=0;i<sources.length;i++) sources[i] = new URLSource(urls[i]);
		compile(target,mode,sources);
	}

	@Override
	public void compile(Location target, CompilationMode mode, File... files) throws MandaraxException {
		class FileSource implements Source {
			private File file = null;
			public FileSource(File file) {
				super();
				this.file = file;
			}
			@Override
			public InputStream openStream() throws IOException {
				return new FileInputStream(file);
			}
			@Override
			public String getName() {
				return file==null?"unknown file":file.getAbsolutePath();
			}
		}
		Source[] sources = new Source[files.length];
		for (int i=0;i<sources.length;i++) sources[i] = new FileSource(files[i]);
		compile(target,mode,sources);

	}

	@Override
	public void compile(Location target, CompilationMode mode, Source... sources) throws MandaraxException {
		// parse
		List<CompilationUnit> cus = new ArrayList<CompilationUnit>();
		ScriptReader reader = new ScriptReader();
		for (Source s:sources) {
			InputStream in;
			try {
				in = s.openStream();
				CompilationUnit cu = reader.readCompilationUnit(in);
				cu.setSource(s.getName());
				cus.add(cu);
				in.close();
			} catch (IOException x) {
				throw new CompilerException("Exception reading file from source " + s,x);
			}
		}
		
		// verify
		verifier.verify(cus,verificationErrorReporter);
		
		
		// delegate
		if (mode==CompilationMode.INTERFACES_ONLY) {
			compileToInterfaces(target,cus);
		}
		else if (mode==CompilationMode.CLASSES_ONLY){
			compileToClasses(target,cus);
		}
			
		else {
			throw new CompilerException("Unsupported compilation mode " + mode.name());
		}
	}
	
	private void compileToClasses(Location target, List<CompilationUnit> cus) throws MandaraxException {	
		// add additional rules for inheritance
		resolveSuperRelationships(cus);
		// associate function invocation with referenced relationships
		resolveFunctionRefs(cus);
		// rewrite NAF (precond: we must know which function invocation ref to relationships)
		normalizeNAF(cus);
		
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				try {
					LOGGER.info("Generating classes for " + rel + " defined in " + cu);
					LOGGER.info("Output to: " + target);
					createRelationshipQueryImplementation(target,cus,cu,rel);
				} catch (Exception e) {
					LOGGER.error(e);
					throw new CompilerException(cu,rel.getPosition(),"Cannot generate query interface for relationship " + rel.getName(),e);
				}
				
			}
		}
	}

	private void normalizeNAF(List<CompilationUnit> cus) {
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				for (int i=0;i<rel.getRules().size();i++) {
					Rule rule = rel.getRules().get(i);
					Rule normalised = rel.getRules().get(i).normaliseNAF();
					if (!rule.equals(normalised)) {
						LOGGER.debug("Normalised rule containing NAF from " + rule + " to " + normalised);
						rel.getRules().set(i,normalised);
					}
				}
			}
		}
		
	}

	// create additional rules representing relationship inheritance
	private void resolveSuperRelationships(List<CompilationUnit> cus) {
		
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				Set<String> ids = new HashSet<String>();
				for (RelationshipDefinition subRel:findSubRels(cus,rel)) {
					String id = "_"+subRel.getName()+"_extends_"+rel.getName();
					Position pos = Position.NO_POSITION; // means not defined in script
					Context context = rel.getContext();
					if (ids.add(id)) {
						FunctionInvocation head = new FunctionInvocation(pos,context,rel.getName(),createVariables(pos,context,rel.getSlotDeclarations().size()));
						FunctionInvocation body = new FunctionInvocation(pos,context,subRel.getName(),createVariables(pos,context,rel.getSlotDeclarations().size()));
						Rule rule = new Rule(pos,context,id,body,head);
						rel.addRule(rule);
						LOGGER.info("Adding inheritance rule to relationship " + rel.getName() + ": " + rule);
					}
					else {
						LOGGER.warn("Attempt to add more than one rule for sub relationship " + subRel.getName() + " of " + rel.getName());
					}
				}
			}
		}
	}
	
	private List<Expression> createVariables(Position pos,Context context,int n) {
		List<Expression> terms = new ArrayList<Expression>();
		for (int i=0;i<n;i++) {
			terms.add(new Variable(pos,context,"_x"+i));
		}
		return terms;
	}

	private List<RelationshipDefinition> findSubRels(List<CompilationUnit> cus, RelationshipDefinition superRel) {
		List<RelationshipDefinition> subTypes = new ArrayList<RelationshipDefinition>();
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				if (rel.getSuperTypes().contains(superRel.getName()) && rel.getSlotDeclarations().size()==superRel.getSlotDeclarations().size()) {
					LOGGER.debug("Found sub relationships " + rel.getName() + " extending " + superRel.getName());
					subTypes.add(rel);
				}
			}	
		}
		return subTypes;
	}

	private void compileToInterfaces(Location target, List<CompilationUnit> cus) throws MandaraxException {	
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				try {
					LOGGER.info("Generating interfaces for " + rel + " defined in " + cu);
					LOGGER.info("Output to: " + target);
					createRelationshipType (target,cu,rel);
				} catch (Exception e) {
					LOGGER.error(e);
					throw new CompilerException(cu,rel.getPosition(),"Cannot generate class to represent relationship " + rel.getName(),e);
				}
				
				try {
					createRelationshipQueryInterface (target,cu,rel);
				} catch (Exception e) {
					throw new CompilerException(cu,rel.getPosition(),"Cannot generate query interface for relationship " + rel.getName(),e);
				}
				
			}
		}
	}
	
	
	
	
	// keep public for unit testing
	public void createRelationshipType (Location target,CompilationUnit cu,RelationshipDefinition rel) throws Exception {
		Map<String,Object> bindings = createParamBindings(cu);
		bindings.put("rel",rel);
		String generated = (String) TemplateRuntime.execute(getTemplate(RELATIONSHIP_TYPE), bindings,Templates.registry);	
		printGeneratedCode(cu,target,rel.getName()+TYPE_EXTENSION,generated);
	}
	
	// keep public for unit testing
	public void createRelationshipQueryInterface (Location target,CompilationUnit cu,RelationshipDefinition rel) throws Exception {
		Map<String,Object> bindings = createParamBindings(cu);
		bindings.put("rel",rel);
		String packageName = cu.getContext().getPackageDeclaration().getName();
		bindings.put("packageName",packageName);
		String generated = (String) TemplateRuntime.execute(getTemplate(RELATIONSHIP_QUERY_INTERFACE), bindings,Templates.registry);
		printGeneratedCode(cu,target,rel.getName()+TYPE_EXTENSION+"Instances",generated);
	}
	
	// keep public for unit testing
	public void createRelationshipQueryImplementation(Location target,List<CompilationUnit> cus,CompilationUnit cu, RelationshipDefinition rel) throws Exception {
		String className = rel.getName()+"Instances";
		String packageName = cu.getContext().getPackageDeclaration().getName(); //+".v"+getTimestampAsVersion()
		
		for (Rule rule:rel.getRules()) {
			assignTypes (cus,cu,rel,rule);
		}
		
		Map<String,Object> bindings = createParamBindings(cu);
		bindings.put("rel",rel);
		bindings.put("className",className);
		bindings.put("packageName",packageName);
		bindings.put("ruleIndices",getIndices(rel.getRules()));
		bindings.put("resolver",resolver);
		
		String generated = (String) TemplateRuntime.execute(getTemplate(RELATIONSHIP_QUERY_IMPLEMENTATION), bindings,Templates.registry);
		printGeneratedCode(cu,target,rel.getName()+TYPE_EXTENSION+"Instances",generated);
	}
	

	private void printGeneratedCode(CompilationUnit cu,Location target,String localClassName,String code) throws Exception {
		Writer out = target.getSrcOut(cu.getContext().getPackageDeclaration().getName(),localClassName);
		out.write(code);
		out.close();
	}
	
	// bindings to be used to instantiate the templates
	private Map<String,Object> createParamBindings(CompilationUnit cu) {
		Map<String,Object> bindings = new HashMap<String,Object>();
		bindings.put("context",cu.getContext());
		bindings.put("timestamp",getTimestamp());	
		bindings.put("ext",TYPE_EXTENSION);
		return bindings;
	}
	
	// associates expressions with type information
	// keep public for unit testing
	public void assignTypes (Collection<CompilationUnit> cus,CompilationUnit cu, RelationshipDefinition rel,Rule rule) throws CompilerException, ResolverException {
		LOGGER.debug("Assigning types to terms in " + rule);
		
		// types for all variables and declared (imported) objects will be stored here
		final Map<String,Class> objTypeMap = new HashMap<String,Class>();
		final Map<Expression,Class> varTypeMap = new HashMap<Expression,Class>();
		
		// assign types for imported objects
		for (ObjectDeclaration objDecl:cu.getObjectDeclarations()) {
			String name = objDecl.getName();
			Class type = resolver.getType(cu.getContext(),objDecl.getType());
			objTypeMap.put(name,type);
			LOGGER.debug("Adding type info from object declaration to type map: " + name + " -> " + type);
		}
		
		// collect relationships
		final Collection<RelationshipDefinition> rels = new HashSet<RelationshipDefinition>();
		for (CompilationUnit cun:cus) {
			rels.addAll(cun.getRelationshipDefinitions());
		}
		
		// head
		FunctionInvocation head = rule.getHead();
		for (int i=0;i<head.getParameters().size();i++) {
			Expression term = head.getParameters().get(i);

			if (term instanceof Variable) {
				// try to use term from obj declaration
				Class type = objTypeMap.get(((Variable) term).getName());
				// else get type from slot definition
				Class type2 = resolver.getType(cu.getContext(),rel.getSlotDeclarations().get(i).getType());
				if (type==null) {
					varTypeMap.put((Variable)term,type2);
					LOGGER.debug("Adding type info from rule head to type map: " + term + " -> " + type2);
				}
				else {
					checkTypeConsistency(cu,rule,type,type2);
					varTypeMap.put((Variable)term,type);
					LOGGER.debug("Adding type info from rule head to type map: " + term + " -> " + type);
				}
			}
			else {
				// complex term in head
				Class type2 = resolver.getType(cu.getContext(),rel.getSlotDeclarations().get(i).getType());
				varTypeMap.put(term,type2);
				LOGGER.debug("Adding type info from rule head to type map: " + term + " -> " + type2);
			}
		}
		// body
		for (Expression expression:rule.getBody()) {
			RelationshipDefinition rel2 = null;
			for (Variable var:expression.getVariables()) {
				if (!varTypeMap.containsKey(var)) {
					int pos = -1;
					if (expression instanceof FunctionInvocation) {
						FunctionInvocation fi = (FunctionInvocation)expression;
						rel2 = this.findRelationshipDefinition(cus,fi.getFunction(),fi.getParameters().size());
						for (int i=0;i<fi.getParameters().size();i++) {
							if (fi.getParameters().get(i).equals(var)) pos=i;
						}
						
					}
					if (pos==-1) {
						// do not throw an error here - we now also support complex expressions having their types assigned by slots in rels
						// throw new CompilerException(cu,var.getPosition(),"The variable " + var + " introduced in " + rule + " must be a top level term in the relationship " + rel2.getName());
					}
					else {
						Class type = objTypeMap.get((var).getName());
						// else get type from slot definition
						
						Class type2 = resolver.getType(rel2.getContext(),rel2.getSlotDeclarations().get(pos).getType());
						if (type==null) {
							varTypeMap.put(var,type2);
							LOGGER.debug("Adding type info from prerequisite " + expression + " to type map: " + var + " -> " + type2);
						}
						else {
							checkTypeConsistency(cu,rule,type,type2);
							varTypeMap.put(var,type);
							LOGGER.debug("Adding type info from prerequisite " + expression + " to type map: " + var + " -> " + type);
						}
					}
				}
			}
			
			// add types to complex expressions having their types assigned by slots in rels
			if (expression instanceof FunctionInvocation) {
				FunctionInvocation fi = (FunctionInvocation)expression;
				rel2 = this.findRelationshipDefinition(cus,fi.getFunction(),fi.getParameters().size());
				for (int i=0;i<fi.getParameters().size();i++) {
					Expression part = fi.getParameters().get(i);
					if (!(part instanceof Variable)) {
						Class type = resolver.getType(rel2.getContext(),rel2.getSlotDeclarations().get(i).getType());
						varTypeMap.put(part,type);
						LOGGER.debug("Adding type info from prerequisite " + expression + " to type map: " + part + " -> " + type);
					}
				}
			}
			
		}
		
		// build type reasoner
		// TODO: type reasoner should also override method to associate function invocations with types
		// sometimes types for complex expressions are known (e.g. from referenced slots in predicates)
		final TypeReasoner typeReasoner = new AbstractTypeReasoner() {
			@Override
			protected Class doGetType(Variable expression, Resolver resolver,Collection<RelationshipDefinition> rels) throws TypeReasoningException {
				return varTypeMap.get(expression);
			}
			@Override
			public Class getType(Expression expression, Resolver resolver,Collection<RelationshipDefinition> rels) throws TypeReasoningException {
				Class type = varTypeMap.get(expression);
				if (type!=null) return type; // we can associate complex terms directly with types through slots 
				else return super.getType(expression,resolver, rels);
			}
		};
		
		final List<TypeReasoningException> typeReasonerExceptions = new ArrayList<TypeReasoningException>();
		
		ASTVisitor visitor = new ASTVisitor() {
			private boolean setType(Expression x)  {
				Class c;
				try {
					c = typeReasoner.getType(x, resolver,rels);
					x.setType(c);
				} catch (TypeReasoningException e) {
					typeReasonerExceptions.add(e);
				}
				return true;
			}

			@Override public boolean visit(Rule x) {return true;}
			@Override public boolean visit(Annotation x) {return true;}
			@Override public boolean visit(FunctionDeclaration x) {return true;}
			@Override public boolean visit(ImportDeclaration x) {return true;}
			@Override public boolean visit(ObjectDeclaration x) {return true;}
			@Override public boolean visit(PackageDeclaration x) {return true;}
			@Override public boolean visit(VariableDeclaration x) {return true;}
			@Override public boolean visit(CompilationUnit x) {return true;}
			@Override public boolean visit(RelationshipDefinition x) {return true;}
			@Override public boolean visit(BinaryExpression x) {return setType(x);}
			@Override public boolean visit(BooleanLiteral x) {return setType(x);}
			@Override public boolean visit(CastExpression x) {return setType(x);}
			@Override public boolean visit(ConditionalExpression x) {return setType(x);}
			@Override public boolean visit(InstanceOfExpression x) {return setType(x);}
			@Override public boolean visit(IntLiteral x) {return setType(x);}
			@Override public boolean visit(DoubleLiteral x) {return setType(x);}
			@Override public boolean visit(MemberAccess x) {return setType(x);}
			@Override public boolean visit(StringLiteral x) {return setType(x);}
			@Override public boolean visit(UnaryExpression x) {return setType(x);}
			@Override public boolean visit(Variable x) {return setType(x);}
			@Override public boolean visit(FunctionInvocation x) {return setType(x);}
			@Override public boolean visit(ConstructorInvocation x) {return setType(x);}
			@Override public boolean visit(NullValue x) {return setType(x);}
			@Override public void endVisit(CompilationUnit x) {}
			@Override public void endVisit(RelationshipDefinition x) {}
			@Override public void endVisit(Rule x) {}
			@Override public void endVisit(Annotation x) {}
			@Override public void endVisit(FunctionDeclaration x) { }
			@Override public void endVisit(ImportDeclaration x) { }
			@Override public void endVisit(ObjectDeclaration x) { }
			@Override public void endVisit(PackageDeclaration x) { }
			@Override public void endVisit(VariableDeclaration x) { }
			@Override public void endVisit(BinaryExpression x) { }
			@Override public void endVisit(BooleanLiteral x) { }
			@Override public void endVisit(CastExpression x) { }
			@Override public void endVisit(ConditionalExpression x) { }
			@Override public void endVisit(InstanceOfExpression x) { }
			@Override public void endVisit(IntLiteral x) { }
			@Override public void endVisit(DoubleLiteral x) { }
			@Override public void endVisit(MemberAccess x) { }
			@Override public void endVisit(StringLiteral x) { }
			@Override public void endVisit(UnaryExpression x) { }
			@Override public void endVisit(Variable x) { }
			@Override public void endVisit(FunctionInvocation x) { }
			@Override public void endVisit(ConstructorInvocation x) { }
			@Override public void endVisit(NullValue x) { }
			
		};
		rule.accept(visitor);
		
		if (typeReasonerExceptions.size()>0) throw new CompilerException("Type reasoner exception",typeReasonerExceptions.get(0));
		
	}
	
	private void checkTypeConsistency(CompilationUnit cu, Rule rule, Class subtype, Class supertype) throws CompilerException {
		if (!supertype.isAssignableFrom(subtype)) throw new CompilerException(cu,rule.getPosition(),"Error compiling rule: " + subtype + " is not a subtype of " + supertype);
	}

	private RelationshipDefinition findRelationshipDefinition (Collection<CompilationUnit> cus,String name,int slotCount) {
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				if (rel.getName().equals(name) && rel.getSlotDeclarations().size()==slotCount) {
					return rel;
				}
			}
		}
		return null;
	}
	// cross ref functions in function invocations with function declarations or import statements
	// keep public for unit testing
	public void resolveFunctionRefs (Collection<CompilationUnit> cus) throws CompilerException {
		// collect functions defined in queries
		final Collection<RelationshipDefinition> declaredRels = new HashSet<RelationshipDefinition>();
		class DeclaredFunctionCollector extends AbstractASTVisitor {
			@Override
			public boolean visit(RelationshipDefinition x) {
				declaredRels.add(x);
				return super.visit(x);
			}
		};
		for (CompilationUnit cu:cus) {
			cu.accept(new DeclaredFunctionCollector());
		}
		
		// collect function references
		// careful with duplicates here: we could get the same expressions in multiple rules
		final Collection<FunctionInvocation> referencedFunctions = new ArrayList<FunctionInvocation>(); 
		// do not count rule heads!
		class ReferencedFunctionCollector extends AbstractASTVisitor {
			private Rule context = null;
			@Override
			public boolean visit(FunctionInvocation x) {
				if (context==null || context.getHead()!=x) {
					referencedFunctions.add(x);
				}
				return super.visit(x);
			}
			@Override
			public boolean visit(Rule r) {
				context = r;
				return super.visit(r);
			}
			@Override
			public void endVisit(Rule r) {
				context = null;
				super.endVisit(r);
			}
		};
		for (CompilationUnit cu:cus) {
			cu.accept(new ReferencedFunctionCollector());
		}
		
		// cross ref
		for (FunctionInvocation ref:referencedFunctions) {
			for (RelationshipDefinition rel:declaredRels) {
				// TODO type check here? currently we only look for name and param number
				if (ref.getFunction().equals(rel.getName()) && ref.getParameters().size()==rel.getSlotDeclarations().size()) {
					LOGGER.debug("Mapping term " + ref + " defined at " + ref.getPosition() + " to relationship " + rel);
					ref.setRelationship(rel);
					break;
				}
			}
			// check whether the function has been imported
			if (ref.getRelationship()==null) {
				Method method = resolver.getFunction(ref.getContext(),ref.getFunction(),ref.getParameters().size());
				if (method!=null) {
					ref.setReferencedMethod(method);
					// TODO logging
				}
				else {
					throw new CompilerException("Cannot resolve function invocation " + ref);
				}
			}
			
		}
	}

}
