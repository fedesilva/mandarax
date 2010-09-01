package org.mandarax.dsl;

/**
 * Superclass for all AST nodes.
 * @author jens dietrich
 */

public abstract class ASTNode  implements Visitable {
	public ASTNode(Position position) {
		super();
		this.position = position;
	}

	// the position of this artefact in the script defining it
	private Position position = null;

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

}
