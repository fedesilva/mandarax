package org.mandarax.dsl;

/**
 * InstanceOf expression.
 * @author jens dietrich
 */
public class InstanceOfExpression extends Expression{
	private Expression objectReference = null;
	private String type = null;
	
	public InstanceOfExpression(Position position,Expression objectReference, String type) {
		super(position);
		this.objectReference = objectReference;
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
