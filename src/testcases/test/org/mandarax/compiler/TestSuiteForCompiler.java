/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package test.org.mandarax.compiler;
import org.junit.runner.RunWith;

import org.junit.runners.*;

/**
 * Test suite containing all compiler tests.
 * Use GenerateCodeForTesting to create classes for tests.
 * @author jens dietrich
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CompilerTests4.class,
	CompilerTests5.class,
	CompilerTests6.class,
	CompilerTests7.class,
	CompilerTests8.class,
	CompilerTests10.class,
	CompilerTests11.class,
	CompilerTests12.class
})
public class TestSuiteForCompiler {

}

