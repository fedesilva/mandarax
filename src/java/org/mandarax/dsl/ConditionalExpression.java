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
 * Conditional expression (cond?ifTrue:ifFalse).
 * @author jens dietrich
 */

public class ConditionalExpression extends Expression {
	
	private Expression condition = null;
	private Expression ifTrue = null;
	private Expression ifFalse = null;
	
	public ConditionalExpression(Position position,Context context,Expression condition, Expression ifTrue,Expression ifFalse) {
		super(position,context);
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}
	public Expression getCondition() {
		return condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	public Expression getIfTrue() {
		return ifTrue;
	}
	public void setIfTrue(Expression ifTrue) {
		this.ifTrue = ifTrue;
	}
	public Expression getIfFalse() {
		return ifFalse;
	}
	public void setIfFalse(Expression ifFalse) {
		this.ifFalse = ifFalse;
	}
	
	public void accept(ASTVisitor visitor) {
		if (visitor.visit(this)) {
			condition.accept(visitor);
			ifTrue.accept(visitor);
			ifFalse.accept(visitor);
		}
		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((ifFalse == null) ? 0 : ifFalse.hashCode());
		result = prime * result + ((ifTrue == null) ? 0 : ifTrue.hashCode());
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
		ConditionalExpression other = (ConditionalExpression) obj;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (ifFalse == null) {
			if (other.ifFalse != null)
				return false;
		} else if (!ifFalse.equals(other.ifFalse))
			return false;
		if (ifTrue == null) {
			if (other.ifTrue != null)
				return false;
		} else if (!ifTrue.equals(other.ifTrue))
			return false;
		return true;
	}
	
	@Override
	public List<Expression> getChildren() {
		List<Expression> children = new ArrayList<Expression>(3);
		children.add(this.condition);
		children.add(this.ifTrue);
		children.add(this.ifFalse);
		return children;
	}
}
