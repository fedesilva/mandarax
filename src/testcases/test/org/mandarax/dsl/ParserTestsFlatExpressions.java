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
 * Test cases for flat expressions (variables and literals).
 * @author jens dietrich
 */

public class ParserTestsFlatExpressions extends AbstractTests {
	
	@Test
	public void testStringLiteral1() throws Exception {
		Expression expression = readExpression("\"test\"");
		print(expression);
		assertTrue(expression instanceof StringLiteral);
		StringLiteral x = (StringLiteral)expression;
		assertEquals("test",x.getValue());
	}

	@Test
	public void testStringLiteral2() throws Exception {
		Expression expression = readExpression("\"\\\"test\\\"\"");
		print(expression);
		assertTrue(expression instanceof StringLiteral);
		StringLiteral x = (StringLiteral)expression;
		//System.out.println(org.apache.commons.lang.StringEscapeUtils.unescapeJava(x.getValue()));
		assertEquals("\"test\"",x.getValue());
	}
	// this is a keyword
	@Test
	public void testStringLiteral3() throws Exception {
		Expression expression = readExpression("\"in\"");
		print(expression);
		assertTrue(expression instanceof StringLiteral);
		StringLiteral x = (StringLiteral)expression;
		assertEquals("in",x.getValue());
	}
	// this is a keyword
	@Test
	public void testStringLiteral4() throws Exception {
		Expression expression = readExpression("\"min\"");
		print(expression);
		assertTrue(expression instanceof StringLiteral);
		StringLiteral x = (StringLiteral)expression;
		assertEquals("min",x.getValue());
	}
	
	@Test
	public void testIntLiteralDec1() throws Exception {
		Expression expression = readExpression("42");
		print(expression);
		assertTrue(expression instanceof IntLiteral);
		IntLiteral x = (IntLiteral)expression;
		assertEquals(42,(int)x.getValue());
	}
	@Test
	public void testIntLiteralHex1() throws Exception {
		Expression expression = readExpression("0xB");
		print(expression);
		assertTrue(expression instanceof IntLiteral);
		IntLiteral x = (IntLiteral)expression;
		assertEquals(11,(int)x.getValue());
	}
	
	
	@Test
	public void testIntLiteralOct1() throws Exception {
		Expression expression = readExpression("012");
		print(expression);
		assertTrue(expression instanceof IntLiteral);
		IntLiteral x = (IntLiteral)expression;
		assertEquals(10,(int)x.getValue());
	}
	
	@Test
	public void testBooleanLiteral1() throws Exception {
		Expression expression = readExpression("true");
		print(expression);
		assertTrue(expression instanceof BooleanLiteral);
		BooleanLiteral x = (BooleanLiteral)expression;
		assertEquals(true,x.getValue());
	}
	
	@Test
	public void testBooleanLiteral2() throws Exception {
		Expression expression = readExpression("false");
		print(expression);
		assertTrue(expression instanceof BooleanLiteral);
		BooleanLiteral x = (BooleanLiteral)expression;
		assertEquals(false,x.getValue());
	}
	
	@Test
	public void testVariable1() throws Exception {
		Expression expression = readExpression("myvar");
		print(expression);
		assertTrue(expression instanceof Variable);
		Variable x = (Variable)expression;
		assertEquals("myvar",x.getName());
	}

}
