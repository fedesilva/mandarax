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
 * Test cases for o.
 * @author jens dietrich
 */ 
public class ParserTestsObjectDeclarations extends AbstractTests{
	
	@Test
	public void testObjectDeclaration1() throws Exception {
		ObjectDeclaration decl = readObjectDeclaration("Person klaus = new PersonImpl(\"Klaus\");");
		assertEquals("klaus",decl.getName());
		assertEquals("Person",decl.getType());
		assertTrue(decl.getDefaultValueDeclaration() instanceof ConstructorInvocation);
		
		ConstructorInvocation ci = (ConstructorInvocation)decl.getDefaultValueDeclaration();
		assertEquals("PersonImpl",ci.getTypeName());
		assertEquals(1,ci.getParameters().size());
		assertEquals("Klaus",((StringLiteral)ci.getParameters().get(0)).getValue());
	}
	
	@Test
	public void testObjectDeclaration2() throws Exception {
		ObjectDeclaration decl = readObjectDeclaration("com.example.Person klaus = new com.example.PersonImpl(\"Klaus\");");
		assertEquals("klaus",decl.getName());
		assertEquals("com.example.Person",decl.getType());
		assertTrue(decl.getDefaultValueDeclaration() instanceof ConstructorInvocation);
		
		ConstructorInvocation ci = (ConstructorInvocation)decl.getDefaultValueDeclaration();
		assertEquals("com.example.PersonImpl",ci.getTypeName());
		assertEquals(1,ci.getParameters().size());
		assertEquals("Klaus",((StringLiteral)ci.getParameters().get(0)).getValue());
	}
	
	@Test
	public void testObjectDeclaration3() throws Exception {
		ObjectDeclaration decl = readObjectDeclaration("com.example.Person klaus = null;");
		assertEquals("klaus",decl.getName());
		assertEquals("com.example.Person",decl.getType());
		assertTrue(decl.getDefaultValueDeclaration() instanceof NullValue);
	}
	

}
