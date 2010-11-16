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
import test.org.mandarax.compiler.reldefStaticMethods1.*;

/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTestsStaticMethods1 {
	
//	rel Factorial(int n,int fact) queries isFactorial(n,fact) {
//		rule1: y==factorial(x) -> Factorial(x,y);
//	}
	
	@Test
	public void test1() throws Exception {
		ResultSet<FactorialRel> rs = FactorialRelInstances.isFactorial(5,120);
		assertTrue(rs.hasNext());
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<FactorialRel> rs = FactorialRelInstances.isFactorial(5,121);
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test3() throws Exception {
		ResultSet<FactorialRel> rs = FactorialRelInstances.isFactorial(4,120);
		assertFalse(rs.hasNext());
	}
	
	
	
}
