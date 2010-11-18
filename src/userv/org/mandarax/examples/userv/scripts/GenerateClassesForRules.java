/*
 * Copyright 2010 Jens Dietrich Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.gnu.org/licenses/agpl.html Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.mandarax.examples.userv.scripts;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.Logger;
import org.mandarax.compiler.CompilationMode;
import org.mandarax.compiler.Compiler;
import org.mandarax.compiler.CompilerException;
import org.mandarax.compiler.Location;
import org.mandarax.compiler.impl.DefaultCompiler;
import test.org.mandarax.compiler.GenerateCodeForTesting;
/**
 * Utility to generate code for the userv rules.
 * @author jens dietrich
 */
public class GenerateClassesForRules {
	
	public static final Logger LOGGER = Logger.getLogger(GenerateCodeForTesting.class);

	public static void main(String[] args) throws Exception {
		File[] files = new File("src/userv/org/mandarax/examples/userv/rules").listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getAbsolutePath().endsWith(".rel");
			}});
		
		compile(files);
		
	}

	private static void compile(File[] files) throws Exception {
		Compiler compiler = new DefaultCompiler();
		Location location = new Location() {
			String file = null;
			@Override
			public Writer getSrcOut(String p,String c) throws CompilerException {
				String folder = "src/userv/" + p.replace('.','/');
				File dir = new File(folder);
				if (!dir.exists()) dir.mkdirs();
				file = folder + '/' + c + ".java";
				try {
					return new FileWriter(new File(file));
				} catch (IOException e) {
					throw new CompilerException(e);
				}
			}
			@Override
			public String toString() {
				return file;
			}
		};
		compiler.compile(location,CompilationMode.INTERFACES_ONLY,files);
		compiler.compile(location,CompilationMode.CLASSES_ONLY,files);
		
		LOGGER.info("Compiled rules from " + files);
	}
}
