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

package org.mandarax.examples.userv.domain;

/**
 * Bean class that is part of the example domain model.
 * http://www.businessrulesforum.com/2005_Product_Derby.pdf 
 * @author jens dietrich
 */

public class Policy {
	private boolean includesUninsuredMotoristCoverage = false;
	private boolean includesMedicalCoverage = false;
	public boolean includesUninsuredMotoristCoverage() {
		return includesUninsuredMotoristCoverage;
	}
	public void setIncludesUninsuredMotoristCoverage(
			boolean includesUninsuredMotoristCoverage) {
		this.includesUninsuredMotoristCoverage = includesUninsuredMotoristCoverage;
	}
	public boolean includesMedicalCoverage() {
		return includesMedicalCoverage;
	}
	public void setIncludesMedicalCoverage(boolean includesMedicalCoverage) {
		this.includesMedicalCoverage = includesMedicalCoverage;
	}
	
}
