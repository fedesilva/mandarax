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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mandarax.compiler.CompilerException;
import org.mandarax.dsl.Context;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.FunctionDeclaration;
import org.mandarax.dsl.FunctionInvocation;
import org.mandarax.dsl.ObjectDeclaration;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.Variable;
import org.mandarax.dsl.VariableDeclaration;

/**
 * Utility to keep track of bindings of variables.
 * @author jens dietrich
 */
public class VariableBindings {
	
	public static org.apache.log4j.Logger LOGGER = Logger.getLogger(VariableBindings.class);
	
	private Map<String,String> map = new HashMap<String,String>();
	private List<ObjectDeclaration> objectDeclarations = new ArrayList<ObjectDeclaration>();
	private FunctionInvocation ruleHead = null;
	private String className = null; // the name of the class that is being generated
	
	public VariableBindings(Context c,String className) {
		super();
		objectDeclarations.addAll(c.getObjectDeclarations());
		this.className = className;
	}
	
	
	public String getBinding(Variable var) {
		String value = getBindingNoDefault(var);
		if (value==null) value = getDefaultValue(var);
		return value;
	}
	
	private String getBindingNoDefault(Variable var) {
		for (ObjectDeclaration objDecl:objectDeclarations) {
			if (objDecl.getName().equals(var.getName())) {
				return className+'.'+var.getName(); // can reference this as a static variable in the class that is being generated
			}
		}
		return map.get(var.getName());
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
		this.ruleHead = ruleHead;
		
		boolean[] signature = query.getSignature();
		for (int i=0;i<signature.length;i++) {
			if (signature[i]) {
				Expression x = ruleHead.getParameters().get(i);
				if (x instanceof Variable) {
					Variable v = (Variable)x;
					map.put(v.getName(),query.getRelationship().getSlotDeclarations().get(i).getName());
				}
			}
		}
	}
	
	
	
	// indicates whether bindings exist for all variables referenced in this expression
	private boolean isBound(Expression expression) {
		for (Variable var:expression.getVariables()) {
			if (getBindingNoDefault(var)==null) {
				return false;
			}
		}
		return true;
	}
	
	public String print(Expression expression,String scope) {
		// TODO full recursion
		
		if (expression instanceof Variable) {
			return scope + '.' + expression;
		}
		return expression.toString();
	}
	
	public String print(VariableDeclaration var,int pos,String scope) {
		// detect variable term used in rule head,
		// and then look up matching property in bindings
		Variable v = (Variable) ruleHead.getParameters().get(pos); // TODO refactor if complex terms are supported in rule head
		return scope + '.' + v.getName();
	}
 
	
	public String printSlots(List<VariableDeclaration> varDecls,String scope) {
		StringBuffer buf = new StringBuffer();
		int i = 0;
		for (VariableDeclaration varDecl:varDecls) {
			if (buf.length()>0) buf.append(',');
			buf.append(print(varDecl,i,scope));
			i = i+1;
		}
		return buf.toString();
	}
	
	// get the query to be invoked for a function invocation that references a relationship definition
	public FunctionDeclaration getQuery(Prereq prereq) throws CompilerException {
		FunctionInvocation finv = (FunctionInvocation)prereq.getExpression();
		RelationshipDefinition rel = finv.getRelationship();
		boolean[] signature = new boolean[rel.getSlotDeclarations().size()];
		for (int i=0;i<signature.length;i++) {
			Expression term = finv.getParameters().get(i);
			signature[i]=this.isBound(term);
		}
		for (FunctionDeclaration query:rel.getQueries()) {
			if (Arrays.equals(signature,query.getSignature())) {
				return query;
			}
		}
		throw new CompilerException("Cannot find matching query for function invocation: " + finv);
	}
	
}
