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
	
	private Verifier verifier = new VerifyAll();
	private VerificationErrorReporter verificationErrorReporter = new DefaultVerificationErrorReporter();
	private Resolver resolver = new DefaultResolver();

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
		
		resolveFunctionRefs(cus);
		
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				try {
					createRelationshipQueryImplementation(target,cus,cu,rel);
				} catch (Exception e) {
					throw new CompilerException(cu,rel.getPosition(),"Cannot generate query interface for relationship " + rel.getName(),e);
				}
				
			}
		}
	}
	

	private void compileToInterfaces(Location target, List<CompilationUnit> cus) throws MandaraxException {	
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				try {
					createRelationshipType (target,cu,rel);
				} catch (Exception e) {
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
		printGeneratedCode(cu,target,rel.getName(),generated);
	}
	
	// keep public for unit testing
	public void createRelationshipQueryInterface (Location target,CompilationUnit cu,RelationshipDefinition rel) throws Exception {
		Map<String,Object> bindings = createParamBindings(cu);
		bindings.put("rel",rel);
		String generated = (String) TemplateRuntime.execute(getTemplate(RELATIONSHIP_QUERY_INTERFACE), bindings,Templates.registry);
		printGeneratedCode(cu,target,rel.getName()+"Instances",generated);
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
		
		String generated = (String) TemplateRuntime.execute(getTemplate(RELATIONSHIP_QUERY_IMPLEMENTATION), bindings,Templates.registry);
		printGeneratedCode(cu,target,rel.getName()+"Instances",generated);
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
		return bindings;
	}
	
	// associates expressions with type information
	// keep public for unit testing
	public void assignTypes (Collection<CompilationUnit> cus,CompilationUnit cu, RelationshipDefinition rel,Rule rule) throws CompilerException, ResolverException {
		// types for all variables and declared (imported) objects will be stored here
		final Map<String,Class> objTypeMap = new HashMap<String,Class>();
		final Map<Variable,Class> varTypeMap = new HashMap<Variable,Class>();
		
		// assign types for imported objects
		for (ObjectDeclaration objDecl:cu.getObjectDeclarations()) {
			objTypeMap.put(objDecl.getName(),resolver.getType(cu.getContext(),objDecl.getType()));
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
			assert(term.isFlat());
			if (term instanceof Variable) {
				// try to use term from obj declaration
				Class type = objTypeMap.get(((Variable) term).getName());
				// else get type from slot definition
				Class type2 = resolver.getType(cu.getContext(),rel.getSlotDeclarations().get(i).getType());
				if (type==null) {
					varTypeMap.put((Variable)term,type2);
				}
				else {
					checkTypeConsistency(cu,rule,type,type2);
					varTypeMap.put((Variable)term,type);
				}
			}
		}
		// body
		for (Expression expression:rule.getBody()) {
			
			for (Variable var:expression.getVariables()) {
				if (!varTypeMap.containsKey(var)) {
					RelationshipDefinition rel2 = null;
					int pos = -1;
					if (expression instanceof FunctionInvocation) {
						FunctionInvocation fi = (FunctionInvocation)expression;
						rel2 = this.findRelationshipDefinition(cus,fi.getFunction(),fi.getParameters().size());
						for (int i=0;i<fi.getParameters().size();i++) {
							if (fi.getParameters().get(i)==var) pos=i;
						}
						if (pos==-1) throw new CompilerException(cu,var.getPosition(),"The variable " + var + " introduced in " + rule + " must be a top level term in the relationship " + rel2.getName());
					}
					Class type = objTypeMap.get((var).getName());
					// else get type from slot definition
					
					Class type2 = resolver.getType(rel2.getContext(),rel2.getSlotDeclarations().get(pos).getType());
					if (type==null) {
						varTypeMap.put(var,type2);
					}
					else {
						checkTypeConsistency(cu,rule,type,type2);
						varTypeMap.put(var,type);
					}
				}
			}
		}
		
		// build type reasoner
		final TypeReasoner typeReasoner = new AbstractTypeReasoner() {
			@Override
			public Class getVarType(Variable expression, Resolver resolver) throws TypeReasoningException {
				return varTypeMap.get(expression);
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
		final Collection<FunctionInvocation> referencedFunctions = new HashSet<FunctionInvocation>();
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
					ref.setRelationship(rel);
					// TODO logging
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
