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
import static test.org.mandarax.dsl.TestUtils.readRelationshipDefinition;
import java.util.List;
import org.junit.Test;
import org.mandarax.compiler.CompilerException;
import org.mandarax.compiler.impl.Prereq;
import org.mandarax.compiler.impl.Scheduler;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.Rule;
import org.mandarax.dsl.util.DefaultResolver;

/**
 * Tests for scheduler.
 * @author jens dietrich
 */
public class TestScheduler {
	@Test
	public void test1() throws Exception {
		RelationshipDefinition rel = readRelationshipDefinition("rel p1(int value) queries query1(value){\nrule1: x>2 & p2(x,y) & y==42 -> p1(x);\n}");
		Rule rule = rel.getRules().get(0);
		List<Prereq> prereqs = new Scheduler(new DefaultResolver(),rule,rel.getQueries().get(0)).getPrerequisites();
		
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
		assertTrue(toStrings(prereqs.get(1).getNewlyBoundVariables()).contains("y"));
	}
	
	@Test
	public void test2() throws Exception {
		RelationshipDefinition rel = readRelationshipDefinition("rel p1(int value) queries query1(value){\nrule1: p2(x,y) & y==42 & x>2 -> p1(x);\n}");
		Rule rule = rel.getRules().get(0);
		
		List<Prereq> prereqs =  new Scheduler(new DefaultResolver(),rule,rel.getQueries().get(0)).getPrerequisites();
		
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
		assertTrue(toStrings(prereqs.get(1).getNewlyBoundVariables()).contains("y"));
	}
	
	@Test
	public void test3() throws Exception {
		
		RelationshipDefinition rel = readRelationshipDefinition("rel p1(int value) queries query1(value){\nrule1:  y==42 & x>2 & p2(x,y) -> p1(x);\n}");
		Rule rule = rel.getRules().get(0);
		
		List<Prereq> prereqs =  new Scheduler(new DefaultResolver(),rule,rel.getQueries().get(0)).getPrerequisites();
		
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
		assertTrue(toStrings(prereqs.get(1).getNewlyBoundVariables()).contains("y"));
	}
	
	// issue8/case1
	@Test(expected=CompilerException.class)
	public void test4() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef9.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(1);
		assertEquals("GrandFather",rel.getName());
		Rule rule = rel.getRules().get(0);
		List<Prereq> prereqs =  new Scheduler(new DefaultResolver(),rule,rel.getQueries().get(0)).getPrerequisites();
		
		for (Prereq prereq:prereqs) {
			System.out.println(prereq.getExpression());
		}
	}
	
	// issue8/case2
	@Test
	public void test5() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef10.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(1);
		assertEquals("GrandFather",rel.getName());
		Rule rule = rel.getRules().get(0);
		List<Prereq> prereqs =  new Scheduler(new DefaultResolver(),rule,rel.getQueries().get(0)).getPrerequisites();
		
		for (Prereq prereq:prereqs) {
			System.out.println(prereq.getExpression());
		}
		
		assertEquals(2,prereqs.size());
		
		Expression x1 = prereqs.get(0).getExpression();
		Expression x2 = prereqs.get(1).getExpression();
		
		assertEquals(1,prereqs.get(0).getNewlyBoundVariables().size());
		assertEquals(0,prereqs.get(1).getNewlyBoundVariables().size());
		
		assertTrue(x1.toString().contains("x.name"));
		assertTrue(x1.toString().contains("__t0"));
		
		assertTrue(x2.toString().contains("z.name"));
		assertTrue(x2.toString().contains("__t0"));
	}
	
	// issue8/case3
	@Test
	public void test6() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef11.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(1);
		assertEquals("GrandFather",rel.getName());
		Rule rule = rel.getRules().get(0);
		List<Prereq> prereqs =  new Scheduler(new DefaultResolver(),rule,rel.getQueries().get(0)).getPrerequisites();
		
		for (Prereq prereq:prereqs) {
			System.out.println(prereq.getExpression());
		}
		
		assertEquals(2,prereqs.size());
		
		Expression x1 = prereqs.get(0).getExpression();
		Expression x2 = prereqs.get(1).getExpression();
		
		assertEquals(2,prereqs.get(0).getNewlyBoundVariables().size());
		assertEquals(1,prereqs.get(1).getNewlyBoundVariables().size());
		
		assertTrue(x1.toString().contains("x"));
		assertTrue(x1.toString().contains("y"));
		
		assertTrue(x2.toString().contains("y"));
		assertTrue(x2.toString().contains("z"));
	}
	
	// issue8/case4
	@Test
	public void test7() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef12.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(1);
		assertEquals("GrandFather",rel.getName());
		Rule rule = rel.getRules().get(0);
		List<Prereq> prereqs =  new Scheduler(new DefaultResolver(),rule,rel.getQueries().get(0)).getPrerequisites();
		
		for (Prereq prereq:prereqs) {
			System.out.println(prereq.getExpression());
		}
		
		assertEquals(3,prereqs.size());
		
		Expression x1 = prereqs.get(0).getExpression();
		Expression x2 = prereqs.get(1).getExpression();
		Expression x3 = prereqs.get(2).getExpression();
		
		assertEquals(2,prereqs.get(0).getNewlyBoundVariables().size());
		assertEquals(0,prereqs.get(1).getNewlyBoundVariables().size());
		assertEquals(1,prereqs.get(2).getNewlyBoundVariables().size());
		
		assertTrue(x1.toString().contains("x"));
		assertTrue(x1.toString().contains("y"));
		
		assertTrue(x2.toString().contains("x"));
		assertTrue(x2.toString().contains("__t0"));
		
		assertTrue(x3.toString().contains("z"));
		assertTrue(x3.toString().contains("y"));
	}
}
