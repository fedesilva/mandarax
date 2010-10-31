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
 * Expression using an unary operator such as ! or -.
 * @author jens dietrich
 */

public class UnaryExpression extends Expression {

	private UnOp operator = null;
	private Expression part = null;
	
	public UnaryExpression(Position position,Context context,UnOp operator, Expression part) {
		super(position,context);
		this.operator = operator;
		this.part = part;
	}
	
	
	public UnOp getOperator() {
		return operator;
	}
	public Expression getPart() {
		return part;
	}
	
	public void accept(ASTVisitor visitor) {
		if (visitor.visit(this)) {
			part.accept(visitor);
		}
		visitor.endVisit(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((part == null) ? 0 : part.hashCode());
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
		UnaryExpression other = (UnaryExpression) obj;
		if (operator != other.operator)
			return false;
		if (part == null) {
			if (other.part != null)
				return false;
		} else if (!part.equals(other.part))
			return false;
		return true;
	}
	
	@Override
	public List<Expression> getChildren() {
		List<Expression> children = new ArrayList<Expression>(1);
		children.add(this.part);
		return children;
	}
	
	@Override
	public Expression substitute(Map<Expression,Expression> substitutions) {
		Expression substituteThis = substitutions.get(this);
		if (substituteThis!=null) {
			return new UnaryExpression(getPosition(),getContext(),operator,part.substitute(substitutions));
		}
		else {
			return substituteThis;
		}
	}
	
}
