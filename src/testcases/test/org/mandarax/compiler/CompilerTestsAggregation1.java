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
import test.org.mandarax.compiler.aggregation1.*;
/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTestsAggregation1 {
	
//	rel Transaction(String customer,int value) queries getTransactions(customer),getAll() {
//		fact1: -> Transaction("Jens",42);
//		fact2: -> Transaction("Jens",80);
//		fact3: -> Transaction("Jens",99);
//		fact4: -> Transaction("Max",16);
//		fact5: -> Transaction("Max",20);
//		fact6: -> Transaction("Max",3);
//		fact7: -> Transaction("Yadi",1200);
//	}
//
//	rel Discount(String customer,int discount) queries getDiscount(customer) {
//		rule1: (max d in Transaction(c,d)) > 1000 -> Discount(c,30);
//		rule2: (avg d in Transaction(c,d)) > 50 -> Discount(c,20);
//		rule3: (sum d in Transaction(c,d)) > 30 -> Discount(c,10);
//		rule4: 2< (count d in Transaction(c,d)) -> Discount(c,5);
//	}
	
	@Test
	public void test1() throws Exception {
		ResultSet<DiscountRel> rs = DiscountRelInstances.getDiscount("Jens");
		
		assertTrue(rs.hasNext());
		assertEquals(20,rs.next().discount); // rule2
		assertTrue(rs.hasNext());
		assertEquals(10,rs.next().discount); // rule3
		assertTrue(rs.hasNext());
		assertEquals(5,rs.next().discount); // rule4
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<DiscountRel> rs = DiscountRelInstances.getDiscount("Max");
		
		assertTrue(rs.hasNext());
		assertEquals(10,rs.next().discount); // rule3
		assertTrue(rs.hasNext());
		assertEquals(5,rs.next().discount); // rule4
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test3() throws Exception {
		ResultSet<DiscountRel> rs = DiscountRelInstances.getDiscount("Yadi");
		
		assertTrue(rs.hasNext());
		assertEquals(30,rs.next().discount); // rule1
		assertTrue(rs.hasNext());
		assertEquals(20,rs.next().discount); // rule2
		assertTrue(rs.hasNext());
		assertEquals(10,rs.next().discount); // rule3
		assertFalse(rs.hasNext());
	}
	

	
}
