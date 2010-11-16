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
import org.junit.Test;
import org.mandarax.rt.ResultSet;
import test.org.mandarax.compiler.rlist.*;

/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTestsRList {
	
//	rel Factorial(int i,int f) queries getFactorial(i),isFactorial(i,f) {
//		rule1: -> Factorial(0,1);
//		rule2: Factorial(x-1,n) -> Factorial(x,n*x); 
//	}

	private RList list = new RList("one",new RList("two",new RList("three",new RList("four"))));
	
	@Test
	public void test1() throws Exception {	
		ResultSet<ContainsRel> rs = ContainsRelInstances.contains(list,"one");
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<ContainsRel> rs = ContainsRelInstances.contains(list,"two");
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test3() throws Exception {
		ResultSet<ContainsRel> rs = ContainsRelInstances.contains(list,"three");
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test4() throws Exception {
		ResultSet<ContainsRel> rs = ContainsRelInstances.contains(list,"four");
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test5() throws Exception {
		ResultSet<ContainsRel> rs = ContainsRelInstances.contains(list,"five");
		assertFalse(rs.hasNext());	
	}
	
	@Test
	public void test6() throws Exception {	
		ResultSet<ContainsRel> rs = ContainsRelInstances.getElements(list);
		assertEquals("one",rs.next().element);
		assertEquals("two",rs.next().element);	
		assertEquals("three",rs.next().element);	
		assertEquals("four",rs.next().element);
		assertFalse(rs.hasNext());
	}
	

}
