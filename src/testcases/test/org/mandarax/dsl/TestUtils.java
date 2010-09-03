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

package test.org.mandarax.dsl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.ImportDeclaration;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.parser.ScriptReader;

/**
 * Test utilities.
 * @author jens dietrich
 */
public class TestUtils {

	public static Expression readExpression(String expression) throws Exception {
		InputStream in = new ByteArrayInputStream(expression.getBytes("UTF-8"));
		return new ScriptReader().readExpression(in);
	}
	public static ImportDeclaration readImportDeclaration(String imp) throws Exception {
		InputStream in = new ByteArrayInputStream(imp.getBytes("UTF-8"));
		return new ScriptReader().readImportDeclaration(in);
	}
	public static RelationshipDefinition readRelationshipDefinition(String imp) throws Exception {
		InputStream in = new ByteArrayInputStream(imp.getBytes("UTF-8"));
		return new ScriptReader().readRelationshipDefinition(in);
	}
}
