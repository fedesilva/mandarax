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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mandarax.MandaraxException;
import org.mandarax.compiler.CompilationMode;
import org.mandarax.compiler.Compiler;
import org.mandarax.compiler.CompilerException;
import org.mandarax.compiler.Location;
import org.mandarax.compiler.Source;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.Verifier;
import org.mandarax.dsl.VerifyAll;
import org.mandarax.dsl.parser.ScriptReader;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;

import static org.mandarax.compiler.impl.Templates.*;


/**
 * Default compiler.
 * @author jens dietrich
 */
public class DefaultCompiler implements Compiler {
	
	

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
				// TODO verify 
				cus.add(cu);
				in.close();
			} catch (IOException x) {
				throw new CompilerException("Exception reading file from source " + s,x);
			}
		}
		
		
		// delegate
		if (mode==CompilationMode.INTERFACES_ONLY) {
			compileInterfaces(target,cus);
		}
		else throw new CompilerException("Unsupported compilation mode " + mode.name());
	}

	public void compileInterfaces(Location target, List<CompilationUnit> cus) throws MandaraxException {
		
		for (CompilationUnit cu:cus) {
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				try {
					createRelationshipType (target,cu,rel);
				} catch (Exception e) {
					throw new CompilerException("Cannot generate class to represent relationship",e);
				}
			}
		}
	}
	
	
	public void createRelationshipType (Location target,CompilationUnit cu,RelationshipDefinition rel) throws Exception {
		CompiledTemplate template = getTemplate("RelationshipType");
		Map<String,Object> bindings = new HashMap<String,Object>();
		
		bindings.put("context",cu.getContext());
		bindings.put("rel",rel);
		bindings.put("slots",rel.getSlotDeclarations());
		String generated = (String) TemplateRuntime.execute(template, bindings);
		
		Writer out = target.getSrcOut(cu.getContext().getPackageDeclaration().getName() + '.' + rel.getName());
		out.write(generated);
		out.close();
	}

}
