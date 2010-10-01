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

package org.mandarax.compiler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mandarax.dsl.Context;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.FunctionDeclaration;
import org.mandarax.dsl.FunctionInvocation;
import org.mandarax.dsl.ObjectDeclaration;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.Variable;

/**
 * Utility to keep track of bindings of variables.
 * @author jens dietrich
 */
public class VariableBindings {
	
	private Map<Variable,String> map = new HashMap<Variable,String>();
	private List<ObjectDeclaration> objectDeclarations = new ArrayList<ObjectDeclaration>();
	
	public VariableBindings(Context c) {
		super();
		objectDeclarations.addAll(c.getObjectDeclarations());
	}
	
	
	public String getBinding(Variable var) {
		String value = getBindingNoDefault(var);
		if (value==null) value = getDefaultValue(var);
		return value;
	}
	
	private String getBindingNoDefault(Variable var) {
		for (ObjectDeclaration objDecl:objectDeclarations) {
			if (objDecl.getName().equals(var.getName())) {
				return var.getName(); // can reference this as an instance variable
			}
		}
		return map.get(var);
	}
	
	// utility for code generation
	public String getDefaultValue(Variable var) {
		Class type = var.getType();
		if (type==null) return null;
		else if (type==Integer.class || type==Integer.TYPE) return new Integer(0).toString();
		else if (type==Short.class || type==Short.TYPE) return new Short((short) 0).toString();
		else if (type==Long.class || type==Long.TYPE) return new Long(0).toString();
		else if (type==Byte.class || type==Byte.TYPE) return new Byte((byte) 0).toString();
		else if (type==Character.class || type==Character.TYPE) return new Character((char) 0).toString();
		else if (type==Double.class || type==Double.TYPE) return new Double(0).toString();
		else if (type==Float.class || type==Float.TYPE) return new Float(0).toString();
		else if (type==Boolean.class || type==Boolean.TYPE) return Boolean.FALSE.toString();
		return "null";
	}

	
	
	public void bind(FunctionInvocation ruleHead,FunctionDeclaration query) {
		for (int i=0;i<query.getParameterNames().size();i++) {
			Expression x = ruleHead.getParameters().get(i);
			if (x instanceof Variable) {
				Variable v = (Variable)x;
				map.put(v,query.getParameterNames().get(i));
			}
		}
	}
	// indicates whether bindings exist for all variables referenced in this expression
	public boolean isBound(Expression expression) {
		for (Variable var:expression.getVariables()) {
			if (getBindingNoDefault(var)==null) {
				return false;
			}
		}
		return true;
	}
	

}
