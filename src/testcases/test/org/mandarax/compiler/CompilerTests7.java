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

import java.util.List;
import org.junit.Test;
import org.mandarax.rt.DerivationLogEntry;
import org.mandarax.rt.ResultSet;
import test.org.mandarax.compiler.reldef7.DiscountRel;
import test.org.mandarax.compiler.reldef7.DiscountRelInstances;
/**
 * Test cases using generated code.
 * Tests reflection.
 * @author jens dietrich
 */
public class CompilerTests7 {

//	object Discount goldDiscount = new Discount(20,true);
//	object Discount silverDiscount = new Discount(10,true);
//	object Discount specialDiscount = new Discount(5,false);
//	@author="jens"
//	@lastupdated="26/10/10"
//	rel DiscountRel(Customer customer,Discount discount) queries getDiscount(customer),qualifiesForDiscount(customer,discount) {
//		@lastupdated="27/10/10"
//		@name="golden rule"
//		rule1: c.turnover>1000 -> DiscountRel(c,goldDiscount);
//		@name="silver, second rule"
//		rule2: FrequentCustomer(c) -> DiscountRel(c,silverDiscount);
//		@name="special rule"
//		rule3: c.paymentMethod == "CompanyVisa" -> DiscountRel(c,specialDiscount);
//	}
//	@author="jens"
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
		
		List<DerivationLogEntry> derivation = rs.getDerivationLog();
		assertEquals(1,derivation.size());
		assertEquals("DiscountRel.rule1",derivation.get(0).getName());
		
		assertEquals(3,derivation.get(0).getAnnotationKeys().size()); // lastupdated is overridden
		assertEquals("golden rule",derivation.get(0).getAnnotation("name"));
		assertEquals("27/10/10",derivation.get(0).getAnnotation("lastupdated"));
		assertEquals("jens",derivation.get(0).getAnnotation("author"));
		
		discount = rs.next(); // silver
		assertEquals(10,discount.discount.getValue());
		assertEquals(true,discount.discount.isRelative());
		
		derivation = rs.getDerivationLog();
		assertEquals(2,derivation.size());
		assertEquals("DiscountRel.rule2",derivation.get(0).getName());
		assertEquals("FrequentCustomer.rule2",derivation.get(1).getName());
		
		assertEquals(3,derivation.get(0).getAnnotationKeys().size()); // lastupdated is overridden
		assertEquals("\"silver\" rule",derivation.get(0).getAnnotation("name"));
		assertEquals("26/10/10",derivation.get(0).getAnnotation("lastupdated"));
		assertEquals("jens",derivation.get(0).getAnnotation("author"));
		
		
		assertFalse(rs.hasNext());
	}
	
	
}
