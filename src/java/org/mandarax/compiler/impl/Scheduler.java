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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.mandarax.compiler.CompilerException;
import org.mandarax.dsl.BinOp;
import org.mandarax.dsl.BinaryExpression;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.FunctionDeclaration;
import org.mandarax.dsl.ObjectDeclaration;
import org.mandarax.dsl.Rule;
import org.mandarax.dsl.FunctionInvocation;
import org.mandarax.dsl.Variable;
import org.mandarax.dsl.util.Resolver;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import static org.mandarax.dsl.Position.*;

/**
 * Algorithm to organise the prerequisites in rules in order to optimise code generation. 
 * @author jens dietrich
 */
public class Scheduler {
	
	public static org.apache.log4j.Logger LOGGER = Logger.getLogger(Scheduler.class);
	public static final String ASSERTED_BY_COMPILER = "asserted_by_compiler" ;
	private Resolver resolver = null;
	private Rule originalRule = null;
	private Rule rule = null;
	private FunctionDeclaration query = null;
	private List<Prereq> prereqs = null;
	
	
	// this are the expressions in the head of the rule that are not bound by query parameters,
	// they must be bound by the query, if not, an exception must be throws 
	// see also issue8/case1
	private Collection<Expression> mustBeBound = new HashSet<Expression>();
	
	// bound variables
	private Collection<Expression> boundVariables  = new HashSet<Expression>();
	
	// this map is used to store association between (complex) terms and (existing and newly introduced) variables
	// replacing them
	private Map<Expression,Variable> substitutions = new HashMap<Expression,Variable>();
	
	// the expression in the body currently investigated
	private Expression selected = null;
	
	private int counter=0;
	
	public Scheduler(Resolver resolver,Rule rule,FunctionDeclaration query) throws CompilerException {
		super();
		this.resolver = resolver;
		this.rule = rule;
		this.originalRule = rule;
		this.query = query;
		this.prereqs = new ArrayList<Prereq>(rule.getBody().size());
		
		schedule();
	}

	/**
	 * Prepare the rule for computation.
	 * @throws CompilerException
	 */
	private void schedule() throws CompilerException {
		
		LOGGER.info("Scheduling prerequisites in " + rule);
		
		// clone rule - we might have to add additional expressions to the body, see for instance issue8/case4 for an example
		String oldRule = rule.toString();
		
		rule = rule.clone();
		
		initVariables();
	
		
		List<Expression> body = new ArrayList<Expression>();
		body.addAll(rule.getBody());
		Collections.sort(body,new Comparator<Expression>(){
			@Override
			public int compare(Expression o1, Expression o2) {
				int r1 = (o1 instanceof FunctionInvocation)?1:0;
				int r2 = (o2 instanceof FunctionInvocation)?1:0;
				return r2-r1;
			}});
		
		
		Prereq last = null;
		
		// main algorithm
		while (!body.isEmpty()) {
			last = addAllResolved(body,last);
			last = addOneUnresolved(body,last);
		}
		
		
		LOGGER.debug("Finished scheduling rule " + oldRule + " for query " + query + " / relationship " + query.getRelationship());
		LOGGER.debug("Rule is now: " + this.rule);
		LOGGER.debug("Prerequisites: " + this.prereqs);
		
		// check whether all variables in head have been bound
		Collection<Expression> unbound = Collections2.filter(mustBeBound, new Predicate<Expression>() {
			@Override
			public boolean apply(Expression x) {
				return !x.isGroundWRT(boundVariables);
			}});
		
		if (!unbound.isEmpty()) {
			throw new CompilerException("Cannot compile rule " + this.originalRule + ", the following terms in the rule head cannot be bound: " + unbound);
			
		}
	}
	
	private Prereq addOneUnresolved(List<Expression> body, Prereq last) {
		if (body.isEmpty()) return last;
		
		// TODO: optimise - sort first
		selected = body.remove(0);
		
		Collection<Expression> newVariables = getUnresolvedVariables(selected,body);
		
		Prereq prereq = new Prereq();
		prereq.setPrevious(last);
		prereq.setExpression(selected);
		
		prereq.setNewlyBoundVariables(newVariables);
		boundVariables.addAll(newVariables);
		Collection<Expression> bound = new LinkedHashSet<Expression>();
		bound.addAll(boundVariables);
		prereq.setBoundVariables(bound);
		prereqs.add(prereq);
		
		LOGGER.debug("Adding prerequisite " + prereq.getExpression() + ", newely bound variables: " + prereq.getNewlyBoundVariables());
		
		return prereq;
		
	}
	// get the parts not yet resolved in an expression
	private Collection<Expression> getUnresolvedVariables(Expression expression,List<Expression> body) {
		Collection<Expression> unresolved = new LinkedHashSet<Expression>();
		boolean mustApplySubstitutions = false;
		for (Expression child:expression.getChildren()) {
			if (!child.isGroundWRT(boundVariables)) {
				LOGGER.debug("Found unresolved term " + child + " in expression " + expression);
				if (!(child instanceof Variable)) {
					// unbound complex term - reuse by introduce new variable
					Variable v = this.substitutions.get(child);
					if (v==null) {
						v = createVariable(child);
						mustApplySubstitutions = true;
						unresolved.add(v);
						v.setProperty(ASSERTED_BY_COMPILER, true);
					}
				}
				else {
					unresolved.add(child);
				}
			}
		}
		
		if (mustApplySubstitutions) {
			applySubstitutions(body);
		}
		
		return unresolved;
	}

	// add the prereqs for which all variables are known
	private Prereq addAllResolved(List<Expression> body, Prereq last) {
		for (Iterator<Expression> iter = body.iterator();iter.hasNext();) {
			Expression expression = iter.next();
			if (expression.isGroundWRT(boundVariables)) {
				selected=expression;
				Prereq prereq = new Prereq();
				prereq.setPrevious(last);
				last = prereq;
				prereq.setNewlyBoundVariables(new ArrayList<Expression>());
				prereq.setExpression(expression);
				prereqs.add(prereq);
				iter.remove();
				Collection<Expression> bound = new LinkedHashSet<Expression>();
				bound.addAll(boundVariables);
				prereq.setBoundVariables(bound);
				
				LOGGER.debug("Adding prerequisite " + prereq.getExpression() + ", newely bound variables: " + prereq.getNewlyBoundVariables());
			}
		}
		return last;
	}

	private void initVariables() throws CompilerException {
		
		// add references to object declaration 
		for (ObjectDeclaration objDecl:rule.getContext().getObjectDeclarations()) {
			Variable v = new Variable(objDecl.getPosition(),rule.getContext(),objDecl.getName());
			v.setType(resolver.getType(rule.getContext(),objDecl.getType()));
			boundVariables.add(v);
		}
		
		// add expressions from rule head that are query parameters
		boolean[] sign = query.getSignature();
		
		for (int i=0;i<sign.length;i++) {
			Expression param = rule.getHead().getParameters().get(i);
			if (sign[i]) {
				if (param instanceof Variable) {
					boundVariables.add(param);
				}
				else if (!param.isGround()) {
					// see also issue8/case4
					Variable var = createVariable(param);
					boundVariables.add(var);
					BinaryExpression constraint = new BinaryExpression(NO_POSITION,rule.getContext(),BinOp.EQ,var,param);
					constraint.setProperty(ASSERTED_BY_COMPILER,true);
					constraint.setType(Boolean.class);
					LOGGER.debug("Substitute term in head of rule " + rule.getId() + ": " + param + " -> " + var);
					LOGGER.debug("Creating new constraint in rule " + rule.getId() + ": " + constraint);
					rule.addToBody(constraint);					
				}
				
			}
			else {
				mustBeBound.add(param);				
			}
		}
		
		if (!substitutions.isEmpty()) {
			applySubstitutions(rule.getBody());
			// also apply to head
			rule = new Rule(rule.getPosition(),rule.getContext(),rule.getId(),rule.getBody(),(FunctionInvocation)rule.getHead().substitute(substitutions));
		}
		
		
	}
	private Variable createVariable(Expression toBeReplaced) {
		Variable v = new Variable(NO_POSITION,toBeReplaced.getContext(),createVarName());
		v.setType(toBeReplaced.getType());
		LOGGER.debug("Substitute term in body of rule " + rule.getId() + ": " + toBeReplaced + " -> " + v);
		substitutions.put(toBeReplaced,v);
		return v;
	}
	
	private String createVarName() {
		return "__t" + (counter++);
	}
	
	private void applySubstitutions(List<Expression> body) {
//		// apply to rule
//		List<Expression> newBody = new ArrayList<Expression>(rule.getBody().size());
//		for (Expression b:rule.getBody()) {
//			if (b.getProperty(ASSERTED_BY_COMPILER)==null) {
//				newBody.add(b.substitute(substitutions));
//			}
//			else {
//				newBody.add(b.clone());
//			}
//		}
//		
//		String oldRuleTxt = rule.toString();
//		rule =  new Rule(rule.getPosition(),rule.getContext(),rule.getId(),newBody,(FunctionInvocation)rule.getHead().substitute(substitutions));
		
		// apply to body
		if (body!=null) {
			for (int i=0;i<body.size();i++) {
				Expression b = body.get(i);
				if (b.getProperty(ASSERTED_BY_COMPILER)==null) {
					body.set(i,b.substitute(substitutions));
				}
			}
		}
		if (selected!=null && selected.getProperty(ASSERTED_BY_COMPILER)==null) {
			selected=selected.substitute(substitutions);
		}
		
		rule =  new Rule(rule.getPosition(),rule.getContext(),rule.getId(),Lists.transform(rule.getBody(),new Function<Expression,Expression>(){

			@Override
			public Expression apply(Expression x) {
				return (x.getProperty(ASSERTED_BY_COMPILER)==null)?x.substitute(substitutions):x;
			}}),(FunctionInvocation)rule.getHead().substitute(substitutions));
		
		
		LOGGER.debug("Applying substitution to rule " + rule);
	}

	/**
	 * Get the list of prerequisites.
	 * @return
	 */
	public List<Prereq> getPrerequisites() {
//		for (Prereq p:prereqs) {
//			Expression x = p.getExpression();
//			System.out.println(x + " : " + x.getTypeName());
//			for (Expression c:x.getAllChildren()) {
//				System.out.println(" " + c + " : " + c.getTypeName());
//			}
//		}
		return prereqs;
	}
	/**
	 * Get the rule optimised for code generation. 
	 * This means that the order of clauses in the body might have changed, and that there could be additional elements in the body.
	 * @return
	 */
	public Rule getRule() {
		return rule;
	}
}
