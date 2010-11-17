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
import test.org.mandarax.compiler.reldef14.*;

/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTests14 {

//	rel HasName(Person person,String name) queries getName(person) {
//		rule1: -> HasName(p,p.name);
//	}

	
	@Test
	public void test1() throws Exception {
		ResultSet<HasNameRel> rs = HasNameRelInstances.getName(new Person("Jens"));
		assertTrue(rs.hasNext());	
		assertEquals("Jens",rs.next().name);
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<HasNameRel> rs = HasNameRelInstances.hasName(new Person("Jens"),"Jens");
		assertTrue(rs.hasNext());
		rs.next();
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test3() throws Exception {
		ResultSet<HasNameRel> rs = HasNameRelInstances.hasName(new Person("Jens"),"Max");
		assertFalse(rs.hasNext());
	}
	


	
}
