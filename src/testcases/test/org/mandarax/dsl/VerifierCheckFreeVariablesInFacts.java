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

import java.util.Collection;
import org.junit.Test;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.DefaultVerificationErrorReporter;
import org.mandarax.dsl.VerificationException;
import org.mandarax.dsl.Verifier;
import org.mandarax.dsl.VerifyAll;
import org.mandarax.dsl.verification.CheckFreeVariablesInFacts;
import static test.org.mandarax.dsl.TestUtils.*;
/**
 * Test the respective verifier.
 * @author jens dietrich
 */
public class VerifierCheckFreeVariablesInFacts {
	

	
	@Test (expected=VerificationException.class)
	public void testCompilationUnit1A() throws Exception {
		Verifier verifier = new CheckFreeVariablesInFacts();
		Collection<CompilationUnit> cus = readCUSFromCP("rules7.rel");
		verifier.verify(cus, new DefaultVerificationErrorReporter());
	}
	
// removed - allow free variables in rule heads	
//	@Test (expected=VerificationException.class)
//	public void testCompilationUnit1B() throws Exception {
//		Verifier verifier = new VerifyAll();
//		Collection<CompilationUnit> cus = readCUSFromCP("rules7.rel");
//		verifier.verify(cus, new DefaultVerificationErrorReporter());
//	}
	
}
