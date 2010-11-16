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

package test.org.mandarax.dsl;

import java.util.List;
import org.junit.Test;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.Variable;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import static org.junit.Assert.*;
import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Tests the interface to extract variables from expressions.
 * @author jens dietrich
 */
public class UtilTestsExtractVariables {
	
	private List<String> stringify(List<Variable> variables) throws Exception {
		return Lists.transform(variables, new Function<Variable,String>() {
			@Override
			public String apply(Variable v) {
				return v.getName();
			}});
	}
	
	@Test
	public void testExtractVars1() throws Exception {
		Expression expression = readExpression("x");
		List<String> names = stringify(expression.getVariables());
		assertEquals(1,names.size());
		assertEquals("x",names.get(0));
	}
	
	@Test
	public void testExtractVars2() throws Exception {
		Expression expression = readExpression("\"x\"");
		List<String> names = stringify(expression.getVariables());
		assertEquals(0,names.size());
	}
	
	@Test
	public void testExtractVars3() throws Exception {
		Expression expression = readExpression("x+42");
		List<String> names = stringify(expression.getVariables());
		assertEquals(1,names.size());
		assertEquals("x",names.get(0));
	}
	
	@Test
	public void testExtractVars4() throws Exception {
		Expression expression = readExpression("x+(y+3)");
		List<String> names = stringify(expression.getVariables());
		assertEquals(2,names.size());
		assertEquals("x",names.get(0));
		assertEquals("y",names.get(1));
	}
	
	@Test
	public void testExtractVars5() throws Exception {
		Expression expression = readExpression("x|(y&true)");
		List<String> names = stringify(expression.getVariables());
		assertEquals(2,names.size());
		assertEquals("x",names.get(0));
		assertEquals("y",names.get(1));
	}
	@Test
	public void testExtractVars6() throws Exception {
		Expression expression = readExpression("x.f(42+y)");
		List<String> names = stringify(expression.getVariables());
		assertEquals(2,names.size());
		assertEquals("x",names.get(0));
		assertEquals("y",names.get(1));
	}
	@Test
	public void testExtractVars7() throws Exception {
		Expression expression = readExpression("f(x*42+y)");
		List<String> names = stringify(expression.getVariables());
		assertEquals(2,names.size());
		assertEquals("x",names.get(0));
		assertEquals("y",names.get(1));
	}
	@Test
	public void testExtractVars8() throws Exception {
		Expression expression = readExpression("f(x*g(42+y))");
		List<String> names = stringify(expression.getVariables());
		assertEquals(2,names.size());
		assertEquals("x",names.get(0));
		assertEquals("y",names.get(1));
	}
	
	@Test
	public void testExtractVars9() throws Exception {
		Expression expression = readExpression("((int)x==3) | (y instanceof java.util.String)");
		List<String> names = stringify(expression.getVariables());
		assertEquals(2,names.size());
		assertEquals("x",names.get(0));
		assertEquals("y",names.get(1));
	}
}
