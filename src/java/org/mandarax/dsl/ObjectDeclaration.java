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

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * Represents the declaration of an object. 
 * @author jens dietrich
 */
public class ObjectDeclaration extends ASTNode {
	
	private String type = null;
	private String name = null;
	private Expression defaultValueDeclaration = null;
	
	public ObjectDeclaration(Position position, Context context, String type,final String name,Expression defaultValueDeclaration) throws InternalScriptException {
		super(position, context);
		this.type = type;
		this.name = name;
		this.defaultValueDeclaration = defaultValueDeclaration;
		
		// add to context and check whether all variables are defined
		Collection<ObjectDeclaration> defined = Collections2.filter(context.getObjectDeclarations(),new Predicate<ObjectDeclaration>(){
			@Override
			public boolean apply(ObjectDeclaration decl) {
				return name.equals(decl.name);
			}});
		
		if (!defined.isEmpty()) {
			throw new InternalScriptException("Cannot define object " + name + " at " + position + " - this name has already been used to define an object at " + defined.iterator().next().getPosition());
		}
		
		// check whether there are references to undefined variables
		Collection<Variable> vars = defaultValueDeclaration.getVariables();
		Collection<String> names = Collections2.transform(context.getObjectDeclarations(),new Function<ObjectDeclaration,String>(){
			@Override
			public String apply(ObjectDeclaration decl) {
				return decl.name;
			}});
		
		for (Variable var:vars) {
			if (!names.contains(var)) {
				throw new InternalScriptException("Cannot reference object " + var.getName() + " at " + var.getPosition() + " - this object has not yet been defined");
			}
		}
		
		// add to context
		context.add(this);
		
	}


	public void accept(ASTVisitor visitor) {
		if (visitor.visit(this)) {
			defaultValueDeclaration.accept(visitor);
		}
		visitor.endVisit(this);
	}

	@Override
	protected void appendTo(StringBuffer b) {
		b.append("object ");
		b.append(type);
		b.append(' ');
		b.append(name);
		if (defaultValueDeclaration!=null) {
			b.append(" = ");
			defaultValueDeclaration.appendTo(b);
		}
		b.append(";");
	}

	public String getName() {
		return name;
	}


	public Expression getDefaultValueDeclaration() {
		return defaultValueDeclaration;
	}

	public String getType() {
		return type;
	}


}
