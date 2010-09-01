package org.mandarax.dsl;

/**
 * Variable/object references by name.
 * @author jens dietrich
 */

public class Variable extends Expression {
	private String name = null;

	public Variable(Position position,String name) {
		super(position);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "Variable("+name+')';
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}
	
	public boolean isFlat() {
		return true;
	}
}
