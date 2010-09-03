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

import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * Represents queries. 
 * @author jens dietrich
 */
public class Query extends ASTNode {
	public Query(Position position, Context context, String predicateName,List<VariableDeclaration> slotDeclarations,String methodName, List<String> methodParamNames) throws IllegalArgumentException {
		super(position, context);
		this.predicateName = predicateName;
		this.methodName = methodName;
		this.methodParamNames = methodParamNames;
		this.slotDeclarations = slotDeclarations;
		
		// consistency check: all methodParamNames must occur in the slot declarations
		Collection<String> definedNames = Collections2.transform(slotDeclarations,new Function<VariableDeclaration,String>(){
			@Override
			public String apply(VariableDeclaration v) {return v.getName();}});
		
		for (String name:methodParamNames) {
			if (!definedNames.contains(name)) throw new IllegalArgumentException("Exception in query definition at " + getPosition() + " the following method parameter is not defined as predicate slot: " + name);
		}
	}

	private String predicateName = null;
	private String methodName = null;
	private List<String> methodParamNames = null;
	private List<VariableDeclaration> slotDeclarations = null;

	
	@Override
	public void accept(ExpressionVisitor visitor) {
		
	}

	public String getPredicateName() {
		return predicateName;
	}

	public String getMethodName() {
		return methodName;
	}

	public List<String> getMethodParamNames() {
		return methodParamNames;
	}

	public List<VariableDeclaration> getSlotDeclarations() {
		return slotDeclarations;
	}

}
