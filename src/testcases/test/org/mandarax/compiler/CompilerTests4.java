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
import test.org.mandarax.compiler.reldef4.*;
/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTests4 {
	@Test
	public void test1() throws Exception {
		ResultSet<GrandFatherRel> rs = new GrandFatherRelInstances().getAll();
		
		GrandFatherRel gf = rs.next();
		assertEquals("Max",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		gf = rs.next();
		assertEquals("Xiomara",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		gf = rs.next();
		assertEquals("Jens",gf.grandChild);
		assertEquals("Otto",gf.grandFather);
		
		assertFalse(rs.hasNext());
			
		
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<GrandFatherRel> rs = new GrandFatherRelInstances().getGrandChildren("Klaus");
		
		GrandFatherRel gf = rs.next();
		assertEquals("Max",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		gf = rs.next();
		assertEquals("Xiomara",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		assertFalse(rs.hasNext());

	}
	
	@Test
	public void test3() throws Exception {
		ResultSet<GrandFatherRel> rs = new GrandFatherRelInstances().getGrandFather("Max");
		
		GrandFatherRel gf = rs.next();
		assertEquals("Max",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		assertFalse(rs.hasNext());

	}
	
	@Test
	public void test4() throws Exception {
		ResultSet<GrandFatherRel> rs = new GrandFatherRelInstances().isGrandFather("Klaus","Max");
		
		GrandFatherRel gf = rs.next();
		assertEquals("Max",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		assertFalse(rs.hasNext());

	}
	
	@Test
	public void test5() throws Exception {
		ResultSet<GrandFatherRel> rs = new GrandFatherRelInstances().isGrandFather("Klaus","Jens");
		
		assertFalse(rs.hasNext());

	}
	
	@Test 
	public void test6() throws Exception {
		ResultSet<FatherRel> rs = new FatherRelInstances().isFather("Klaus","Jens");
		assertTrue(rs.hasNext());
	}
	
	@Test 
	public void test7() throws Exception {
		ResultSet<FatherRel> rs = new FatherRelInstances().isFather("Jens","Max");
		assertTrue(rs.hasNext());
	}
	
	@Test 
	public void test8() throws Exception {
		ResultSet<FatherRel> rs = new FatherRelInstances().isFather("Max","Jens");
		assertFalse(rs.hasNext());
	}
	
	@Test 
	public void test9() throws Exception {
		ResultSet<FatherRel> rs = new FatherRelInstances().getFatherAndChild();
		int c = 0;
		while (rs.hasNext()) {
			FatherRel f = rs.next();
			c = c+1;
			System.out.println(f);
		}
		assertFalse(rs.hasNext());
		assertEquals(4,c);
	}
	
}
