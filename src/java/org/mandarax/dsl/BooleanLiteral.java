package org.mandarax.dsl;

/**
 * Boolean literal.
 * @author jens dietrich
 */

public class BooleanLiteral extends Literal<Boolean> {
	private boolean value = false;

	public BooleanLiteral(Position position,boolean value) {
		super(position);
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}
}
