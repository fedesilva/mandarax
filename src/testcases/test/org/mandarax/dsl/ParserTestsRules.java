/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.gnu.org/licenses/agpl.html 
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
 * Test cases for rules.
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
		
		assertFalse(rule.isFact());
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
		
		assertFalse(rule.isFact());
	}
	
	@Test
	public void testRule3() throws Exception {
		Rule rule = readRule("@id=\"rel42\" \n rule_2: x>2 & x<=100-> special(x,42);");
		print(rule);
		
		List<Annotation> annotations = rule.getAnnotations();
		assertEquals(1,annotations.size());
		assertEquals("id",annotations.get(0).getKey());
		assertEquals("rel42",annotations.get(0).getValue());
		
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
		
		assertFalse(rule.isFact());
	}
	
	@Test
	public void testRule4() throws Exception {
		Rule rule = readRule("@id=\"rel42\" \n @author = \"jens\"\r\n\nrule_2: x>2 & x<=100-> special(x,42);");
		print(rule);
		
		List<Annotation> annotations = rule.getAnnotations();
		assertEquals(2,annotations.size());
		assertEquals("id",annotations.get(0).getKey());
		assertEquals("rel42",annotations.get(0).getValue());
		assertEquals("author",annotations.get(1).getKey());
		assertEquals("jens",annotations.get(1).getValue());
		
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
		
		assertFalse(rule.isFact());
	}
	
	@Test
	public void testFact1() throws Exception {
		Rule rule = readRule("fact1: -> special(42);");
		print(rule);
		assertEquals("fact1",rule.getId());
		
		List<Expression> body = rule.getBody();
		assertEquals(0,body.size());
		assertTrue(rule.isFact());
		
		Expression head = rule.getHead();
		assertTrue(head instanceof FunctionInvocation);
		FunctionInvocation f = (FunctionInvocation)head;
		
		assertEquals("special",f.getFunction());
		List<Expression> terms = f.getParameters();
		assertEquals(1,terms.size());
		assertEquals(new Integer(42),((IntLiteral)terms.get(0)).getValue());
	}

}
