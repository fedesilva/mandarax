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
import org.mandarax.compiler.impl.DefaultCompiler;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.RelationshipDefinition;
import static test.org.mandarax.compiler.TestUtils.*;

/**
 * Tests the generation of relationship types.
 * @author jens dietrich
 */
public class TestGeneratedRelationshipTypes {
	

	

	@Test
	public void testGeneratedRelationshipTypes1() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef1.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(0);
		StringLocation location = new StringLocation();
		DefaultCompiler compiler = new DefaultCompiler();
		compiler.createRelationshipType(location, cu, rel);
		
		String def = location.getGeneratedCode();
		
		System.out.println(def);
		
		assertTrue(def.contains("package test.org.mandarax.compiler;"));
		assertTrue(def.contains("public class Father"));
		assertTrue(def.contains("public MalePerson father = null; "));
		assertTrue(def.contains("public Person child = null;"));
	}
	

}
