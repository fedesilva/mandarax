package org.mandarax.dsl;

/**
 * Expression using an unary operator such as ! or -.
 * @author jens dietrich
 */

public class UnaryExpression extends Expression {

	private UnOp operator = null;
	private Expression part = null;
	
	public UnaryExpression(Position position,UnOp operator, Expression part) {
		super(position);
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
