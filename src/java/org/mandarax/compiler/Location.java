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

package org.mandarax.compiler;

import java.io.*;
/**
 * Interface describing how to access source code and compiled classes.
 * @author jens dietrich
 */
public interface Location {
	/**
	 * Get a stream to write source code.
	 * @param packageName the package name
	 * @param className the class name
	 * @return a Writer using the specified class as destination
	 * @throws CompilerException if the Writer couldn't be created 
	 */
	public Writer getSrcOut(String packageName,String className) throws CompilerException;
		
	
}
