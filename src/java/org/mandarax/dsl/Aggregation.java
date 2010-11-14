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
import java.util.Map;

/**
 * Represents an aggregation. 
 * @author jens dietrich
 */
public class Aggregation extends Expression {

	private AggregationFunction function = null;
	private Variable variable = null;
	private FunctionInvocation expression = null;
	
	
	public Aggregation(Position position,Context context,String fun,String var,FunctionInvocation expr) {
		super(position,context);
		this.function = AggregationFunction.valueOf(fun);
		this.variable = new Variable(position,context,var);
		this.expression = expr;
	}
	
	private Aggregation(Position position,Context context,AggregationFunction fun,Variable var,FunctionInvocation expr) {
		super(position,context);
		this.function = fun;
		this.variable = var;
		this.expression = expr;
	}

	
	@Override
	public Expression substitute(final Map<Expression,? extends Expression> substitutions) {
		Expression substituteThis = substitutions.get(this);
		if (substituteThis==null) {
			Aggregation e = new Aggregation(getPosition(),getContext(),function,(Variable)variable.substitute(substitutions),(FunctionInvocation)expression.substitute(substitutions));
			copyPropertiesTo(e);
			return e;
		}
		else {
			return substituteThis;
		}
	}

	@Override
	public void accept(ASTVisitor visitor) {
		if (visitor.visit(this)) {
			variable.accept(visitor);
			expression.accept(visitor);
		}
		visitor.endVisit(this);
	}

	@Override
	public List<Expression> getChildren() {
		List<Expression> children = new ArrayList<Expression>(2);
		children.add(variable);
		children.add(expression);
		return children;
	}

	public AggregationFunction getFunction() {
		return function;
	}

	public Variable getVariable() {
		return variable;
	}

	public FunctionInvocation getExpression() {
		return expression;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((function == null) ? 0 : function.hashCode());
		result = prime * result + ((variable == null) ? 0 : variable.hashCode());
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
		Aggregation other = (Aggregation) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (function != other.function)
			return false;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}


}
