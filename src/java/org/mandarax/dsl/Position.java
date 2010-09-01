/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

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
