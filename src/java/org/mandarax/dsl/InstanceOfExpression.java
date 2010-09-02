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
 * InstanceOf expression.
 * @author jens dietrich
 */
public class InstanceOfExpression extends Expression{
	private Expression objectReference = null;
	private String type = null;
	
	public InstanceOfExpression(Position position,Context context,Expression objectReference, String type) {
		super(position,context);
		this.objectReference = objectReference;
		this.type = type;
	}

	public Expression getObjectReference() {
		return objectReference;
	}
	public String getType() {
		return type;
	}
	
	public void accept(Visitor visitor) {
		if (visitor.visit(this)) {
			objectReference.accept(visitor);
		}
		visitor.endVisit(this);
	}
}
