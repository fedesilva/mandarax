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
import java.util.List;


/**
 * Superclass for expression AST nodes.
 * @author jens dietrich
 */

public abstract class Expression extends ASTNode {
	
	private Class type = null;
	private List<Variable> variables = null;

	public Expression(Position position,Context context) {
		super(position,context);
	}
	
	public boolean isFlat() {
		return false;
	}
	
	public Class getType() {
		return type;
	}
	public void setType(Class type) {
		this.type = type;
	}
	
	/**
	 * Get all variables contained in this expression.
	 * @return
	 */
	public List<Variable> getVariables() {
		// try cached variables first
		if (variables!=null) return variables;
		
		class VariableCollector extends AbstractExpressionVisitor {
			List<Variable> variables = new ArrayList<Variable>();
			@Override
			public boolean visit(Variable x) {
				// check whether this is a reference to object declaration 
				// ignore those
				for (ObjectDeclaration objDecl:getContext().getObjectDeclarations()) {
					if (objDecl.getName().equals(x.getName())) {
						return super.visit(x);
					}
				}
				variables.add(x);
				return super.visit(x);
			}
		}
		VariableCollector collector = new VariableCollector();
		this.accept(collector);
		variables = collector.variables;
		
		return variables;
		
	}
	
	// reset cached info
	public void reset() {
		this.type = null;
		this.variables = null;
	}
}
