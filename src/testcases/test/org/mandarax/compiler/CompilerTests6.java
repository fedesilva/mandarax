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
import test.org.mandarax.compiler.reldef6.DiscountRel;
import test.org.mandarax.compiler.reldef6.DiscountRelInstances;
/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTests6 {

//	object Discount goldDiscount = new Discount(20,true);
//	object Discount silverDiscount = new Discount(10,true);
//	object Discount specialDiscount = new Discount(5,false);
//	rel DiscountRel(Customer customer,Discount discount) queries getDiscount(customer),qualifiesForDiscount(customer,discount) {
//		rule1: c.turnover>1000 -> DiscountRel(c,goldDiscount);
//		rule2: FrequentCustomer(c) -> DiscountRel(c,silverDiscount);
//		rule3: c.paymentMethod == "CompanyVisa" -> DiscountRel(c,specialDiscount);
//	}
//	rel FrequentCustomer(Customer customer) queries isFrequentCustomer(customer) {
//		rule1: c.transactionCount>5 -> FrequentCustomer(c);
//		rule2: c.transactionCount>3 & c.turnover>500 -> FrequentCustomer(c);
//	}
	
	@Test
	public void test1() throws Exception {
		Customer customer = new Customer("John");
		customer.setPaymentMethod("Visa");
		customer.setTransactionCount(5);
		customer.setTurnover(1200);
		
		ResultSet<DiscountRel> rs = new DiscountRelInstances().getDiscount(customer);
		
		DiscountRel discount = rs.next(); // gold discount
		assertEquals(20,discount.discount.getValue());
		assertEquals(true,discount.discount.isRelative());
		
		discount = rs.next(); // silver
		assertEquals(10,discount.discount.getValue());
		assertEquals(true,discount.discount.isRelative());
		
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test2() throws Exception {
		Customer customer = new Customer("John");
		customer.setPaymentMethod("Visa");
		customer.setTransactionCount(6);
		customer.setTurnover(300);
		
		ResultSet<DiscountRel> rs = new DiscountRelInstances().getDiscount(customer);
		
		DiscountRel discount = rs.next(); // silver
		assertEquals(10,discount.discount.getValue());
		assertEquals(true,discount.discount.isRelative());
		
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test3() throws Exception {
		Customer customer = new Customer("John");
		customer.setPaymentMethod("CompanyVisa");
		customer.setTransactionCount(2);
		customer.setTurnover(100);
		
		ResultSet<DiscountRel> rs = new DiscountRelInstances().getDiscount(customer);
		
		DiscountRel discount = rs.next(); // silver
		assertEquals(5,discount.discount.getValue());
		assertEquals(false,discount.discount.isRelative());
		
		assertFalse(rs.hasNext());
	}

	
	@Test
	public void test4() throws Exception {
		Customer customer = new Customer("John");
		customer.setPaymentMethod("Visa");
		customer.setTransactionCount(5);
		customer.setTurnover(1200);
		
		ResultSet<DiscountRel> rs = new DiscountRelInstances().getDiscount(customer);
		
		DiscountRel discount = rs.next(); // gold discount
		assertEquals(20,discount.discount.getValue());
		assertEquals(true,discount.discount.isRelative());
		
		discount = rs.next(); // silver
		assertEquals(10,discount.discount.getValue());
		assertEquals(true,discount.discount.isRelative());
		
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test5() throws Exception {
		Customer customer = new Customer("John");
		customer.setPaymentMethod("Visa");
		customer.setTransactionCount(5);
		customer.setTurnover(1200);
		
		Discount goldDiscount = new Discount(20,true);
		
		ResultSet<DiscountRel> rs = new DiscountRelInstances().qualifiesForDiscount(customer,goldDiscount);
		assertTrue(rs.hasNext());
	}
	
	@Test
	public void test6() throws Exception {
		Customer customer = new Customer("John");
		customer.setPaymentMethod("Visa");
		customer.setTransactionCount(5);
		customer.setTurnover(1200);
		
		Discount silverDiscount = new Discount(10,true);
		
		ResultSet<DiscountRel> rs = new DiscountRelInstances().qualifiesForDiscount(customer,silverDiscount);
		assertTrue(rs.hasNext());
	}
	
	@Test
	public void test7() throws Exception {
		Customer customer = new Customer("John");
		customer.setPaymentMethod("Visa");
		customer.setTransactionCount(6);
		customer.setTurnover(300);
		
		Discount silverDiscount = new Discount(10,true);
		
		ResultSet<DiscountRel> rs = new DiscountRelInstances().qualifiesForDiscount(customer,silverDiscount);
		assertTrue(rs.hasNext());
	}
	
	
	@Test
	public void test8() throws Exception {
		Customer customer = new Customer("John");
		customer.setPaymentMethod("CompanyVisa");
		customer.setTransactionCount(3);
		customer.setTurnover(100);
		
		Discount specialDiscount = new Discount(5,false);
		
		ResultSet<DiscountRel> rs = new DiscountRelInstances().qualifiesForDiscount(customer,specialDiscount);
		assertTrue(rs.hasNext());
	}
}
