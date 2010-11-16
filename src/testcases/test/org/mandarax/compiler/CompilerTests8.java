/*
 * Copyright 2010 Jens Dietrich Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.gnu.org/licenses/agpl.html Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package test.org.mandarax.compiler;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mandarax.rt.ResultSet;
import test.org.mandarax.compiler.reldef8.*;
/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTests8 {
	
//	rel Father(String father,String child) extends Related queries getFather(child),isFather(father,child),getChildren(father),getFatherAndChild() {
//		rule1: -> Father("Jens","Max");
//		rule2: -> Father("Jens","Xiomara");
//		rule3: -> Father("Klaus","Jens");
//		rule4: -> Father("Otto","Klaus");
//	}
//	rel GrandFather(String grandFather,String grandChild) extends Related queries getGrandChildren(grandFather),getGrandFather(grandChild),isGrandFather(grandFather,grandChild),getAll(){
//		rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);	
//	}
//	rel Related(String person1,String person2) queries getRelated1(person1),getRelated2(person2),isRelated(person1,person2),getAll() {
//		// one explicit fact
//		fact1: -> Related("John","Tom");
//	}
	
	@Test
	public void test1() throws Exception {
		ResultSet<RelatedRel> rs = RelatedRelInstances.getAll();
		assertTrue(contains(rs,"John","Tom"));
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<RelatedRel> rs = RelatedRelInstances.getAll();
		assertTrue(contains(rs,"Jens","Max"));
	}
	
	@Test
	public void test3() throws Exception {
		ResultSet<RelatedRel> rs = RelatedRelInstances.getAll();
		assertTrue(contains(rs,"Klaus","Jens"));
	}
	
	@Test
	public void test4() throws Exception {
		ResultSet<RelatedRel> rs = RelatedRelInstances.getAll();
		assertTrue(contains(rs,"Klaus","Max"));
	}
	@Test
	public void test5() throws Exception {
		ResultSet<RelatedRel> rs = RelatedRelInstances.getAll();
		assertTrue(contains(rs,"Otto","Jens"));
	}
	

	private boolean contains(ResultSet<RelatedRel> rs, String p1, String p2) {
		while (rs.hasNext()) {
			RelatedRel rel = rs.next();
			if (rel.person1.equals(p1) && rel.person2.equals(p2)) {
				rs.close();
				return true;
			}
		}
		rs.close();
		return false;
	}

	
}
