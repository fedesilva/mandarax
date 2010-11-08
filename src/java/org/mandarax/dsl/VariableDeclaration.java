/*
 * Copyright 2010 Jens Dietrich Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.mandarax.dsl;

import static org.mandarax.dsl.Utils.*;
/**
 * Represents variable declarations consisting of a type (name) and a variable name.
 * @author jens dietrich
 */
public class VariableDeclaration extends ASTNode {

	public VariableDeclaration(Position position, Context context,String type,String name) {
		super(position, context);
		this.type = type;
		this.name = name;
	}

	private String type = null;
	private String name = null;
	
	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(type);
		b.append(' ');
		b.append(name);
		return b.toString();
	}
	
	public String getDefaultValue() {
		return Utils.getDefaultValue(type);
	}

}
