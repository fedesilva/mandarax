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

import com.google.common.base.Function;

/**
 * Import declaration.
 * @author jens dietrich
 */
public class ImportDeclaration extends ASTNode {
	
	private boolean staticImport = false;
	private boolean usesWildcard = false;
	private String name = null;

	public ImportDeclaration(Position position, Context context,String name,boolean isStatic,boolean usesWildcard) {
		super(position, context);
		this.name = name;
		this.staticImport = isStatic;
		this.usesWildcard = usesWildcard;
		context.add(this);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

	public boolean isStaticImport() {
		return staticImport;
	}

	public boolean isUsingWildcard() {
		return usesWildcard;
	}

	public String getName() {
		return name;
	}
	
	public void appendTo(StringBuffer b,Function<Variable,String> conversion) {
		b.append("import ");
		if (this.staticImport) {
			b.append("static ");
		}
		b.append(name);
		if (this.usesWildcard) {
			b.append(".*");
		}
		b.append(';');
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (staticImport ? 1231 : 1237);
		result = prime * result + (usesWildcard ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImportDeclaration other = (ImportDeclaration) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (staticImport != other.staticImport)
			return false;
		if (usesWildcard != other.usesWildcard)
			return false;
		return true;
	}

}
