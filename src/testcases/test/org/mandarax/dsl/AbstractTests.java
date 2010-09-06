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

import org.mandarax.dsl.Expression;
import org.mandarax.dsl.Rule;
import org.mandarax.dsl.util.ExpressionStructurePrinter;

/**
 * Abstract superclass for test cases.
 * @author jens dietrich
 */
abstract class AbstractTests {
	
	private boolean DEBUG_MODE = true;

	protected void print(Expression expression) {
		if (DEBUG_MODE) {
			System.out.println("-- starts --");
			System.out.println(expression);
			ExpressionStructurePrinter printer = new ExpressionStructurePrinter();
			printer.println();
			//expression.accept(printer);
			System.out.println("-- ends --");
			System.out.println();
		}
	}
	
	protected void print(Rule rule) {
		if (DEBUG_MODE) {
			System.out.println(rule);
		}		
	}

}
