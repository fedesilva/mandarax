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
import test.org.mandarax.compiler.reldef10.*;

/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTests10 {
	
//	rel Father(String father,String child) queries getFather(child),isFather(father,child),getChildren(father),getFatherAndChild() {
//		rule1: -> Father("Jens","Max");
//		rule2: -> Father("Jens","Xiomara");
//		rule3: -> Father("Klaus","Jens");
//		rule4: -> Father("Otto","Klaus");
//	}
//	rel GrandFather(MalePerson grandFather,MalePerson grandChild) queries isGrandFather(grandFather,grandChild){
//		// should introduce new variable for y.name
//		rule1: Father(x.name,y.name) & Father(y.name,z.name) -> GrandFather(x,z); 	
//	}
	
	@Test
	public void test1() throws Exception {
		MalePerson max = new MalePerson("Max");
		MalePerson klaus = new MalePerson("Klaus");
		ResultSet<GrandFatherRel> rs = GrandFatherRelInstances.isGrandFather(klaus, max);
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test2() throws Exception {
		MalePerson jens = new MalePerson("Jens");
		MalePerson otto = new MalePerson("Otto");
		ResultSet<GrandFatherRel> rs = GrandFatherRelInstances.isGrandFather(otto, jens);
		assertTrue(rs.hasNext());	
	}
	
	@Test
	public void test3() throws Exception {
		MalePerson jens = new MalePerson("Jens");
		MalePerson otto = new MalePerson("Otto");
		ResultSet<GrandFatherRel> rs = GrandFatherRelInstances.isGrandFather(jens, otto);
		assertFalse(rs.hasNext());	
	}
	
	@Test
	public void test4() throws Exception {
		MalePerson max = new MalePerson("Max");
		MalePerson jens = new MalePerson("Jens");
		ResultSet<GrandFatherRel> rs = GrandFatherRelInstances.isGrandFather(jens, max);
		assertFalse(rs.hasNext());	
	}

	
}
