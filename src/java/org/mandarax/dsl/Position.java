package org.mandarax.dsl;

/**
 * Simple data structure to represent the position of an artefact in a script.
 * @author jens dietrich
 */

public class Position {
	public Position(int line, int charPosInLine) {
		super();
		this.line = line;
		this.charPosInLine = charPosInLine;
	}
	private int line = -1;
	private int charPosInLine = -1;
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getCharPosInLine() {
		return charPosInLine;
	}
	public void setCharPosInLine(int charPosInLine) {
		this.charPosInLine = charPosInLine;
	}
	
	public Position clone() {
		return new Position (this.getLine(),this.getCharPosInLine());
	}
}
