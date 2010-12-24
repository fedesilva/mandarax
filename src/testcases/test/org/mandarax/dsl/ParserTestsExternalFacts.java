/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.gnu.org/licenses/agpl.html 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package test.org.mandarax.dsl;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import org.mandarax.dsl.*;

import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Test cases for external facts.
 * @author jens dietrich
 */ 
public class ParserTestsExternalFacts extends AbstractTests{
	
	@Test
	public void testExternalFacts1() throws Exception {
		ExternalFacts facts = readExternalFacts("facts1: include new FactStore();");
		print(facts);
		assertEquals("facts1",facts.getId());
		Expression def = facts.getIterable();
		assertTrue(def instanceof ConstructorInvocation);
		ConstructorInvocation ci = (ConstructorInvocation)def;
		assertEquals("FactStore",ci.getTypeName());
		assertEquals(0,ci.getParameters().size());
	}
	
	@Test
	public void testExternalFacts2() throws Exception {
		ExternalFacts facts = readExternalFacts("facts1: include factstore1;");
		print(facts);
		assertEquals("facts1",facts.getId());
		Expression def = facts.getIterable();
		assertTrue(def instanceof Variable);
		Variable var = (Variable)def;
		assertEquals("factstore1",var.getName());
	}
	
}
