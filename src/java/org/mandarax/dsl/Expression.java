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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Superclass for expression AST nodes.
 * @author jens dietrich
 */

public abstract class Expression extends ASTNode {
	
	final static List<Expression> EMPTY_LIST = Collections.unmodifiableList(new ArrayList<Expression>(0));
	
	private Class type = null;

	public Expression(Position position,Context context) {
		super(position,context);
	}
	
	public boolean isFlat() {
		return false;
	}
	
	public boolean isPrimitiveLiteral() {
		return false;
	}
	
	public Class getType() {
		return type;
	}
	public void setType(Class type) {
		this.type = type;
	}
	
	
	// reset cached info
	public void reset() {
		super.reset();
		this.type = null;
	}
	/**
	 * Get a collection of children - those are nexted expressions.
	 * @return
	 */
	public abstract List<Expression> getChildren();
	
	/**
	 * Indicates whether this expression is contructed from a list of given expressions. 
	 * @param boundExpressions
	 * @return
	 */
	public boolean isGroundWRT(Collection<Expression> boundExpressions) {
		if (boundExpressions.contains(this)) return true;
		else {
			List<Expression> children = getChildren();
			if (children.isEmpty()) return false;
			for (Expression child:children) {
				if (!isGroundWRT(boundExpressions)) return false;
			}
			return true;
		}
	}
}
