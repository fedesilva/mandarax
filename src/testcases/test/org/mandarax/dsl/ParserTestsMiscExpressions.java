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
 * Test cases for misc expressions.
 * @author jens dietrich
 */
public class ParserTestsMiscExpressions extends AbstractTests{
	
	@Test
	public void testCastExpression() throws Exception {
		Expression expression = readExpression("((String)x).length");
		print(expression);
		assertTrue(expression instanceof MemberAccess);
		MemberAccess x = (MemberAccess)expression;
		assertEquals("length",x.getMember());
		assertTrue(x.getObjectReference() instanceof CastExpression);
		CastExpression x2 = (CastExpression)x.getObjectReference();
		assertEquals("String",x2.getTypeName());
		assertTrue(x2.getObjectReference() instanceof Variable);
		assertEquals("x",((Variable)x2.getObjectReference()).getName());
	}
	
	@Test
	public void testInstanceOfExpression() throws Exception {
		Expression expression = readExpression("!(x instanceof String)");
		print(expression);
		assertTrue(expression instanceof UnaryExpression);
		UnaryExpression x = (UnaryExpression)expression;
		assertEquals(UnOp.NOT,x.getOperator());
		assertTrue(x.getPart() instanceof InstanceOfExpression);
		InstanceOfExpression x2 = (InstanceOfExpression)x.getPart();
		assertEquals("String",x2.getTypeName());
		assertTrue(x2.getObjectReference() instanceof Variable);
		assertEquals("x",((Variable)x2.getObjectReference()).getName());
	}

}
