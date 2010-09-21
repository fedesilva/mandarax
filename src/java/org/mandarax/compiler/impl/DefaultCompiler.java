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
import java.net.URL;
import java.util.*;
import org.mandarax.MandaraxException;
import org.mandarax.compiler.*;
import org.mandarax.compiler.Compiler;
import org.mandarax.dsl.*;
import org.mandarax.dsl.parser.ScriptReader;
import org.mandarax.dsl.util.DefaultResolver;
import org.mandarax.dsl.util.Resolver;
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
		Verifier verifier = new VerifyAll();
		for (Source s:sources) {
			InputStream in;
			try {
				in = s.openStream();
				CompilationUnit cu = reader.readCompilationUnit(in);
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
		else throw new CompilerException("Unsupported compilation mode " + mode.name());
	}
	
	private void compileToClasses(Location target, List<CompilationUnit> cus) throws MandaraxException {	
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				try {
					createRelationshipQueryImplementation(target,cus,cu,rel);
				} catch (Exception e) {
					throw new CompilerException("Cannot generate query interface for relationship " + rel.getName(),e);
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
					throw new CompilerException("Cannot generate class to represent relationship " + rel.getName(),e);
				}
				
				try {
					createRelationshipQueryInterface (target,cu,rel);
				} catch (Exception e) {
					throw new CompilerException("Cannot generate query interface for relationship " + rel.getName(),e);
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
		String className = rel.getName()+"InstancesImpl";
		String packageName = cu.getContext().getPackageDeclaration().getName()+".v"+getTimestampAsVersion();
		
		Map<String,Object> bindings = createParamBindings(cu);
		bindings.put("rel",rel);
		bindings.put("className",className);
		bindings.put("packageName",packageName);
		bindings.put("ruleIndices",getIndices(rel.getRules()));
		
		String generated = (String) TemplateRuntime.execute(getTemplate(RELATIONSHIP_QUERY_IMPLEMENTATION), bindings,Templates.registry);
		printGeneratedCode(cu,target,rel.getName()+"Instances",generated);
	}
	

	private void printGeneratedCode(CompilationUnit cu,Location target,String localClassName,String code) throws Exception {
		Writer out = target.getSrcOut(cu.getContext().getPackageDeclaration().getName() + '.' + localClassName);
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
	public void assignTypes (CompilationUnit cu, RelationshipDefinition rel,Rule rule) throws CompilerException {
		
	}
	

}
