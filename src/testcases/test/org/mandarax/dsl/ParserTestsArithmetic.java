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


public class ParserTestsArithmetic extends AbstractTests {
	
	@Test
	public void testUnaryExpression1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("-42");
		print(expression);
		assertTrue(expression instanceof UnaryExpression);
		UnaryExpression x = (UnaryExpression)expression;
		assertEquals(UnOp.MINUS,x.getOperator());
		assertTrue(x.getPart() instanceof IntLiteral);
		assertEquals(42,(int)((IntLiteral)x.getPart()).getValue());
	}
	
	@Test
	public void testUnaryExpression2() throws Exception {
		Expression expression = new ExpressionReader().readExpression("~42");
		print(expression);
		assertTrue(expression instanceof UnaryExpression);
		UnaryExpression x = (UnaryExpression)expression;
		assertEquals(UnOp.COMPL,x.getOperator());
		assertTrue(x.getPart() instanceof IntLiteral);
		assertEquals(42,(int)((IntLiteral)x.getPart()).getValue());
	}
	
	@Test
	public void testUnaryExpression3() throws Exception {
		Expression expression = new ExpressionReader().readExpression("!42");
		print(expression);
		assertTrue(expression instanceof UnaryExpression);
		UnaryExpression x = (UnaryExpression)expression;
		assertEquals(UnOp.NOT,x.getOperator());
		assertTrue(x.getPart() instanceof IntLiteral);
		assertEquals(42,(int)((IntLiteral)x.getPart()).getValue());
	}
	
	@Test
	public void testArithmeticOperators1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x+42");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.PLUS,x.getOperator());
		assertTrue(x.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x.getLeft()).getName());
		assertEquals(42,(int)((IntLiteral)x.getRight()).getValue());
	}
	
	@Test
	public void testArithmeticOperators2() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x*42");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.TIMES,x.getOperator());
		assertTrue(x.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x.getLeft()).getName());
		assertEquals(42,(int)((IntLiteral)x.getRight()).getValue());
	}
	
	@Test
	public void testArithmeticOperators3() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x%42");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.MOD,x.getOperator());
		assertTrue(x.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x.getLeft()).getName());
		assertEquals(42,(int)((IntLiteral)x.getRight()).getValue());
	}
	
	@Test
	public void testArithmeticOperators4() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x-42");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.MINUS,x.getOperator());
		assertTrue(x.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x.getLeft()).getName());
		assertEquals(42,(int)((IntLiteral)x.getRight()).getValue());
	}
	
	@Test
	public void testArithmeticOperators5() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x/42");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.DIV,x.getOperator());
		assertTrue(x.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x.getLeft()).getName());
		assertEquals(42,(int)((IntLiteral)x.getRight()).getValue());
	}
	
	@Test
	public void testArithmeticOperators6() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x >> 42");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.SHIFT_RR,x.getOperator());
		assertTrue(x.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x.getLeft()).getName());
		assertEquals(42,(int)((IntLiteral)x.getRight()).getValue());
	}
	
	@Test
	public void testArithmeticOperators7() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x >>> 42");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.SHIFT_RRR,x.getOperator());
		assertTrue(x.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x.getLeft()).getName());
		assertEquals(42,(int)((IntLiteral)x.getRight()).getValue());
	}
	
	@Test
	public void testArithmeticOperators8() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x << 42");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.SHIFT_LL,x.getOperator());
		assertTrue(x.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x.getLeft()).getName());
		assertEquals(42,(int)((IntLiteral)x.getRight()).getValue());
	}
	
	
	@Test
	public void testArithmeticOperators9() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x+y-z");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.MINUS,x.getOperator());
		
		Expression part1 = x.getLeft();
		Expression part2 = x.getRight();
		assertTrue(part1 instanceof BinaryExpression);
		assertTrue(part2 instanceof Variable);	
		BinaryExpression x1 = (BinaryExpression)part1;
		Variable x2 = (Variable)part2;
		
		assertEquals(BinOp.PLUS,x1.getOperator());
		assertTrue(x1.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x1.getLeft()).getName());
		assertTrue(x1.getRight() instanceof Variable);
		assertEquals("y",((Variable)x1.getRight()).getName());
		assertEquals("z",x2.getName());
	}
	
	@Test
	public void testArithmeticOperators10() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x*y/z");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.DIV,x.getOperator());
		
		Expression part1 = x.getLeft();
		Expression part2 = x.getRight();
		assertTrue(part1 instanceof BinaryExpression);
		assertTrue(part2 instanceof Variable);	
		BinaryExpression x1 = (BinaryExpression)part1;
		Variable x2 = (Variable)part2;
		
		assertEquals(BinOp.TIMES,x1.getOperator());
		assertTrue(x1.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x1.getLeft()).getName());
		assertTrue(x1.getRight() instanceof Variable);
		assertEquals("y",((Variable)x1.getRight()).getName());
		assertEquals("z",x2.getName());
	}
	
	@Test
	// precedense rules
	public void testArithmeticOperators11() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x*y+z");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.PLUS,x.getOperator());
		
		Expression part1 = x.getLeft();
		Expression part2 = x.getRight();
		assertTrue(part1 instanceof BinaryExpression);
		assertTrue(part2 instanceof Variable);	
		BinaryExpression x1 = (BinaryExpression)part1;
		Variable x2 = (Variable)part2;
		
		assertEquals(BinOp.TIMES,x1.getOperator());
		assertTrue(x1.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x1.getLeft()).getName());
		assertTrue(x1.getRight() instanceof Variable);
		assertEquals("y",((Variable)x1.getRight()).getName());
		assertEquals("z",x2.getName());
	}
	
	@Test
	// precedense rules
	public void testArithmeticOperators12() throws Exception {
		Expression expression = new ExpressionReader().readExpression("x+y*z");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.PLUS,x.getOperator());
		
		Expression part1 = x.getLeft();
		Expression part2 = x.getRight();
		assertTrue(part2 instanceof BinaryExpression);
		assertTrue(part1 instanceof Variable);	
		BinaryExpression x2 = (BinaryExpression)part2;
		Variable x1 = (Variable)part1;
		
		assertEquals(BinOp.TIMES,x2.getOperator());
		assertTrue(x2.getLeft() instanceof Variable);
		assertEquals("y",((Variable)x2.getLeft()).getName());
		assertTrue(x2.getRight() instanceof Variable);
		assertEquals("z",((Variable)x2.getRight()).getName());
		assertEquals("x",x1.getName());
	}
	
	@Test
	// override precedense rules with brackets
	public void testArithmeticOperators13() throws Exception {
		Expression expression = new ExpressionReader().readExpression("(x+y)*z");
		print(expression);
		assertTrue(expression instanceof BinaryExpression);
		BinaryExpression x = (BinaryExpression)expression;
		assertEquals(BinOp.TIMES,x.getOperator());
		
		Expression part1 = x.getLeft();
		Expression part2 = x.getRight();
		assertTrue(part1 instanceof BinaryExpression);
		assertTrue(part2 instanceof Variable);	
		BinaryExpression x1 = (BinaryExpression)part1;
		Variable x2 = (Variable)part2;
		
		assertEquals(BinOp.PLUS,x1.getOperator());
		assertTrue(x1.getLeft() instanceof Variable);
		assertEquals("x",((Variable)x1.getLeft()).getName());
		assertTrue(x1.getRight() instanceof Variable);
		assertEquals("y",((Variable)x1.getRight()).getName());
		assertEquals("z",x2.getName());
	}
	
	
}
