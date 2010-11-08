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
import test.org.mandarax.compiler.naf1.*;

/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTestsNAF1 {
	
//	package test.org.mandarax.compiler.reldef14;
//	import test.org.mandarax.compiler.*;

//	Person m1 = new Person("m1",25);
//	Person m2 = new Person("m2",5);
//	Person m3 = new Person("m3",5);
//	Person f1 = new Person("f1",25);
//	rel Father(Person father,Person child) queries getFather(child),isFather(father,child),getChildren(father),getFatherAndChild() {
//		rule1: -> Father(m1,m2);
//	}
//	rel Mother(Person mother,Person child) queries getMother(child),isMother(mother,child),getChildren(mother),getMotherAndChild() {
//	}
//	rel Orphan(Person person) queries isOrphan(person){
//		rule1: isYoung(p) & !Father(f,p) & !Mother(m,p)-> Orphan(p); 	
//	}
//	rel SemiOrphan(Person person) queries isSemiOrphan(person){
//		rule1: isYoung(p) & Father(f,p) & !Mother(m,p)-> SemiOrphan(p); 
//		rule2: isYoung(p) & !Father(f,p) & Mother(m,p)-> SemiOrphan(p); 	
//	}
//
//	rel isYoung(Person person) queries isYoung(person){
//		rule1: p.age<=18 -> isYoung(p); 	
//	}

	Person m1 = new Person("m1",25);
	Person m2 = new Person("m2",5);
	Person m3 = new Person("m3",5);
	Person f1 = new Person("f1",25);
	
	@Test
	public void test1() throws Exception {
		ResultSet<OrphanRel> rs = new OrphanRelInstances().isOrphan(m3);
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<OrphanRel> rs = new OrphanRelInstances().isOrphan(m2);
		assertFalse(rs.hasNext());	 // has father !
	}

	@Test
	public void test3() throws Exception {
		ResultSet<OrphanRel> rs = new OrphanRelInstances().isOrphan(m1);
		assertFalse(rs.hasNext());	 //too old
	}
	
	@Test
	public void test4() throws Exception {
		ResultSet<SemiOrphanRel> rs = new SemiOrphanRelInstances().isSemiOrphan(m3);
		assertFalse(rs.hasNext());	 // is full orphan
	}
	
	@Test
	public void test5() throws Exception {
		ResultSet<SemiOrphanRel> rs = new SemiOrphanRelInstances().isSemiOrphan(m2);
		assertTrue(rs.hasNext());	
	}

	
}
