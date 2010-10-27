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

/**
 * Semantic exceptions - throws if constraints that are not enforced by the ANTLR grammar are encountered.
 * This is a runtime exception to minimize interference with ANTLR.
 * @author jens dietrich
 */
@SuppressWarnings("serial")
public class InternalScriptException extends RuntimeException {

	public InternalScriptException() {}

	public InternalScriptException(String message) {
		super(message);
	}

	public InternalScriptException(Throwable cause) {
		super(cause);
	}

	public InternalScriptException(String message, Throwable cause) {
		super(message, cause);
	}

}
