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
import java.net.URL;

import org.mandarax.compiler.CompilationMode;
import org.mandarax.compiler.Compiler;
import org.mandarax.compiler.CompilerException;
import org.mandarax.compiler.Location;
import org.mandarax.compiler.Source;
import org.mandarax.dsl.parser.ScriptException;

/**
 * Default compiler.
 * @author jens dietrich
 */
public class DefaultCompiler implements Compiler {

	@Override
	public void compile(Location target, CompilationMode mode, URL... sources) throws CompilerException, ScriptException {
		// TODO Auto-generated method stub

	}

	@Override
	public void compile(Location target, CompilationMode mode, File... sources) throws CompilerException, ScriptException {
		// TODO Auto-generated method stub

	}

	@Override
	public void compile(Location target, CompilationMode mode, Source... sources) throws CompilerException, ScriptException {
		// TODO Auto-generated method stub

	}

}
