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
import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Test cases for queries.
 * @author jens dietrich
 */
public class ParserTestsQuery extends AbstractTests {
	
	@Test
	public void testQuery1() throws Exception {
		Query query = readQuery("query Height(com.example.Person p,int value) getHeight(p);");
		assertEquals("Height",query.getPredicateName());
		assertEquals("getHeight",query.getMethodName());
		
		List<VariableDeclaration> slots = query.getSlotDeclarations();
		assertEquals(2,slots.size());
		assertEquals("com.example.Person",slots.get(0).getType());
		assertEquals("p",slots.get(0).getName());
		assertEquals("int",slots.get(1).getType());
		assertEquals("value",slots.get(1).getName());
		
		List<String> paramNames = query.getMethodParamNames();
		assertEquals(1,paramNames.size());
		assertEquals("p",slots.get(0).getName());
	}
	
	@Test
	public void testQuery2() throws Exception {
		Query query = readQuery("query Height(com.example.Person p,int value) getHeight(p,value);");
		assertEquals("Height",query.getPredicateName());
		assertEquals("getHeight",query.getMethodName());
		
		List<VariableDeclaration> slots = query.getSlotDeclarations();
		assertEquals(2,slots.size());
		assertEquals("com.example.Person",slots.get(0).getType());
		assertEquals("p",slots.get(0).getName());
		assertEquals("int",slots.get(1).getType());
		assertEquals("value",slots.get(1).getName());
		
		List<String> paramNames = query.getMethodParamNames();
		assertEquals(2,paramNames.size());
		assertEquals("p",slots.get(0).getName());
		assertEquals("value",slots.get(1).getName());
	}
	
	@Test
	public void testQuery3() throws Exception {
		Query query = readQuery("query Height(com.example.Person p,int value) getHeight();");
		assertEquals("Height",query.getPredicateName());
		assertEquals("getHeight",query.getMethodName());
		
		List<VariableDeclaration> slots = query.getSlotDeclarations();
		assertEquals(2,slots.size());
		assertEquals("com.example.Person",slots.get(0).getType());
		assertEquals("p",slots.get(0).getName());
		assertEquals("int",slots.get(1).getType());
		assertEquals("value",slots.get(1).getName());
		
		List<String> paramNames = query.getMethodParamNames();
		assertEquals(0,paramNames.size());
	}
	// p1 is not defined
	@Test(expected=IllegalArgumentException.class)
	public void testQuery4() throws Exception {
		readQuery("query Height(com.example.Person p,int value) getHeight(p1);");
	}
	
	
	
	
}
