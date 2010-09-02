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
import org.junit.runner.RunWith;

import org.junit.runners.*;

/**
 * Test suite. Used to run all tests in this package.
 * @author jens dietrich
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ParserTestsArithmetic.class,
	ParserTestsRelationalExpressions.class,
	ParserTestsFlatExpressions.class,
	ParserTestsMemberAccess.class,
	ParserTestsFunctionInvocations.class,
	ParserTestsMiscExpressions.class,
	ParserTestsComplexExpressions.class,
	TypeReasonerTests.class
})
public class AllTests {

}

