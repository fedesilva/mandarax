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

package org.mandarax.dsl;

import java.util.Collection;

import org.mandarax.dsl.util.Resolver;
import org.mandarax.dsl.util.TypeReasoner;
import org.mandarax.dsl.verification.*;
/**
 * Delegates verification to a chain of verifiers.
 * @author jens dietrich
 */
public class VerifyAll implements Verifier {
	
	final static Verifier[] ALL = {
		new CheckUniqueNamesInObjectDeclarations(),
		new CheckReferencesInObjectDeclarations()
	};
	
	public void verify(Collection<CompilationUnit> cus,VerificationErrorReporter errorHandler,Resolver resolver,TypeReasoner typeReasoner) throws VerificationException {
		for (Verifier verifier:ALL) {
			verifier.verify(cus, errorHandler,resolver,typeReasoner);
		}
	}
}
