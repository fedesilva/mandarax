/*
 * Copyright 2010 Jens Dietrich Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.gnu.org/licenses/agpl.html Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package test.org.mandarax.compiler;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mandarax.rt.ResultSet;
import test.org.mandarax.compiler.factorial.*;

/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTestsFactorial {
	
//	rel Factorial(int i,int f) queries getFactorial(i),isFactorial(i,f) {
//		rule1: -> Factorial(0,1);
//		rule2: Factorial(x-1,n) -> Factorial(x,n*x); 
//	}

	
	@Test
	public void test1() throws Exception {
		ResultSet<FactorialRel> rs = FactorialRelInstances.isFactorial(4, 24);
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<FactorialRel> rs = FactorialRelInstances.isFactorial(5, 120);
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test3() throws Exception {
		ResultSet<FactorialRel> rs = FactorialRelInstances.getFactorial(0);
		
		assertEquals(1,rs.next().f);	
		//assertFalse(rs.hasNext());
	}
	
	@Test
	public void test4() throws Exception {
		ResultSet<FactorialRel> rs = FactorialRelInstances.getFactorial(4);
		
		assertEquals(24,rs.next().f);	
		//assertFalse(rs.hasNext());
	}
	
	@Test
	public void test5() throws Exception {
		ResultSet<FactorialRel> rs = FactorialRelInstances.getFactorial(5);
		
		assertEquals(120,rs.next().f);	
		//assertFalse(rs.hasNext());
	}
	

}
