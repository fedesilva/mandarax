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

package org.mandarax.dsl.verification;

import java.util.Collection;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.ObjectDeclaration;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.Rule;
import org.mandarax.dsl.Variable;
import org.mandarax.dsl.VerificationErrorReporter;
import org.mandarax.dsl.VerificationException;
import org.mandarax.dsl.Verifier;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * Ensure that facts have only ground terms.
 * @author jens dietrich
 */
public class CheckFreeVariablesInFacts implements Verifier {

	@Override
	public void verify(Collection<CompilationUnit> cus,VerificationErrorReporter errorHandler) throws VerificationException {
		for (CompilationUnit cu:cus) {
			// collect declared objects
			Collection<String> objNames = Collections2.transform(cu.getObjectDeclarations(),new Function<ObjectDeclaration,String>(){
				@Override
				public String apply(ObjectDeclaration obj) {
					return obj.getName();
				}});
			for (RelationshipDefinition rel:cu.getRelationshipDefinitions()) {
				for (Rule rule:rel.getRules()) {
					if (rule.isFact()) {
						for (Variable var:rule.getHead().getVariables()) {
							if (!objNames.contains(var.getName())) {
								errorHandler.reportError(cu,"The term ",var," at ", rule.getPosition()," in the fact ",rule.getId()," cannot be a free variable");
							}
						}
					}

				}
			}
		}
		
	}

}
