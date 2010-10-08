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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.mandarax.compiler.CompilerException;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.FunctionDeclaration;
import org.mandarax.dsl.Literal;
import org.mandarax.dsl.ObjectDeclaration;
import org.mandarax.dsl.RelationshipDefinition;
import org.mandarax.dsl.Rule;
import org.mandarax.dsl.FunctionInvocation;
import org.mandarax.dsl.Variable;

import static org.mandarax.compiler.impl.CompilerUtils.*;

/**
 * Algorithm to organise the prerequisites in rules in order to optimse code generation. 
 * This is a singleton.
 * @author jens dietrich
 */
public class Scheduler {
	
	public static Scheduler DEFAULT = new Scheduler();
	
	private Scheduler() {
		super();
	}

	/**
	 * Rearrange the body of the rule.
	 * @param rule
	 * @return
	 * @throws CompilerException
	 */
	public List<Prereq> getPrerequisites(Rule rule,FunctionDeclaration query) throws CompilerException {
	
		Collection<String> variables = initVariables(rule,query);
		
		List<Expression> body = new ArrayList<Expression>();
		body.addAll(rule.getBody());
		Collections.sort(body,new Comparator<Expression>(){
			@Override
			public int compare(Expression o1, Expression o2) {
				int r1 = (o1 instanceof FunctionInvocation)?1:0;
				int r2 = (o2 instanceof FunctionInvocation)?1:0;
				return r2-r1;
			}});
		
		List<Prereq> prereqs = new ArrayList<Prereq>(rule.getBody().size());
		Prereq last = null;
		while (!body.isEmpty()) {
			last = addAllResolved(body,prereqs,variables,last);
			last = addOneUnresolved(body,prereqs,variables,last);
		}
		
		return prereqs;
	}
	
	private Prereq addOneUnresolved(List<Expression> body, List<Prereq> prereqs,Collection<String> variables,Prereq last) {
		if (body.isEmpty()) return last;
		
		// TODO: optimise - sort first
		Expression selected = body.remove(0);
		
		Prereq prereq = new Prereq();
		prereq.setPrevious(last);
		prereq.setExpression(selected);
		List<String> newVariables = getUnresolvedVariables(selected,variables);
		prereq.setNewlyBoundVariables(newVariables);
		variables.addAll(newVariables);
		Collection<String> vars = new LinkedHashSet<String>();
		vars.addAll(variables);
		prereq.setBoundVariables(vars);
		prereqs.add(prereq);
		return prereq;
		
	}
	
	private List<String> getUnresolvedVariables(Expression expression,Collection<String> resolvedVariables) {
		List<String> varNames = new ArrayList<String>();
		for (String varName:getNames(expression.getVariables())) {
			if (!resolvedVariables.contains(varName)) {
				varNames.add(varName);
			}
		}
		return varNames;
	}

	// add the prereqs for which all variables are known
	private Prereq addAllResolved(List<Expression> body, List<Prereq> prereqs,Collection<String> variables,Prereq last) {
		for (Iterator<Expression> iter = body.iterator();iter.hasNext();) {
			Expression expression = iter.next();
			if (variables.containsAll(getNames(expression.getVariables()))) {
				Prereq prereq = new Prereq();
				prereq.setPrevious(last);
				last = prereq;
				prereq.setNewlyBoundVariables(new ArrayList<String>());
				prereq.setExpression(expression);
				prereqs.add(prereq);
				iter.remove();
				Collection<String> vars = new LinkedHashSet<String>();
				vars.addAll(variables);
				prereq.setBoundVariables(vars);
			}
		}
		return last;
	}

	private Collection<String> initVariables(Rule rule,FunctionDeclaration query) throws CompilerException {
		Collection<String> variables = new HashSet<String>();
		
		// add references to object declaration 
		for (ObjectDeclaration objDecl:rule.getContext().getObjectDeclarations()) {
			variables.add(objDecl.getName());
		}
		
		// add vars from rule head that are query parameters
		RelationshipDefinition rel = query.getRelationship();
		boolean[] sign = query.getSignature();
		for (int i=0;i<sign.length;i++) {
			Expression param = rule.getHead().getParameters().get(i);
			if (sign[i]) {
				if (param instanceof Variable) {
					variables.add(((Variable) param).getName());
				}
				else if (param instanceof Literal) {
					// ignore
				}
				else {
					throw new CompilerException("Only flat terms are allowed in rule heads. Found term " + param + " in rule " + rule);
				}
			}
		}
		
		return variables;
	}
}
