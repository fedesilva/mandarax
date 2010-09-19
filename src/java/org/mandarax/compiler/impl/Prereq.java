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

package org.mandarax.compiler.impl;

import java.util.ArrayList;
import java.util.List;
import org.mandarax.dsl.Expression;

/**
 * Annotation description of prerequisites. 
 * @author jens dietrich
 */
public class Prereq {
	private Expression expression = null;
	private List<String> newlyBoundVariables = new ArrayList<String>(); 
	
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	public List<String> getNewlyBoundVariables() {
		return newlyBoundVariables;
	}
	public void setNewlyBoundVariables(List<String> newlyBoundVariables) {
		this.newlyBoundVariables = newlyBoundVariables;
	}

}
