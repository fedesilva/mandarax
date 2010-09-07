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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * Represents relationship definitions. 
 * @author jens dietrich
 */
public class RelationshipDefinition extends AnnotatableNode {
	
	private String name = null;
	private List<FunctionDeclaration> queries = null;
	private List<VariableDeclaration> slotDeclarations = null;
	private List<String> superTypes = null;
	private List<Rule> rules = new ArrayList<Rule>();
	
	/**
	 * Constructor.
	 * @throws InternalScriptException thrown if method parameter names do not occur in slot definitions
	 */
	public RelationshipDefinition(Position position, Context context, String name,List<VariableDeclaration> slotDeclarations,List<String> superTypes,List<FunctionDeclaration> queries) throws InternalScriptException {
		super(position, context);
		this.name = name;
		this.slotDeclarations = slotDeclarations;
		this.superTypes = superTypes;
		this.queries = queries;
		
		
		// consistency check: all methodParamNames must occur in the slot declarations
		Collection<String> definedNames = Collections2.transform(slotDeclarations,new Function<VariableDeclaration,String>(){
			@Override
			public String apply(VariableDeclaration v) {return v.getName();}});
		
		
		for (FunctionDeclaration query:queries) {
			for (String refedName:query.getParameterNames()) {
				if (!definedNames.contains(refedName)) 
					throw new InternalScriptException("Exception in query definition at " + query.getPosition() + " the following method parameter is not defined as predicate slot: " + name);
			}
		}
	}


	
	@Override
	public void accept(ExpressionVisitor visitor) {
		
	}

	public String getName() {
		return name;
	}

	public List<VariableDeclaration> getSlotDeclarations() {
		return slotDeclarations;
	}

	public List<FunctionDeclaration> getQueries() {
		return queries;
	}

	public List<String> getSuperTypes() {
		return superTypes;
	}

	protected void appendTo(StringBuffer b) {
		b.append(name);
		appendListOfNodes(slotDeclarations, b,true);
		if (superTypes!=null && !superTypes.isEmpty()) {
			b.append("extends ");
			this.appendListOfStrings(superTypes, b,false);
		}
		b.append(' ');
		appendListOfNodes(queries, b,false);
	}

	public List<Rule> getRules() {
		return rules;
	}
	
	public void addRule(Rule r) {
		rules.add(r);
	}
}
