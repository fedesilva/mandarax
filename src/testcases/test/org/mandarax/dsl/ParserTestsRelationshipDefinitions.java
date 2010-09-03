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
import java.util.List;
import org.junit.Test;
import org.mandarax.dsl.*;
import org.mandarax.dsl.parser.ScriptException;

import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Test cases for queries.
 * @author jens dietrich
 */
public class ParserTestsRelationshipDefinitions extends AbstractTests {
	
	@Test
	public void testQuery1() throws Exception {
		RelationshipDefinition rel = readRelationshipDefinition("rel Height(com.example.Person p,int value) getHeight(p);");
		assertEquals("Height",rel.getName());
		
		List<VariableDeclaration> slots = rel.getSlotDeclarations();
		assertEquals(2,slots.size());
		assertEquals("com.example.Person",slots.get(0).getType());
		assertEquals("p",slots.get(0).getName());
		assertEquals("int",slots.get(1).getType());
		assertEquals("value",slots.get(1).getName());
		
		List<FunctionDeclaration> queries = rel.getQueries();
		assertEquals(1,queries.size());
		assertEquals(Visibility.PUBLIC,queries.get(0).getVisibility());
		assertEquals("getHeight",queries.get(0).getName());
		assertEquals(1,queries.get(0).getParameterNames().size());
		assertEquals("p",queries.get(0).getParameterNames().get(0));
	}
	
	@Test
	public void testQuery2() throws Exception {
		RelationshipDefinition rel = readRelationshipDefinition("relationship Height(com.example.Person p,int value) private getHeight(p);");
		assertEquals("Height",rel.getName());
		
		List<VariableDeclaration> slots = rel.getSlotDeclarations();
		assertEquals(2,slots.size());
		assertEquals("com.example.Person",slots.get(0).getType());
		assertEquals("p",slots.get(0).getName());
		assertEquals("int",slots.get(1).getType());
		assertEquals("value",slots.get(1).getName());
		
		List<FunctionDeclaration> queries = rel.getQueries();
		assertEquals(1,queries.size());
		assertEquals(Visibility.PRIVATE,queries.get(0).getVisibility());
		assertEquals("getHeight",queries.get(0).getName());
		assertEquals(1,queries.get(0).getParameterNames().size());
		assertEquals("p",queries.get(0).getParameterNames().get(0));
	}
	
	
	@Test
	public void testQuery3() throws Exception {
		RelationshipDefinition rel = readRelationshipDefinition("rel Height(com.example.Person p,int value) private getHeight(p,value), public getHeightForPerson(p), getHeights() ;");
		assertEquals("Height",rel.getName());
		
		List<VariableDeclaration> slots = rel.getSlotDeclarations();
		assertEquals(2,slots.size());
		assertEquals("com.example.Person",slots.get(0).getType());
		assertEquals("p",slots.get(0).getName());
		assertEquals("int",slots.get(1).getType());
		assertEquals("value",slots.get(1).getName());
		
		List<FunctionDeclaration> queries = rel.getQueries();
		assertEquals(3,queries.size());
		
		assertEquals(Visibility.PRIVATE,queries.get(0).getVisibility());
		assertEquals("getHeight",queries.get(0).getName());
		assertEquals(2,queries.get(0).getParameterNames().size());
		assertEquals("p",queries.get(0).getParameterNames().get(0));
		assertEquals("value",queries.get(0).getParameterNames().get(1));
		
		assertEquals(Visibility.PUBLIC,queries.get(1).getVisibility());
		assertEquals("getHeightForPerson",queries.get(1).getName());
		assertEquals(1,queries.get(1).getParameterNames().size());
		assertEquals("p",queries.get(1).getParameterNames().get(0));
		
		assertEquals(Visibility.PUBLIC,queries.get(2).getVisibility());
		assertEquals("getHeights",queries.get(2).getName());
		assertEquals(0,queries.get(2).getParameterNames().size());
		
		
	}

	// p1 is not defined
	@Test(expected=ScriptException.class)
	public void testQuery4() throws Exception {
		readRelationshipDefinition("rel Height(com.example.Person p,int value) getHeight(p1);");
	}
	
	
	
}
