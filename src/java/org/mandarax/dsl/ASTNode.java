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

import java.util.List;

/**
 * Superclass for all AST nodes.
 * @author jens dietrich
 */

public abstract class ASTNode  implements Visitable {
	public ASTNode(Position position,Context context) {
		super();
		this.position = position;
		this.context = context;
	}

	// the position of this artefact in the script defining it
	private Position position = null;
	// contextual information
	private Context context = null;

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	// method used for toString conversion
	protected abstract void appendTo(StringBuffer b);
	
	// useful utility for printing: print a list in brackets, items separated by commas
	protected void appendListOfNodes(List<? extends ASTNode> list, StringBuffer b,boolean brackets) {
		if (brackets) b.append('(');
		boolean f = true;
		for (ASTNode n:list) {
			if (f) f=false;
			else b.append(',');
			n.appendTo(b);
		}
		if (brackets) b.append(')');
	}
	
	// useful utility for printing: print a list in brackets, items separated by commas
	protected void appendListOfStrings(List<String> list, StringBuffer b,boolean brackets) {
		if (brackets) b.append('(');
		boolean f = true;
		for (String n:list) {
			if (f) f=false;
			else b.append(',');
			b.append(n);
		}
		if (brackets) b.append(')');
	}
	
	public String toString() {
		StringBuffer b = new StringBuffer();
		this.appendTo(b);
		return b.toString();
	}
	
	

}
