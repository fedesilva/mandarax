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

import test.org.mandarax.compiler.reldef4.GrandFather;
import test.org.mandarax.compiler.reldef4.GrandFatherInstances;
/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTests4 {
	@Test
	public void test4a() throws Exception {
		ResultSet<GrandFather> rs = new GrandFatherInstances().getAll();
		
		GrandFather gf = rs.next();
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
	public void test4b() throws Exception {
		ResultSet<GrandFather> rs = new GrandFatherInstances().getGrandChildren("Klaus");
		
		GrandFather gf = rs.next();
		assertEquals("Max",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		gf = rs.next();
		assertEquals("Xiomara",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		assertFalse(rs.hasNext());

	}
	
	@Test
	public void test4c() throws Exception {
		ResultSet<GrandFather> rs = new GrandFatherInstances().getGrandFather("Max");
		
		GrandFather gf = rs.next();
		assertEquals("Max",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		assertFalse(rs.hasNext());

	}
	
	@Test
	public void test5c() throws Exception {
		ResultSet<GrandFather> rs = new GrandFatherInstances().isGrandFather("Klaus","Max");
		
		GrandFather gf = rs.next();
		assertEquals("Max",gf.grandChild);
		assertEquals("Klaus",gf.grandFather);
		
		assertFalse(rs.hasNext());

	}
	
	@Test
	public void test5d() throws Exception {
		ResultSet<GrandFather> rs = new GrandFatherInstances().isGrandFather("Klaus","Jens");
		
		assertFalse(rs.hasNext());

	}
	
}
