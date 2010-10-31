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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * Function invocation. A function can refer either to a relationship query or is imported.
 * Note that this information is not set by the parser, references must be resolved (usually by the compiler) in a separate processing step. 
 * @author jens dietrich
 */
public class FunctionInvocation extends Expression {

	private String function = null;
	private List<Expression> parameters = new ArrayList<Expression>();
	private RelationshipDefinition relationship = null; // if this is a reference to a relationship
	private Method referencedMethod = null; // if this is a reference to an imported function
	
	public FunctionInvocation(Position position,Context context,String function,List<Expression> parameters) {
		super(position,context);
		this.function = function;
		this.parameters = parameters;
	}

	public boolean isDefinedByRelationship() {
		return relationship!=null;
	}
	
	public String getFunction() {
		return function;
	}
	
	@Override
	public Expression substitute(final Map<Expression,? extends Expression> substitutions) {
		Expression substituteThis = substitutions.get(this);
		if (substituteThis!=null) {
			return new FunctionInvocation(getPosition(),getContext(),function,Lists.transform(parameters, new Function<Expression,Expression>() {
				@Override
				public Expression apply(Expression p) {
					return p.substitute(substitutions);
				}}));
		}
		else {
			return substituteThis;
		}
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


	public void setFunction(String function) {
		this.function = function;
	}

	public Method getReferencedMethod() {
		return referencedMethod;
	}

	public void setReferencedMethod(Method referencedMethod) {
		this.referencedMethod = referencedMethod;
	}

	public RelationshipDefinition getRelationship() {
		return relationship;
	}

	public void setRelationship(RelationshipDefinition relationship) {
		this.relationship = relationship;
	}
	
	@Override
	public List<Expression> getChildren() {
		List<Expression> children = new ArrayList<Expression>();
		children.addAll(this.parameters);
		return children;
	}

}
