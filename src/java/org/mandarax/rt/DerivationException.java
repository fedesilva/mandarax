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
 * Exceptions that can be thrown when next(), hasNext() or close() are invoked.
 * This are runtime exceptions. The main reason for this is compatibility with java.util.Iterator. 
 * I.e., users can work with the well-known iterator interface. 
 * @author jens dietrich
 */

public class DerivationException extends RuntimeException {

	public DerivationException() {
		super();
	}

	public DerivationException(String m, Throwable t) {
		super(m, t);
	}

	public DerivationException(String m) {
		super(m);
	}

	public DerivationException(Throwable t) {
		super(t);
	}

}
