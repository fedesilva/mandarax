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
import java.util.List;
import org.junit.Test;
import org.mandarax.dsl.*;
import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Test cases for complex expressions.
 * @author jens dietrich
 */ 
public class ParserTestsRules extends AbstractTests{
	
	
	
	@Test
	public void testRule1() throws Exception {
		Rule rule = readRule("rule1: x>2 -> special(x);");
		print(rule);
		assertEquals("rule1",rule.getId());
		
		List<Expression> body = rule.getBody();
		assertEquals(1,body.size());
		
		Expression prereq1 = body.get(0);
		testVarOpInt(prereq1,"x",BinOp.GT,2);
		
		Expression head = rule.getHead();
		assertTrue(head instanceof FunctionInvocation);
		FunctionInvocation f = (FunctionInvocation)head;
		
		assertEquals("special",f.getFunction());
		List<Expression> terms = f.getParameters();
		assertEquals(1,terms.size());
		assertVariable(terms.get(0),"x");
	}
	
	@Test
	public void testRule2() throws Exception {
		Rule rule = readRule("rule_2: x>2 & x<=100-> special(x,42);");
		print(rule);
		assertEquals("rule_2",rule.getId());
		
		List<Expression> body = rule.getBody();
		assertEquals(2,body.size());
		
		Expression prereq1 = body.get(0);
		testVarOpInt(prereq1,"x",BinOp.GT,2);
		
		Expression prereq2 = body.get(1);
		testVarOpInt(prereq2,"x",BinOp.LTE,100);
		
		Expression head = rule.getHead();
		assertTrue(head instanceof FunctionInvocation);
		FunctionInvocation f = (FunctionInvocation)head;
		
		assertEquals("special",f.getFunction());
		List<Expression> terms = f.getParameters();
		assertEquals(2,terms.size());
		assertVariable(terms.get(0),"x");
		assertIntLiteral(terms.get(1),42);
	}
	
	

}
