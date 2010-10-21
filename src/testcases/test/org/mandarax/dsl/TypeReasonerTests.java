/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package test.org.mandarax.dsl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mandarax.dsl.Context;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.Variable;
import org.mandarax.dsl.util.AbstractTypeReasoner;
import org.mandarax.dsl.util.DefaultResolver;
import org.mandarax.dsl.util.Resolver;
import org.mandarax.dsl.util.ResolverException;
import org.mandarax.dsl.util.TypeReasoner;
import org.mandarax.dsl.util.TypeReasoningException;
import static org.junit.Assert.*;
import static test.org.mandarax.dsl.TestUtils.*;

public class TypeReasonerTests {
	
	class TestTypeReasoner extends AbstractTypeReasoner {
		Map<String,Class> varTypes = null;
		public TestTypeReasoner(Map<String, Class> varTypes) {
			super();
			this.varTypes = varTypes;
		}
		@Override
		public Class doGetType(Variable expression, Resolver resolver,Collection<RelationshipDefinition> rels) throws TypeReasoningException {
			Class clazz = varTypes.get(expression.getName());
			return clazz;
		}
	}
	
	class TestResolver extends DefaultResolver {
		@Override
		public Method getFunction(Context context,String name, String... paramTypeNames) throws ResolverException {
			return null;
		}
	}
	
	private void testType(String expressionDef,Class expectedType,Map<String,Class> varTypes) throws Exception {
		Expression expression = readExpression(expressionDef);
		TypeReasoner typeReasoner = new TestTypeReasoner(varTypes);
		Resolver resolver = new TestResolver();
		Class computedType = typeReasoner.getType(expression, resolver,new ArrayList<RelationshipDefinition>());
		assertEquals(expectedType,computedType);
	}
	@Test
	public void testBinaryExpressions1() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		varTypes.put("y",Byte.class);
		testType(expression,Integer.class,varTypes);
	}
	
	@Test(expected=TypeReasoningException.class) // wont work, y not bound
	public void testBinaryExpressions2() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		//varTypes.put("y",Byte.class);
		testType(expression,Integer.class,varTypes);
	}
	
	@Test(expected=TypeReasoningException.class) // wont work, x not bound
	public void testBinaryExpressions3() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		//varTypes.put("x",Integer.class);
		varTypes.put("y",Byte.class);
		testType(expression,Integer.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions4() throws Exception {
		String expression = "x*y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Character.class);
		varTypes.put("y",Byte.class);
		testType(expression,Integer.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions5() throws Exception {
		String expression = "x%y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		varTypes.put("y",Long.class);
		testType(expression,Long.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions6() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Long.class);
		varTypes.put("y",Long.class);
		testType(expression,Long.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions7() throws Exception {
		String expression = "x+y+z";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		varTypes.put("y",Long.class);
		varTypes.put("z",Integer.class);
		testType(expression,Long.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions8() throws Exception {
		String expression = "(x+y)*(z+4)";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		varTypes.put("y",Integer.class);
		varTypes.put("z",Long.class);
		testType(expression,Long.class,varTypes);
	}
	
	
	@Test
	public void testBinaryExpressions9() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Double.class);
		varTypes.put("y",Integer.class);
		testType(expression,Double.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions10() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Double.class);
		varTypes.put("y",Float.class);
		testType(expression,Double.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions11() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Float.class);
		varTypes.put("y",Float.class);
		testType(expression,Float.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions12() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",Object.class);
		testType(expression,String.class,varTypes);
	}
	// only works if first argument is string
	@Test(expected=TypeReasoningException.class) 
	public void testBinaryExpressions13() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Object.class);
		varTypes.put("y",String.class);
		testType(expression,String.class,varTypes);
	}
	
	// * not supported for strings
	@Test(expected=TypeReasoningException.class) 
	public void testBinaryExpressions14() throws Exception {
		String expression = "x*y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",String.class);
		testType(expression,String.class,varTypes);
	}
	@Test
	public void testConditions1() throws Exception {
		String expression = "x==y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",Exception.class);
		testType(expression,Boolean.class,varTypes);
	}
	@Test
	public void testConditions2() throws Exception {
		String expression = "x<y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",Exception.class);
		testType(expression,Boolean.class,varTypes);
	}
	@Test
	public void testConditions3() throws Exception {
		String expression = "x>y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",Exception.class);
		testType(expression,Boolean.class,varTypes);
	}
}
