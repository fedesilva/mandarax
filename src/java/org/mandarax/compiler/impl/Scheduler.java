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
import java.util.List;
import org.mandarax.compiler.CompilerException;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.ObjectDeclaration;
import org.mandarax.dsl.Rule;
import org.mandarax.dsl.FunctionInvocation;
import static org.mandarax.compiler.impl.CompilerUtils.*;

/**
 * Algorithm to organise the prerequisites in rules in order to optimse code generation. 
 * @author jens dietrich
 */
public class Scheduler {
	
	/**
	 * Rearrange the body of the rule.
	 * @param rule
	 * @return
	 * @throws CompilerException
	 */
	public List<Prereq> getPrerequisites(Rule rule) throws CompilerException {
	
		Collection<String> variables = initVariables(rule);
		
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
		
		while (!body.isEmpty()) {
			addAllResolved(body,prereqs,variables);
			addOneUnresolved(body,prereqs,variables);
		}
		
		return prereqs;
	}
	
	private void addOneUnresolved(List<Expression> body, List<Prereq> prereqs,Collection<String> variables) {
		if (body.isEmpty()) return;
		
		// TODO: optimise - sort first
		Expression selected = body.remove(0);
		
		Prereq prereq = new Prereq();
		prereq.setExpression(selected);
		List<String> newVariables = getUnresolvedVariables(selected,variables);
		prereq.setNewlyBoundVariables(newVariables);
		variables.addAll(newVariables);

		prereqs.add(prereq);
		
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
	private void addAllResolved(List<Expression> body, List<Prereq> prereqs,Collection<String> variables) {
		for (Iterator<Expression> iter = body.iterator();iter.hasNext();) {
			Expression expression = iter.next();
			if (variables.containsAll(getNames(expression.getVariables()))) {
				Prereq prereq = new Prereq();
				prereq.setNewlyBoundVariables(new ArrayList<String>());
				prereq.setExpression(expression);
				prereqs.add(prereq);
				iter.remove();
			}
		}
	}

	private Collection<String> initVariables(Rule rule) {
		Collection<String> variables = new HashSet<String>();
		
		// add references to object declaration 
		for (ObjectDeclaration objDecl:rule.getContext().getObjectDeclarations()) {
			variables.add(objDecl.getName());
		}
		
		// add vars from rule head
		variables.addAll(getNames(rule.getHead().getVariables()));
		
		return variables;
	}
}
