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
import java.net.URL;
import org.mandarax.MandaraxException;
import org.mandarax.compiler.CompilationMode;
import org.mandarax.compiler.Compiler;
import org.mandarax.compiler.CompilerException;
import org.mandarax.compiler.Location;
import org.mandarax.compiler.Source;

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
		
		
		// verify 
		
		// delegate
		if (mode==CompilationMode.INTERFACES_ONLY) {
			compileInterfaces(target,sources);
		}
		else throw new CompilerException("Unsupported compilation mode " + mode.name());
	}

	private void compileInterfaces(Location target, Source[] sources) throws MandaraxException {
//		for (Predicate p:getPublicPredicates()) {
//			createReturnType(p);
//		}
		
	}

}
