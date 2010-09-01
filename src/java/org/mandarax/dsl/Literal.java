package org.mandarax.dsl;

/**
 * Abstract superclass for literals.
 * @author jens dietrich
 */

public abstract class Literal<T> extends Expression {
	
	public Literal(Position position) {
		super(position);
	}

	public abstract T getValue();
	
	public boolean isFlat() {
		return true;
	}

}
