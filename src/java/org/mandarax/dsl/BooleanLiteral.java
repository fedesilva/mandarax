/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.gnu.org/licenses/agpl.html 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package org.mandarax.dsl;

import java.util.Map;


/**
 * Boolean literal.
 * @author jens dietrich
 */

public class BooleanLiteral extends Literal<Boolean> {
	private boolean value = false;

	public BooleanLiteral(Position position,Context context,boolean value) {
		super(position,context);
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}
	
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (value ? 1231 : 1237);
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
		BooleanLiteral other = (BooleanLiteral) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	@Override
	public Expression substitute(Map<Expression,? extends Expression> substitutions) {
		Expression substituteThis = substitutions.get(this);
		if (substituteThis==null) {
			BooleanLiteral e = new BooleanLiteral(getPosition(),getContext(),value);
			e.setType(this.getType());
			copyPropertiesTo(e);
			return e;
		}
		else {
			return substituteThis;
		}
	}
}
