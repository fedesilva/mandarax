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
 * Test cases for complex expressions.
 * @author jens dietrich
 */ 
public class ParserTestsComplexExpressions extends AbstractTests{
	
	private void testVarOpInt(Expression x,String varName,BinOp op,int intValue) throws Exception {
		assertTrue(x instanceof BinaryExpression);
		BinaryExpression bx = (BinaryExpression)x;
		assertTrue(bx.getLeft() instanceof Variable);
		assertTrue(bx.getRight() instanceof IntLiteral);
		assertEquals(op,bx.getOperator());
		assertEquals(varName,((Variable)bx.getLeft()).getName());
		assertEquals(intValue,(int)((IntLiteral)bx.getRight()).getValue());
	}
	
	@Test
	public void testDisjunction1() throws Exception {
		Expression expression = readExpression("(x==2)|(y!=3)");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.OR,x.getOperator());
		testVarOpInt(x.getLeft(),"x",BinOp.EQ,2);
		testVarOpInt(x.getRight(),"y",BinOp.NEQ,3);
	}
	
	@Test
	public void testDisjunction2() throws Exception {
		Expression expression = readExpression("x==2|y!=3");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.OR,x.getOperator());
		testVarOpInt(x.getLeft(),"x",BinOp.EQ,2);
		testVarOpInt(x.getRight(),"y",BinOp.NEQ,3);
	}
	
	@Test
	public void testDisjunction3() throws Exception {
		Expression expression = readExpression("x<2|y>3");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.OR,x.getOperator());
		testVarOpInt(x.getLeft(),"x",BinOp.LT,2);
		testVarOpInt(x.getRight(),"y",BinOp.GT,3);
	}
	
	@Test
	public void testConjunction1() throws Exception {
		Expression expression = readExpression("(x==2)&(y!=3)");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.AND,x.getOperator());
		testVarOpInt(x.getLeft(),"x",BinOp.EQ,2);
		testVarOpInt(x.getRight(),"y",BinOp.NEQ,3);
	}
	
	@Test
	public void testConjunction2() throws Exception {
		Expression expression = readExpression("x==2&y!=3");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.AND,x.getOperator());
		testVarOpInt(x.getLeft(),"x",BinOp.EQ,2);
		testVarOpInt(x.getRight(),"y",BinOp.NEQ,3);
	}
	
	@Test
	public void testConjunction3() throws Exception {
		Expression expression = readExpression("x<2&y>3");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.AND,x.getOperator());
		testVarOpInt(x.getLeft(),"x",BinOp.LT,2);
		testVarOpInt(x.getRight(),"y",BinOp.GT,3);
	}
	
	@Test
	public void testLong1() throws Exception {
		Expression expression = readExpression("x==2 | y>3 | z<4");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.OR,x.getOperator());
		testVarOpInt(x.getRight(),"z",BinOp.LT,4);
		
		assertTrue(x.getLeft() instanceof BinaryExpression);
		BinaryExpression x1 = (BinaryExpression)x.getLeft();
		assertEquals(BinOp.OR,x1.getOperator());
		testVarOpInt(x1.getLeft(),"x",BinOp.EQ,2);
		testVarOpInt(x1.getRight(),"y",BinOp.GT,3);
	}

	@Test
	public void testLong2() throws Exception {
		Expression expression = readExpression("x==2 & y>3 & z<4");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.AND,x.getOperator());
		testVarOpInt(x.getRight(),"z",BinOp.LT,4);
		
		assertTrue(x.getLeft() instanceof BinaryExpression);
		BinaryExpression x1 = (BinaryExpression)x.getLeft();
		assertEquals(BinOp.AND,x1.getOperator());
		testVarOpInt(x1.getLeft(),"x",BinOp.EQ,2);
		testVarOpInt(x1.getRight(),"y",BinOp.GT,3);
	}
	
	@Test
	public void testMixed1() throws Exception {
		Expression expression = readExpression("x==2 & y>3 | z<4");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.OR,x.getOperator());
		testVarOpInt(x.getRight(),"z",BinOp.LT,4);
		
		assertTrue(x.getLeft() instanceof BinaryExpression);
		BinaryExpression x1 = (BinaryExpression)x.getLeft();
		assertEquals(BinOp.AND,x1.getOperator());
		testVarOpInt(x1.getLeft(),"x",BinOp.EQ,2);
		testVarOpInt(x1.getRight(),"y",BinOp.GT,3);
	}
	// | has precedense over & 
	@Test
	public void testMixed2() throws Exception {
		Expression expression = readExpression("x==2 | y>3 & z<4");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.OR,x.getOperator());
		testVarOpInt(x.getLeft(),"x",BinOp.EQ,2);
		
		assertTrue(x.getRight() instanceof BinaryExpression);
		BinaryExpression x1 = (BinaryExpression)x.getRight();
		assertEquals(BinOp.AND,x1.getOperator());
		testVarOpInt(x1.getLeft(),"y",BinOp.GT,3);
		testVarOpInt(x1.getRight(),"z",BinOp.LT,4);
	}
	
	@Test
	public void testMixed3() throws Exception {
		Expression expression = readExpression("x==2 & (y>3 | z<4)");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.AND,x.getOperator());
		testVarOpInt(x.getLeft(),"x",BinOp.EQ,2);
		
		assertTrue(x.getRight() instanceof BinaryExpression);
		BinaryExpression x1 = (BinaryExpression)x.getRight();
		assertEquals(BinOp.OR,x1.getOperator());
		testVarOpInt(x1.getLeft(),"y",BinOp.GT,3);
		testVarOpInt(x1.getRight(),"z",BinOp.LT,4);
	}

}
