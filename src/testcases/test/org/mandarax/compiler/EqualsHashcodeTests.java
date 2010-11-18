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
import test.org.mandarax.compiler.reldef4.*;

/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class EqualsHashcodeTests {
	
	
	@Test
	public void test1() throws Exception {
		FatherRel rel1 = new FatherRel("Jens","Max");
		FatherRel rel2 = new FatherRel("Jens","Max");
		assertEquals(rel1,rel2);
		assertEquals(rel1.hashCode(),rel2.hashCode());
	}
	
	@Test
	public void test2() throws Exception {
		FatherRel rel1 = new FatherRel("Jens","Max");
		FatherRel rel2 = new FatherRel("Jen","Max");
		assertNotSame(rel1, rel2);
		assertNotSame(rel1.hashCode(),rel2.hashCode());
	}
	
	
	
}
