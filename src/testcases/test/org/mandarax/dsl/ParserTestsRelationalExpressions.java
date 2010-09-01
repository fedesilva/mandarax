package test.org.mandarax.dsl;
/**
 * Copyright 2010 Jens Dietrich 
 * @license
 */


import static org.junit.Assert.*;
import org.mandarax.dsl.parser.ExpressionReader;
import org.junit.Test;
import org.mandarax.dsl.*;


public class ParserTestsRelationalExpressions extends AbstractTests{
	
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
	public void testEQ() throws Exception {
		Expression x = new ExpressionReader().readExpression("x==2");
		print(x);
		testVarOpInt(x,"x",BinOp.EQ,2);
	}
	
	@Test
	public void testNEQ() throws Exception {
		Expression x = new ExpressionReader().readExpression("x!=2");
		print(x);
		testVarOpInt(x,"x",BinOp.NEQ,2);
	}
	
	@Test
	public void testLT() throws Exception {
		Expression x = new ExpressionReader().readExpression("x<2");
		print(x);
		testVarOpInt(x,"x",BinOp.LT,2);
	}
	
	@Test
	public void testGT() throws Exception {
		Expression x = new ExpressionReader().readExpression("x>2");
		print(x);
		testVarOpInt(x,"x",BinOp.GT,2);
	}
	
	@Test
	public void testLTE() throws Exception {
		Expression x = new ExpressionReader().readExpression("x<=2");
		print(x);
		testVarOpInt(x,"x",BinOp.LTE,2);
	}

	
	@Test
	public void testGTE() throws Exception {
		Expression x = new ExpressionReader().readExpression("x>=2");
		print(x);
		testVarOpInt(x,"x",BinOp.GTE,2);
	}


}
