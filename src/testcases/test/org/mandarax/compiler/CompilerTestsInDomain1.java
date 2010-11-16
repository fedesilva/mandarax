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
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mandarax.rt.ResultSet;
import test.org.mandarax.compiler.indomain1.*;
/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTestsInDomain1 {

//	package test.org.mandarax.compiler.indomain1;
//	import test.org.mandarax.compiler.*;
//
//	// list of promoted methods of payments, must be set by application before querying
//	java.util.List specialCustomers = new java.util.ArrayList();
//
//	test.org.mandarax.compiler.Discount specialDiscount = new test.org.mandarax.compiler.Discount(5,false);
//
//	rel Discount(Customer customer,Discount discount) queries getAll() {
//		rule1: in(c,specialCustomers) -> Discount(c,specialDiscount);
//	}
	
	private Customer chris = null;
	private Customer dan = null;
	private Customer john = null;
	private Customer jens = null; // not special!
	private List<Customer> specialCustomers = null;
	
	@Before
	public void setup() {	
		chris = new Customer("Chris");
		dan = new Customer("Dan");
		john = new Customer("john");
		jens = new Customer("jens");
		specialCustomers = new ArrayList<Customer>();
		specialCustomers.add(chris);
		specialCustomers.add(dan);
		specialCustomers.add(john);
	}
	@After
	public void tearDown() {	
		chris = null;
		dan = null;
		john = null;
		specialCustomers = null;
	}
	@Test
	public void test1() throws Exception {		
		
		// important: must set iterable
		DiscountRelInstances.specialCustomers = specialCustomers;

		ResultSet<DiscountRel> rs = DiscountRelInstances.getAll();
		
		DiscountRel rel = rs.next();
		assertEquals(rel.customer,chris);
		assertEquals(rel.discount,DiscountRelInstances.specialDiscount);
		
		rel = rs.next();
		assertEquals(rel.customer,dan);
		assertEquals(rel.discount,DiscountRelInstances.specialDiscount);
		
		rel = rs.next();
		assertEquals(rel.customer,john);
		assertEquals(rel.discount,DiscountRelInstances.specialDiscount);
		
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test2() throws Exception {		
		
		// important: must set iterable
		DiscountRelInstances.specialCustomers = specialCustomers;

		ResultSet<DiscountRel> rs = DiscountRelInstances.getDiscount(john);
		
		DiscountRel rel = rs.next();
		assertEquals(rel.customer,john);
		assertEquals(rel.discount,DiscountRelInstances.specialDiscount);
		
		assertFalse(rs.hasNext());
	}
	
}
