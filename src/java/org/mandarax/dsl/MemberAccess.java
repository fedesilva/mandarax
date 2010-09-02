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
 * Member (method or field) access expression.
 * @author jens dietrich
 */

public class MemberAccess extends Expression {
	private String member = null;
	private Expression objectReference = null;
	private List<Expression> parameters = new ArrayList<Expression>();
	private boolean isMethod = false;
	
	public MemberAccess(Position position,Context context,Expression objectReference,String member,List<Expression> parameters) {
		super(position,context);
		this.member = member;
		this.objectReference = objectReference;
		this.parameters = parameters;
		this.isMethod = true;
	}
	public MemberAccess(Position position,Context context,Expression objectReference,String member) {
		super(position,context);
		this.member = member;
		this.objectReference = objectReference;
		this.isMethod = false;
	}
	
	public String getMember() {
		return member;
	}
	public Expression getObjectReference() {
		return objectReference;
	}
	public List<Expression> getParameters() {
		return parameters;
	}
	public boolean isMethod() {
		return isMethod;
	}
	public void accept(Visitor visitor) {
		if (visitor.visit(this)) {
			objectReference.accept(visitor);
			for (Expression param:parameters) {
				param.accept(visitor);
			}
		}
		visitor.endVisit(this);
	}


}
