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
import test.org.mandarax.compiler.reldef5.GrandFatherRel;
import test.org.mandarax.compiler.reldef5.GrandFatherRelInstances;
/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTests5 {
	

	
	@Test
	public void test1() throws Exception {
		Person max = new Person("Max");
		Person klaus = new Person("Klaus");
		ResultSet<GrandFatherRel> rs = new GrandFatherRelInstances().isGrandFather(klaus,max);
		
		GrandFatherRel gf = rs.next();
		assertEquals(max,gf.grandChild);
		assertEquals(klaus,gf.grandFather);
		
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<GrandFatherRel> rs = new GrandFatherRelInstances().isGrandFather(new Person("Jens"),new Person("Max"));
		assertFalse(rs.hasNext());
	}

	@Test
	public void test3() throws Exception {
		ResultSet<GrandFatherRel> rs = new GrandFatherRelInstances().isGrandFather(new Person("Klaus"),new Person("Jens"));
		assertFalse(rs.hasNext());
	}
	
}
