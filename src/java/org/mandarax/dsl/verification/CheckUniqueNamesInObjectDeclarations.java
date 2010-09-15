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

package org.mandarax.dsl.verification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.ObjectDeclaration;
import org.mandarax.dsl.VerificationErrorReporter;
import org.mandarax.dsl.VerificationException;
import org.mandarax.dsl.Verifier;
import org.mandarax.dsl.util.Resolver;
import org.mandarax.dsl.util.TypeReasoner;

/**
 * Ensure that the names used in object declarations are unique.
 * @author jens dietrich
 */
public class CheckUniqueNamesInObjectDeclarations implements Verifier {

	@Override
	public void verify(Collection<CompilationUnit> cus,VerificationErrorReporter errorHandler,Resolver resolver,TypeReasoner typeReasoner) throws VerificationException {
		for (CompilationUnit cu:cus) {
			List<ObjectDeclaration> objDecls = cu.getObjectDeclarations();
			Set<String> set = new HashSet<String>();
			List<ObjectDeclaration> objDecls2 = new ArrayList<ObjectDeclaration>();
			for (ObjectDeclaration objDecl:objDecls) {
				if (!set.add(objDecl.getName())) {
					// duplicate detected
					// find already contained object
					for (ObjectDeclaration objDecl2:objDecls2) {
						if (objDecl.getName().equals(objDecl2.getName())) {
							errorHandler.reportError(cu,"The same name " + objDecl.getName() + " is used twice at positions " + objDecl.getPosition() + " and " + objDecl2.getPosition());
						}
					}
				}
				else {
					objDecls2.add(objDecl);
				}
			}
		}
	}

}
