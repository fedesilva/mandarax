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
 * Test cases for aggregations.
 * @author jens dietrich
 */
public class ParserTestsAggregations extends AbstractTests{
	
	@Test
	public void testAggregationExpression1() throws Exception {
		Expression expression = readExpression("avg x in f(x,y)");
		print(expression);
		assertTrue(expression instanceof Aggregation);
		Aggregation x = (Aggregation)expression;
		assertEquals(AggregationFunction.avg,x.getFunction());
		assertEquals("x",x.getVariable().getName());
		assertTrue(x.getExpression() instanceof FunctionInvocation);
		FunctionInvocation fi = (FunctionInvocation)x.getExpression();
		assertEquals("f",fi.getFunction());
		assertEquals(2,fi.getParameters().size());
	}
	
	@Test
	public void testAggregationExpression2() throws Exception {
		Expression expression = readExpression("min x in f(x,y)");
		print(expression);
		assertTrue(expression instanceof Aggregation);
		Aggregation x = (Aggregation)expression;
		assertEquals(AggregationFunction.min,x.getFunction());
		assertEquals("x",x.getVariable().getName());
		assertTrue(x.getExpression() instanceof FunctionInvocation);
		FunctionInvocation fi = (FunctionInvocation)x.getExpression();
		assertEquals("f",fi.getFunction());
		assertEquals(2,fi.getParameters().size());
	}
	
	@Test
	public void testAggregationExpression3() throws Exception {
		Expression expression = readExpression("max x in f(x,y)");
		print(expression);
		assertTrue(expression instanceof Aggregation);
		Aggregation x = (Aggregation)expression;
		assertEquals(AggregationFunction.max,x.getFunction());
		assertEquals("x",x.getVariable().getName());
		assertTrue(x.getExpression() instanceof FunctionInvocation);
		FunctionInvocation fi = (FunctionInvocation)x.getExpression();
		assertEquals("f",fi.getFunction());
		assertEquals(2,fi.getParameters().size());
	}
	
	@Test
	public void testAggregationExpression4() throws Exception {
		Expression expression = readExpression("sum x in f(x,y)");
		print(expression);
		assertTrue(expression instanceof Aggregation);
		Aggregation x = (Aggregation)expression;
		assertEquals(AggregationFunction.sum,x.getFunction());
		assertEquals("x",x.getVariable().getName());
		assertTrue(x.getExpression() instanceof FunctionInvocation);
		FunctionInvocation fi = (FunctionInvocation)x.getExpression();
		assertEquals("f",fi.getFunction());
		assertEquals(2,fi.getParameters().size());
	}
	
	@Test
	public void testAggregationExpression5() throws Exception {
		Expression expression = readExpression("count x in f(x,y)");
		print(expression);
		assertTrue(expression instanceof Aggregation);
		Aggregation x = (Aggregation)expression;
		assertEquals(AggregationFunction.count,x.getFunction());
		assertEquals("x",x.getVariable().getName());
		assertTrue(x.getExpression() instanceof FunctionInvocation);
		FunctionInvocation fi = (FunctionInvocation)x.getExpression();
		assertEquals("f",fi.getFunction());
		assertEquals(2,fi.getParameters().size());
	}
	
	@Test
	public void testAggregationExpression6() throws Exception {
		Expression expression = readExpression("noaggfunction x in f(x,y)");
		print(expression);
		assertFalse(expression instanceof Aggregation);
	}
	
	

}
