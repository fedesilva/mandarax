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

import static org.mandarax.dsl.Utils.nameForBinOp;


/**
 * Represents binary expressions having two children connected by boolean or arithmetic operators.
 * @author jens dietrich
 */

public class BinaryExpression extends Expression {
	public BinaryExpression(Position position,Context context,BinOp operator, Expression left, Expression right) {
		super(position,context);
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	private BinOp operator = null;
	private Expression left = null;
	private Expression right = null;
	public BinOp getOperator() {
		return operator;
	}
	public Expression getLeft() {
		return left;
	}
	public Expression getRight() {
		return right;
	}
	
	public void accept(ASTVisitor visitor) {
		if (visitor.visit(this)) {
			left.accept(visitor);
			right.accept(visitor);
		}
		visitor.endVisit(this);
	}
	
	protected void appendTo(StringBuffer b) {
		if (!left.isFlat()) b.append('(');
		left.appendTo(b);
		if (!left.isFlat()) b.append(')');
		
		b.append(nameForBinOp(operator));
		
		if (!right.isFlat()) b.append('(');
		right.appendTo(b);
		if (!right.isFlat()) b.append(')');
	}
	
}
