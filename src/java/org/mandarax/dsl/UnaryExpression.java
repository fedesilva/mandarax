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
	
	public void accept(Visitor visitor) {
		if (visitor.visit(this)) {
			part.accept(visitor);
		}
		visitor.endVisit(this);
	}
	
}
