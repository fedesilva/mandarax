package org.mandarax.dsl;

/**
 * String literal.
 * @author jens dietrich
 */

public class StringLiteral extends Literal<String> {
	private String value = null;

	public StringLiteral(Position position,String value) {
		super(position);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	
	public String toString() {
		return "StringLiteral("+value+')';
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}
}
