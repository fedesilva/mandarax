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

package org.mandarax.dsl;

import java.util.Collection;

/**
 * Tool to check the consistency of parsed scripts. The purpose of verifiers is to enforce semantic rules
 * that are not part of the grammar.
 * @author jens dietrich
 */
public interface Verifier {
	public void verify(Collection<CompilationUnit> cus,VerificationErrorReporter errorHandler) throws VerificationException ;
}
