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

package org.mandarax.compiler;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.mandarax.MandaraxException;
import org.mandarax.dsl.parser.ScriptException;
/**
 * Compiler interface.
 * @author jens dietrich
 */
public interface Compiler {
	
	
	/**
	 * Compile the sources.
	 * @param target the location describing where the source code and byte code generated will be stored
	 * @param mode the compilation mode
	 * @param sources the sources where the rel resources can be found
	 * @throws CompilerException
	 * @throws ScriptException
	 */
	public void compile(Location target,CompilationMode mode,URL... sources) throws MandaraxException  ; 
	
	/**
	 * Compile the sources.
	 * @param target the location describing where the source code and byte code generated will be stored
	 * @param mode the compilation mode
	 * @param sources the files where the rel resources can be found, this should be either *.rel files or directories, directories will be searched recursively 
	 * @throws CompilerException
	 * @throws ScriptException
	 */
	public void compile(Location target,CompilationMode mode,File... sources) throws MandaraxException  ; 
	/**
	 * Compile the sources.
	 * @param target the location describing where the source code and byte code generated will be stored
	 * @param mode the compilation mode
	 * @param sources the sources where the rel resources can be found
	 * @throws CompilerException
	 * @throws ScriptException
	 */
	public void compile(Location target,CompilationMode mode,Source... sources) throws MandaraxException  ; 
	
}
