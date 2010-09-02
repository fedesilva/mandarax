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
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
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

}
