/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.gnu.org/licenses/agpl.html 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package test.org.mandarax.dsl;

import org.junit.Test;
import org.mandarax.dsl.*;
import static test.org.mandarax.dsl.TestUtils.*;

/**
 * Test cases for relational expressions.
 * @author jens dietrich
 */
public class ParserTestsRelationalExpressions extends AbstractTests{
	
	@Test
	public void testEQ() throws Exception {
		Expression x = readExpression("x==2");
		print(x);
		testVarOpInt(x,"x",BinOp.EQ,2);
	}
	
	@Test
	public void testNEQ() throws Exception {
		Expression x = readExpression("x!=2");
		print(x);
		testVarOpInt(x,"x",BinOp.NEQ,2);
	}
	
	@Test
	public void testLT() throws Exception {
		Expression x = readExpression("x<2");
		print(x);
		testVarOpInt(x,"x",BinOp.LT,2);
	}
	
	@Test
	public void testGT() throws Exception {
		Expression x = readExpression("x>2");
		print(x);
		testVarOpInt(x,"x",BinOp.GT,2);
	}
	
	@Test
	public void testLTE() throws Exception {
		Expression x = readExpression("x<=2");
		print(x);
		testVarOpInt(x,"x",BinOp.LTE,2);
	}

	
	@Test
	public void testGTE() throws Exception {
		Expression x = readExpression("x>=2");
		print(x);
		testVarOpInt(x,"x",BinOp.GTE,2);
	}


}
