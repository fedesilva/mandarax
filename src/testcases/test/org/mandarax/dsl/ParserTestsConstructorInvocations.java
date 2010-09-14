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
 * Test cases for constructor invocations.
 * @author jens dietrich
 */

public class ParserTestsConstructorInvocations extends AbstractTests {
	
	@Test
	public void testConstructorInvocation1() throws Exception {
		Expression expression = readExpression("new F(x)");
		print(expression);
		assertTrue(expression instanceof ConstructorInvocation);
		ConstructorInvocation fi = (ConstructorInvocation)expression;
		assertEquals("F",fi.getTypeName());
		assertEquals(1,fi.getParameters().size());
		assertEquals("x",((Variable)fi.getParameters().get(0)).getName());
	}
	
	@Test
	public void testConstructorInvocation2() throws Exception {
		Expression expression = readExpression("new  MyClassA(x,y)");
		print(expression);
		assertTrue(expression instanceof ConstructorInvocation);
		ConstructorInvocation fi = (ConstructorInvocation)expression;
		assertEquals("MyClassA",fi.getTypeName());
		assertEquals(2,fi.getParameters().size());
		assertEquals("x",((Variable)fi.getParameters().get(0)).getName());
		assertEquals("y",((Variable)fi.getParameters().get(1)).getName());
	}
	
	@Test
	public void testConstructorInvocation3() throws Exception {
		Expression expression = readExpression("new com.example.MyClassA(x,function(y))");
		print(expression);
		assertTrue(expression instanceof ConstructorInvocation);
		ConstructorInvocation fi = (ConstructorInvocation)expression;
		assertEquals("com.example.MyClassA",fi.getTypeName());
		assertEquals(2,fi.getParameters().size());
		assertEquals("x",((Variable)fi.getParameters().get(0)).getName());
		
		assertTrue(fi.getParameters().get(1) instanceof FunctionInvocation);
		FunctionInvocation fi2 = (FunctionInvocation)fi.getParameters().get(1);
		assertEquals("function",fi2.getFunction());
		assertEquals(1,fi2.getParameters().size());
		assertEquals("y",((Variable)fi2.getParameters().get(0)).getName());
	}

}
