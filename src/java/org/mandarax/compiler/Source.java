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

import java.io.IOException;
import java.io.InputStream;

/**
 * Abstract interface describing locations from where relationship definitions can be read.
 * Can be easily implemented by wrapping files, URLs, memory resources (like strings) or existing streams. 
 * @author jens dietrich
 */
public interface Source {
	public InputStream openStream() throws IOException ; 
	// a descriptive name (file name, URL, ..)
	public String getName();
	
}
