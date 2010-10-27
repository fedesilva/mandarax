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

import org.mandarax.MandaraxException;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.Position;

/**
 * Class used to report compiler exceptions
 * @author jens dietrich
 */

@SuppressWarnings("serial")
public class CompilerException extends MandaraxException {
	

	public CompilerException() {}

	public CompilerException(CompilationUnit cu,Position pos,String message) {
		super(buildMessage(cu,pos,message));
	}

	public CompilerException(Throwable cause) {
		super(cause);
	}

	public CompilerException(CompilationUnit cu,Position pos,String message, Throwable cause) {
		super(buildMessage(cu,pos,message), cause);
	}
	
	public CompilerException(String message) {
		super(message);
	}
	
	public CompilerException(String message, Throwable cause) {
		super(message, cause);
	}

	private static String buildMessage(CompilationUnit cu,Position pos,String m) {
		return "Compiler error in " + cu + " at position " + pos + ": " + m;
	}
}
