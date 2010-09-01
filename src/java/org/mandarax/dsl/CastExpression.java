package org.mandarax.dsl;


/**
 * Cast expression.
 * @author jens dietrich
 */

public class CastExpression extends Expression {
	private Expression objectReference = null;
	private String type = null;
	public CastExpression(Position position,Expression child, String type) {
		super(position);
		this.objectReference = child;
		this.type = type;
	}
	public Expression getObjectReference() {
		return objectReference;
	}
	public String getType() {
		return type;
	}
	
	public void accept(Visitor visitor) {
		if (visitor.visit(this)) {
			objectReference.accept(visitor);
		}
		visitor.endVisit(this);
	}
}
