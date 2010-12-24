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
import org.junit.runner.RunWith;

import org.junit.runners.*;

/**
 * Test suite containing all parser tests.
 * @author jens dietrich
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
	// syntax - expressions
	ParserTestsArithmetic.class,
	ParserTestsRelationalExpressions.class,
	ParserTestsFlatExpressions.class,
	ParserTestsMemberAccess.class,
	ParserTestsFunctionInvocations.class,
	ParserTestsMiscExpressions.class,
	ParserTestsComplexExpressions.class,
	// syntax - other elements
	ParserTestsImportDeclarations.class,
	ParserTestsPackageDeclarations.class,
	ParserTestsRelationshipDefinitions.class,
	ParserTestsAnnotations.class,
	ParserTestsRules.class,
	ParserTestsObjectDeclarations.class,
	ParserTestsInDomain.class,
	ParserTestsAggregations.class,
	ParserTestsExternalFacts.class,
	// integration tests
	ParserIntegrationTests.class
})
public class TestSuiteForParser {

}

