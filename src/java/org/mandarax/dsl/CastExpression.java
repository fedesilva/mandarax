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
 * Cast expression.
 * @author jens dietrich
 */

public class CastExpression extends Expression {
	private Expression objectReference = null;
	private String type = null;
	public CastExpression(Position position,Context context,Expression child, String type) {
		super(position,context);
		this.objectReference = child;
		this.type = type;
	}
	public Expression getObjectReference() {
		return objectReference;
	}
	public String getTypeName() {
		return type;
	}
	
	public void accept(ASTVisitor visitor) {
		if (visitor.visit(this)) {
			objectReference.accept(visitor);
		}
		visitor.endVisit(this);
	}
	
	protected void appendTo(StringBuffer b) {
		b.append('(');
		b.append(type);
		b.append(')');
		objectReference.appendTo(b);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((objectReference == null) ? 0 : objectReference.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		CastExpression other = (CastExpression) obj;
		if (objectReference == null) {
			if (other.objectReference != null)
				return false;
		} else if (!objectReference.equals(other.objectReference))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	@Override
	public List<Expression> getChildren() {
		List<Expression> children = new ArrayList<Expression>(1);
		children.add(objectReference);
		return children;
	}
	
}
