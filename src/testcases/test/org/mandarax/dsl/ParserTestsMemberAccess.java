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
/**

 * @license
 */


import static org.junit.Assert.*;
import org.mandarax.dsl.parser.ExpressionReader;
import org.junit.Test;
import org.mandarax.dsl.*;

/**
 * Test cases for method and field access expressions.
 * @author jens dietrich
 */

public class ParserTestsMemberAccess extends AbstractTests {
	
	@Test
	public void testPropertyAccess1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("myvar.prop1");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess pa = (MemberAccess)expression;
		assertFalse(pa.isMethod());
		assertEquals("prop1",pa.getMember());
		assertEquals("myvar",((Variable)pa.getObjectReference()).getName());
	}
	@Test
	public void testPropertyAccess2() throws Exception {
		Expression expression = new ExpressionReader().readExpression("myvar.prop1.prop2");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess pa = (MemberAccess)expression;
		assertFalse(pa.isMethod());
		assertEquals("prop2",pa.getMember());
		assertTrue(pa.getObjectReference() instanceof MemberAccess);
		MemberAccess pa2= (MemberAccess)pa.getObjectReference();
		assertFalse(pa2.isMethod());
		assertEquals("prop1",pa2.getMember());
		assertEquals("myvar",((Variable)pa2.getObjectReference()).getName());
	}
	
	@Test
	public void testMethodInvocation1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("myvar.meth1()");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess mi = (MemberAccess)expression;
		assertTrue(mi.isMethod());
		assertEquals("meth1",mi.getMember());
		assertEquals(0,mi.getParameters().size());
		assertEquals("myvar",((Variable)mi.getObjectReference()).getName());
	}
	
	@Test
	public void testMethodInvocation2() throws Exception {
		Expression expression = new ExpressionReader().readExpression("\"test\".length()");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess mi = (MemberAccess)expression;
		assertTrue(mi.isMethod());
		assertEquals("length",mi.getMember());
		assertEquals(0,mi.getParameters().size());
		assertEquals("test",((StringLiteral)mi.getObjectReference()).getValue());
	}
	
	@Test
	public void testMethodInvocation3() throws Exception {
		Expression expression = new ExpressionReader().readExpression("myvar.meth1(\"param1\")");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess mi = (MemberAccess)expression;
		assertEquals("meth1",mi.getMember());
		assertTrue(mi.isMethod());
		assertEquals(1,mi.getParameters().size());
		assertEquals("param1",((StringLiteral)mi.getParameters().get(0)).getValue());
		assertEquals("myvar",((Variable)mi.getObjectReference()).getName());
	}
	
	@Test
	public void testMethodInvocation4() throws Exception {
		Expression expression = new ExpressionReader().readExpression("myvar.meth1(\"param1\",42)");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess mi = (MemberAccess)expression;
		assertEquals("meth1",mi.getMember());
		assertTrue(mi.isMethod());
		assertEquals(2,mi.getParameters().size());
		assertEquals("param1",((StringLiteral)mi.getParameters().get(0)).getValue());
		assertEquals(42,(int)((IntLiteral)mi.getParameters().get(1)).getValue());
		assertEquals("myvar",((Variable)mi.getObjectReference()).getName());
	}
	


	@Test
	public void testMethodInvocation5() throws Exception {
		Expression expression = new ExpressionReader().readExpression("myvar.meth1(\"param1\".length())");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess mi = (MemberAccess)expression;
		assertTrue(mi.isMethod());
		assertEquals("meth1",mi.getMember());
		assertEquals(1,mi.getParameters().size());
		assertEquals("myvar",((Variable)mi.getObjectReference()).getName());
		
		MemberAccess mi2 = (MemberAccess)mi.getParameters().get(0);
		assertTrue(mi2.isMethod());
		assertEquals("length",mi2.getMember());
		assertEquals(0,mi2.getParameters().size());
		assertEquals("param1",((StringLiteral)mi2.getObjectReference()).getValue());
	}

}
