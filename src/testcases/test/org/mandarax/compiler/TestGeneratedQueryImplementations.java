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


import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mandarax.compiler.impl.DefaultCompiler;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.DefaultVerificationErrorReporter;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.VerifyAll;

import static test.org.mandarax.compiler.TestUtils.*;

/**
 * Tests the generation of query implementations.
 * @author jens dietrich
 */
public class TestGeneratedQueryImplementations {
	

	@Test
	public void testGeneratedQueryImplementation1() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef1.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(0);
		StringLocation location = new StringLocation();
		DefaultCompiler compiler = new DefaultCompiler();
		List<CompilationUnit> cus = new ArrayList<CompilationUnit>();
		cus.add(cu);
		compiler.resolveFunctionRefs(cus);
		compiler.createRelationshipQueryImplementation(location, cus, cu, rel);
		
		String def = location.getGeneratedCode();
		
		System.out.println(def);
		
//		assertTrue(def.contains("package test.org.mandarax.dsl;"));
//		assertTrue(def.contains("import java.util.Date;"));
//		assertTrue(def.contains("public interface FatherInstances"));
//		assertTrue(def.contains("public ResultSet<Father> getFather (  Person child  );"));
//		assertTrue(def.contains("public ResultSet<Father> isFather (  MalePerson father ,  Person child  );"));
	}
	
	@Test
	public void testGeneratedQueryImplementation2() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef2.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(0);
		StringLocation location = new StringLocation();
		DefaultCompiler compiler = new DefaultCompiler();
		List<CompilationUnit> cus = new ArrayList<CompilationUnit>();
		cus.add(cu);
		compiler.resolveFunctionRefs(cus);
		compiler.createRelationshipQueryImplementation(location,cus, cu, rel);
		
		String def = location.getGeneratedCode();
		
		System.out.println(def);
		
//		assertTrue(def.contains("package test.org.mandarax.dsl;"));
//		assertTrue(def.contains("import java.util.Date;"));
//		assertTrue(def.contains("public interface FatherInstances"));
//		assertTrue(def.contains("public ResultSet<Father> getFather (  Person child  );"));
//		assertTrue(def.contains("public ResultSet<Father> isFather (  MalePerson father ,  Person child  );"));
	}
	
	@Test
	public void testGeneratedQueryImplementation3() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef3.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(0);
		StringLocation location = new StringLocation();
		DefaultCompiler compiler = new DefaultCompiler();
		List<CompilationUnit> cus = new ArrayList<CompilationUnit>();
		cus.add(cu);
		compiler.resolveFunctionRefs(cus);
		compiler.createRelationshipQueryImplementation(location,cus, cu, rel);
		
		String def = location.getGeneratedCode();
		
		System.out.println(def);
		
//		assertTrue(def.contains("package test.org.mandarax.dsl;"));
//		assertTrue(def.contains("import java.util.Date;"));
//		assertTrue(def.contains("public interface FatherInstances"));
//		assertTrue(def.contains("public ResultSet<Father> getFather (  Person child  );"));
//		assertTrue(def.contains("public ResultSet<Father> isFather (  MalePerson father ,  Person child  );"));
	}
	
	@Test
	public void testGeneratedQueryImplementation4() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef4.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(1);
		StringLocation location = new StringLocation();
		DefaultCompiler compiler = new DefaultCompiler();
		List<CompilationUnit> cus = new ArrayList<CompilationUnit>();
		cus.add(cu);
		new VerifyAll().verify(cus,new DefaultVerificationErrorReporter());
		
		compiler.resolveFunctionRefs(cus);
		compiler.createRelationshipQueryImplementation(location, cus, cu, rel);
		
		String def = location.getGeneratedCode();
		
		System.out.println(def);
		
//		assertTrue(def.contains("package test.org.mandarax.dsl;"));
//		assertTrue(def.contains("import java.util.Date;"));
//		assertTrue(def.contains("public interface FatherInstances"));
//		assertTrue(def.contains("public ResultSet<Father> getFather (  Person child  );"));
//		assertTrue(def.contains("public ResultSet<Father> isFather (  MalePerson father ,  Person child  );"));
	}
}
