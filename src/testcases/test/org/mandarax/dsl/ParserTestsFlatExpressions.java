package test.org.mandarax.dsl;
/**
 * Copyright 2010 Jens Dietrich 
 * @license
 */


import static org.junit.Assert.*;
import org.mandarax.dsl.parser.ExpressionReader;
import org.junit.Test;
import org.mandarax.dsl.*;


public class ParserTestsFlatExpressions extends AbstractTests {
	
	@Test
	public void testStringLiteral1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("\"test\"");
		print(expression);
		assertTrue(expression instanceof StringLiteral);
		StringLiteral x = (StringLiteral)expression;
		assertEquals("test",x.getValue());
	}

	
	@Test
	public void testIntLiteralDec1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("42");
		print(expression);
		assertTrue(expression instanceof IntLiteral);
		IntLiteral x = (IntLiteral)expression;
		assertEquals(42,(int)x.getValue());
	}
	@Test
	public void testIntLiteralHex1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("0xB");
		print(expression);
		assertTrue(expression instanceof IntLiteral);
		IntLiteral x = (IntLiteral)expression;
		assertEquals(11,(int)x.getValue());
	}
	
	
	@Test
	public void testIntLiteralOct1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("012");
		print(expression);
		assertTrue(expression instanceof IntLiteral);
		IntLiteral x = (IntLiteral)expression;
		assertEquals(10,(int)x.getValue());
	}
	
	@Test
	public void testBooleanLiteral1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("true");
		print(expression);
		assertTrue(expression instanceof BooleanLiteral);
		BooleanLiteral x = (BooleanLiteral)expression;
		assertEquals(true,x.getValue());
	}
	
	@Test
	public void testBooleanLiteral2() throws Exception {
		Expression expression = new ExpressionReader().readExpression("false");
		print(expression);
		assertTrue(expression instanceof BooleanLiteral);
		BooleanLiteral x = (BooleanLiteral)expression;
		assertEquals(false,x.getValue());
	}
	
	@Test
	public void testVariable1() throws Exception {
		Expression expression = new ExpressionReader().readExpression("myvar");
		print(expression);
		assertTrue(expression instanceof Variable);
		Variable x = (Variable)expression;
		assertEquals("myvar",x.getName());
	}

}
