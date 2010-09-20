/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package org.mandarax.dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * Function invocation.
 * @author jens dietrich
 */
public class FunctionInvocation extends Expression {

	private String function = null;
	private List<Expression> parameters = new ArrayList<Expression>();
	
	public FunctionInvocation(Position position,Context context,String function,List<Expression> parameters) {
		super(position,context);
		this.function = function;
		this.parameters = parameters;
	}

	public String getFunction() {
		return function;
	}



	public List<Expression> getParameters() {
		return parameters;
	}
	
	@Override
	public void accept(ASTVisitor visitor) {
		if (visitor.visit(this)) {
			for (Expression param:parameters) {
				param.accept(visitor);
			}
		}
		visitor.endVisit(this);
	}
	
	protected void appendTo(StringBuffer b) {
		b.append(function);
		appendListOfNodes(parameters, b,true);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((function == null) ? 0 : function.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FunctionInvocation other = (FunctionInvocation) obj;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}

}
