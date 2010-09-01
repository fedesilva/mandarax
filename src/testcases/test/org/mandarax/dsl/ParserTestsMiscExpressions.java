package test.org.mandarax.dsl;
/**
 * Copyright 2010 Jens Dietrich 
 * @license
 */


import static org.junit.Assert.*;
import org.mandarax.dsl.parser.ExpressionReader;
import org.junit.Test;
import org.mandarax.dsl.*;


public class ParserTestsMiscExpressions extends AbstractTests{
	
	@Test
	public void testCastExpression() throws Exception {
		Expression expression = new ExpressionReader().readExpression("((String)x).length");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess x = (MemberAccess)expression;
		assertEquals("length",x.getMember());
		assertTrue(x.getObjectReference() instanceof CastExpression);
		CastExpression x2 = (CastExpression)x.getObjectReference();
		assertEquals("String",x2.getType());
		assertTrue(x2.getObjectReference() instanceof Variable);
		assertEquals("x",((Variable)x2.getObjectReference()).getName());
	}
	
	@Test
	public void testInstanceOfExpression() throws Exception {
		Expression expression = new ExpressionReader().readExpression("!(x instanceof String)");
		print(expression);
		assertTrue(expression instanceof UnaryExpression);
		UnaryExpression x = (UnaryExpression)expression;
		assertEquals(UnOp.NOT,x.getOperator());
		assertTrue(x.getPart() instanceof InstanceOfExpression);
		InstanceOfExpression x2 = (InstanceOfExpression)x.getPart();
		assertEquals("String",x2.getType());
		assertTrue(x2.getObjectReference() instanceof Variable);
		assertEquals("x",((Variable)x2.getObjectReference()).getName());
	}

}
