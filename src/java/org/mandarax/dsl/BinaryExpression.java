package org.mandarax.dsl;


/**
 * Represents binary expressions having two children connected by boolean or arithmetic operators.
 * @author jens dietrich
 */

public class BinaryExpression extends Expression {
	public BinaryExpression(Position position,BinOp operator, Expression left, Expression right) {
		super(position);
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
	
	public void accept(Visitor visitor) {
		if (visitor.visit(this)) {
			left.accept(visitor);
			right.accept(visitor);
		}
		visitor.endVisit(this);
	}
	
}
