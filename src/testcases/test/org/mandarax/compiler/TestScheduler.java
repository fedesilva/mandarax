/*
 * Copyright 2010 Jens Dietrich Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package test.org.mandarax.compiler;

import static org.junit.Assert.*;
import static test.org.mandarax.compiler.TestUtils.*;
import java.util.List;
import org.junit.Test;
import org.mandarax.compiler.impl.Prereq;
import org.mandarax.compiler.impl.Scheduler;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.Rule;

/**
 * Tests for scheduler.
 * @author jens dietrich
 */
public class TestScheduler {
	@Test
	public void test1() throws Exception {
		Rule rule = readRule("rule1: x>2 & p2(x,y) & y==42 -> p1(x);");
		List<Prereq> prereqs = Scheduler.DEFAULT.getPrerequisites(rule);
		
		for (Prereq prereq:prereqs) {
			System.out.println(prereq.getExpression());
		}
		
		assertEquals(3,prereqs.size());
		
		Expression x1 = prereqs.get(0).getExpression();
		Expression x2 = prereqs.get(1).getExpression();
		Expression x3 = prereqs.get(2).getExpression();
		
		assertTrue(x1.toString().contains("x"));
		assertTrue(x1.toString().contains("2"));
		
		assertTrue(x2.toString().contains("x"));
		assertTrue(x2.toString().contains("y"));
		assertTrue(x2.toString().contains("p2"));
		
		assertTrue(x3.toString().contains("y"));
		assertTrue(x3.toString().contains("42"));
		
		assertEquals(0,prereqs.get(0).getNewlyBoundVariables().size());
		assertEquals(1,prereqs.get(1).getNewlyBoundVariables().size());
		assertEquals(0,prereqs.get(2).getNewlyBoundVariables().size());
		assertTrue(prereqs.get(1).getNewlyBoundVariables().contains("y"));
	}
	
	@Test
	public void test2() throws Exception {
		Rule rule = readRule("rule1: p2(x,y) & y==42 & x>2 -> p1(x);");
		List<Prereq> prereqs = Scheduler.DEFAULT.getPrerequisites(rule);
		
		for (Prereq prereq:prereqs) {
			System.out.println(prereq.getExpression());
		}
		
		assertEquals(3,prereqs.size());
		
		Expression x1 = prereqs.get(0).getExpression();
		Expression x2 = prereqs.get(1).getExpression();
		Expression x3 = prereqs.get(2).getExpression();
		
		assertTrue(x1.toString().contains("x"));
		assertTrue(x1.toString().contains("2"));
		
		assertTrue(x2.toString().contains("x"));
		assertTrue(x2.toString().contains("y"));
		assertTrue(x2.toString().contains("p2"));
		
		assertTrue(x3.toString().contains("y"));
		assertTrue(x3.toString().contains("42"));

		assertEquals(0,prereqs.get(0).getNewlyBoundVariables().size());
		assertEquals(1,prereqs.get(1).getNewlyBoundVariables().size());
		assertEquals(0,prereqs.get(2).getNewlyBoundVariables().size());
		assertTrue(prereqs.get(1).getNewlyBoundVariables().contains("y"));
	}
	
	@Test
	public void test3() throws Exception {
		Rule rule = readRule("rule1:  y==42 & x>2 & p2(x,y) -> p1(x);");
		List<Prereq> prereqs = Scheduler.DEFAULT.getPrerequisites(rule);
		
		for (Prereq prereq:prereqs) {
			System.out.println(prereq.getExpression());
		}
		
		assertEquals(3,prereqs.size());
		
		Expression x1 = prereqs.get(0).getExpression();
		Expression x2 = prereqs.get(1).getExpression();
		Expression x3 = prereqs.get(2).getExpression();
		
		assertTrue(x1.toString().contains("x"));
		assertTrue(x1.toString().contains("2"));
		
		assertTrue(x2.toString().contains("x"));
		assertTrue(x2.toString().contains("y"));
		assertTrue(x2.toString().contains("p2"));
		
		assertTrue(x3.toString().contains("y"));
		assertTrue(x3.toString().contains("42"));

		assertEquals(0,prereqs.get(0).getNewlyBoundVariables().size());
		assertEquals(1,prereqs.get(1).getNewlyBoundVariables().size());
		assertEquals(0,prereqs.get(2).getNewlyBoundVariables().size());
		assertTrue(prereqs.get(1).getNewlyBoundVariables().contains("y"));
	}
	
}
