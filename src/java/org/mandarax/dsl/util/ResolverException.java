/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.gnu.org/licenses/agpl.html 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package org.mandarax.dsl.util;

import org.mandarax.compiler.CompilerException;

/**
 * Exception thrown when resolving types.
 * @author jens dietrich
 */

public class ResolverException extends CompilerException {

	public ResolverException() {
	}

	public ResolverException(String message) {
		super(message);
	}

	public ResolverException(Throwable cause) {
		super(cause);
	}

	public ResolverException(String message, Throwable cause) {
		super(message, cause);
	}

}
