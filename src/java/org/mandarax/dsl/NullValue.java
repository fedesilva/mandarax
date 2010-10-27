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

import java.util.Collection;
import java.util.List;

/**
 * Null value.
 * @author jens dietrich
 */

public class NullValue extends Expression {

	public NullValue(Position position,Context context) {
		super(position,context);
	}
	
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}
	
	@Override
	public boolean isFlat() {
		return true;
	}
	
	@Override
	public List<Expression> getChildren() {
		return EMPTY_LIST;
	}
	
	/**
	 * Indicates whether this expression is constructed from a list of given expressions. 
	 * @param boundExpressions
	 * @return
	 */
	public boolean isGroundWRT(Collection<Expression> boundExpressions) {
		return true;
	}
}
