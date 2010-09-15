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
import java.io.InputStream;
import java.util.List;
import org.junit.Test;
import org.mandarax.dsl.*;
import org.mandarax.dsl.parser.ScriptException;
import org.mandarax.dsl.parser.ScriptReader;
import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Test cases for entire compilation units.
 * @author jens dietrich
 */ 
public class ParserIntegrationTests extends AbstractTests{
	
	
	@Test
	public void testCompilationUnit1() throws Exception {
		CompilationUnit cu = readCUFromCP("rules1.rel");
		
		assertEquals("test.org.mandarax.dsl",cu.getPackageDeclaration().getName()); 
		assertEquals(1,cu.getImportDeclarations().size());
		assertEquals("java.util.Date",cu.getImportDeclarations().get(0).getName()); 
		assertEquals(1,cu.getRelationshipDefinitions().size());
		assertEquals("Father",cu.getRelationshipDefinitions().get(0).getName());
		List<Rule> rules1 = cu.getRelationshipDefinitions().get(0).getRules();
		assertEquals(1,rules1.size());
		assertEquals("rule1",rules1.get(0).getId());
	}
	
	@Test
	public void testCompilationUnit2() throws Exception {
		CompilationUnit cu = readCUFromCP("rules2.rel");
		
		assertEquals("test.org.mandarax.dsl",cu.getPackageDeclaration().getName()); 
		
		assertEquals(1,cu.getContext().getImportDeclarations().size());
		assertEquals("java.util.Date",cu.getImportDeclarations().get(0).getName());
		assertEquals(1,cu.getStaticImportDeclarations().size());
		assertEquals("java.lang.System",cu.getStaticImportDeclarations().get(0).getName());
		
		assertEquals(3,cu.getRelationshipDefinitions().size());
		assertEquals("Parent",cu.getRelationshipDefinitions().get(0).getName());
		assertEquals("Father",cu.getRelationshipDefinitions().get(1).getName());
		assertEquals("GrandFather",cu.getRelationshipDefinitions().get(2).getName());
		
		assertEquals(2,cu.getRelationshipDefinitions().get(0).getAnnotations().size());
		assertEquals(1,cu.getRelationshipDefinitions().get(1).getAnnotations().size());
		assertEquals(1,cu.getRelationshipDefinitions().get(2).getAnnotations().size());
		
	}
	
	@Test
	public void testCompilationUnit3() throws Exception {
		CompilationUnit cu = readCUFromCP("rules3.rel");
		
		assertEquals("test.org.mandarax.dsl",cu.getPackageDeclaration().getName()); 
		assertEquals(1,cu.getImportDeclarations().size());
		assertEquals("java.util.Date",cu.getImportDeclarations().get(0).getName()); 
		assertEquals(1,cu.getRelationshipDefinitions().size());
		assertEquals("Father",cu.getRelationshipDefinitions().get(0).getName());
		
		List<Rule> rules1 = cu.getRelationshipDefinitions().get(0).getRules();
		assertEquals(2,rules1.size());
		assertEquals("rule1",rules1.get(0).getId());
		assertEquals("rule2",rules1.get(1).getId());
		
		List<ObjectDeclaration> objects = cu.getObjectDeclarations();
		assertEquals(1,objects.size());
		assertEquals("me",objects.get(0).getName());
		assertEquals("Person",objects.get(0).getType());
	}

	
}
