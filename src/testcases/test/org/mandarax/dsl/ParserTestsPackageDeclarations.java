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

package test.org.mandarax.dsl;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mandarax.dsl.*;
import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Test cases for package declarations.
 * @author jens dietrich
 */
public class ParserTestsPackageDeclarations extends AbstractTests {
	
	@Test
	public void testPackageDeclaration1() throws Exception {
		PackageDeclaration pck = readPackageDeclaration("package org.mandarax.dsl;");
		assertEquals(pck.getName(),"org.mandarax.dsl");
		assertEquals(pck.getContext().getPackageDeclaration(),pck);
	}

	
}
