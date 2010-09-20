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
import org.mandarax.compiler.impl.DefaultCompiler;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.RelationshipDefinition;
import static test.org.mandarax.compiler.TestUtils.*;

/**
 * Tests the generation of query implementations.
 * @author jens dietrich
 */
public class TestGeneratedQueryImplementations {
	

	@Test
	public void testGeneratedQueryInterface1() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef1.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(0);
		StringLocation location = new StringLocation();
		DefaultCompiler compiler = new DefaultCompiler();
		compiler.createRelationshipQueryImplementation(location, cu, rel);
		
		String def = location.getGeneratedCode();
		
		System.out.println(def);
		
//		assertTrue(def.contains("package test.org.mandarax.dsl;"));
//		assertTrue(def.contains("import java.util.Date;"));
//		assertTrue(def.contains("public interface FatherInstances"));
//		assertTrue(def.contains("public ResultSet<Father> getFather (  Person child  );"));
//		assertTrue(def.contains("public ResultSet<Father> isFather (  MalePerson father ,  Person child  );"));
	}
	
	@Test
	public void testGeneratedQueryInterface2() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef2.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(0);
		StringLocation location = new StringLocation();
		DefaultCompiler compiler = new DefaultCompiler();
		compiler.createRelationshipQueryImplementation(location, cu, rel);
		
		String def = location.getGeneratedCode();
		
		System.out.println(def);
		
//		assertTrue(def.contains("package test.org.mandarax.dsl;"));
//		assertTrue(def.contains("import java.util.Date;"));
//		assertTrue(def.contains("public interface FatherInstances"));
//		assertTrue(def.contains("public ResultSet<Father> getFather (  Person child  );"));
//		assertTrue(def.contains("public ResultSet<Father> isFather (  MalePerson father ,  Person child  );"));
	}
}
