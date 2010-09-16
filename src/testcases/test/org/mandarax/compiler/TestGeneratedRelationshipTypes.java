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
import static test.org.mandarax.dsl.TestUtils.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import org.mandarax.compiler.CompilerException;
import org.mandarax.compiler.Location;
import org.mandarax.compiler.impl.DefaultCompiler;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.parser.ScriptReader;
import test.org.mandarax.dsl.TestUtils;

/**
 * Tests the generation of relationship types.
 * @author jens dietrich
 */
public class TestGeneratedRelationshipTypes {
	
	class StringLocation implements Location {

		private StringWriter out = new StringWriter();
		@Override
		public Writer getSrcOut(String c) throws CompilerException {
			return out;
		}

		@Override
		public OutputStream getResourceOut(String p, String r) throws CompilerException {
			throw new UnsupportedOperationException();
		}
		
		public String getGeneratedCode() {
			return out.toString();
		}

		
	}
	
	public static CompilationUnit readCUFromCP(String name) throws Exception {
		InputStream in = TestUtils.class.getResourceAsStream("/test/org/mandarax/compiler/"+name);
		CompilationUnit cu = new ScriptReader().readCompilationUnit(in);
		in.close();
		return cu;
	}

	@Test
	public void test1() throws Exception {
		CompilationUnit cu = readCUFromCP("reldef1.rel");
		RelationshipDefinition rel = cu.getRelationshipDefinitions().get(0);
		StringLocation location = new StringLocation();
		DefaultCompiler compiler = new DefaultCompiler();
		compiler.createRelationshipType(location, cu, rel);
		
		String def = location.getGeneratedCode();
		
		System.out.println(def);
		
		assertTrue(def.contains("package test.org.mandarax.dsl;"));
		assertTrue(def.contains("public class Father"));
		assertTrue(def.contains("public MalePerson father = null; "));
		assertTrue(def.contains("public Person child = null;"));
	}
}
