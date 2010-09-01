package org.mandarax.dsl;

/**
 * Integer literal.
 * @author jens dietrich
 */

public class IntLiteral extends Literal<Integer> {
	private int value = 0;

	public IntLiteral(Position position,int value) {
		super(position);
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}
	
}
