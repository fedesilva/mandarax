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

package org.mandarax.rt;

/**
 * Factory that creates the default derivation step logger using log4j.
 * To enable logging in an application, execute <code>new EnableDefaultLogging().install()</code>
 * before the application starts.
 * @author jens dietrich
 */
public class EnableDefaultLogging extends DerivationStepLoggerFactory{
	
	/**
	 * Create a logger. Returning null is permitted - this means no logging will take place.
	 * @return
	 */
	public DerivationStepLogger createLogger() {
		return new DefaultDerivationStepLogger();
	}

}
