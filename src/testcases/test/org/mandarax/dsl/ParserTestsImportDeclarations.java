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

package test.org.mandarax.dsl;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mandarax.dsl.*;
import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Test cases for import declarations.
 * @author jens dietrich
 */
public class ParserTestsImportDeclarations extends AbstractTests {
	
	@Test
	public void testImportDeclaration1() throws Exception {
		ImportDeclaration imp = readImportDeclaration("import java.util.Date;");
		assertEquals(imp.getName(),"java.util.Date");
		assertFalse(imp.isUsingWildcard());
		assertFalse(imp.isStaticImport());
	}
	
	@Test
	public void testImportDeclaration2() throws Exception {
		ImportDeclaration imp = readImportDeclaration("import java.util.*;");
		assertEquals(imp.getName(),"java.util");
		assertTrue(imp.isUsingWildcard());
		assertFalse(imp.isStaticImport());
	}
	
	@Test
	public void testImportDeclaration3() throws Exception {
		ImportDeclaration imp = readImportDeclaration("import static java.lang.System.currentTimeMillis;");
		assertEquals(imp.getName(),"java.lang.System.currentTimeMillis");
		assertFalse(imp.isUsingWildcard());
		assertTrue(imp.isStaticImport());
	}
	
	@Test
	public void testImportDeclaration4() throws Exception {
		ImportDeclaration imp = readImportDeclaration("import static java.lang.System.*;");
		assertEquals(imp.getName(),"java.lang.System");
		assertTrue(imp.isUsingWildcard());
		assertTrue(imp.isStaticImport());
	}
	
}
