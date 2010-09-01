package org.mandarax.dsl;


/**
 * Superclass for expression AST nodes.
 * @author jens dietrich
 */

public abstract class Expression extends ASTNode {
	public Expression(Position position) {
		super(position);
	}

	
	public boolean isFlat() {
		return false;
	}
}
