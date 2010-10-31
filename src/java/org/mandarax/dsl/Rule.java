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
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import static org.mandarax.dsl.Utils.*;

/**
 * Represents a rule.
 * @author jens dietrich
 */
public class Rule extends AnnotatableNode implements Cloneable {
	
	private String id = null;
	private List<Expression> body = null;
	private FunctionInvocation head = null;
	// variable renamings in the rule head, will be set by relationship definition e.g., in 
	// rel Father(MalePerson father,Person child) extends Parent queries getFather(child),isFather(father,child) {
	// rule1: Son(c,f) -> Father(f,c);}
	// has a mapping {c->child,f->father} 
	private Map<String,String> variableMappingsInHead = null;


	public Rule(Position position, Context context,String id,Expression body,FunctionInvocation head) {
		super(position, context);
		this.id = id;
		this.body = flatten(body);
		this.head = head;
		
		// check whether head is flat
//		for (Expression term:head.getParameters()) {
//			if (!term.isFlat()) {
//				throw new InternalScriptException("Only flat terms (variables and terms) are allowed in rule heads, but this rule is violated by " + term + " " + term.getPosition());
//			}
//		}
		
	}
	
	private Rule(Position position, Context context,String id,List<Expression> body,FunctionInvocation head) {
		super(position, context);
		this.id = id;
		this.body = body;
		this.head = head;
		
	}


	/**
	 * Flatten a conjunction.
	 * @param expr
	 * @return
	 */
	private List<Expression> flatten(Expression expr) {
		if (expr==null) {
			return new ArrayList<Expression>(0);
		}
		else if (expr instanceof BinaryExpression && ((BinaryExpression)expr).getOperator()==BinOp.AND) {
			BinaryExpression bexpr = (BinaryExpression)expr;
			List<Expression> list = new ArrayList<Expression>();
			list.addAll(flatten(bexpr.getLeft()));
			list.addAll(flatten(bexpr.getRight()));
			return list;
		}
		else {
			List<Expression> list = new ArrayList<Expression>(1);
			list.add(expr);
			return list;
		}
	}

	public void accept(ASTVisitor visitor) {
		if (visitor.visit(this)) {
			head.accept(visitor);
			for (Expression e:this.body) e.accept(visitor);
		}
		visitor.endVisit(this);
	}

	public String getId() {
		return id;
	}
	


	public List<Expression> getBody() {
		return body;
	}

	public FunctionInvocation getHead() {
		return head;
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(id);
		b.append(": ");
		this.appendList(body, b,false," & ") ; 
		b.append(" -> ");
		b.append(head);
		b.append(';');
		return b.toString();
	}
	
	public boolean isFact() {
		return this.body==null || this.body.isEmpty();
	}

	public Map<String, String> getVariableMappingsInHead() {
		return variableMappingsInHead;
	}

	public void setVariableMappingsInHead(Map<String, String> variableMappingsInHead) {
		this.variableMappingsInHead = variableMappingsInHead;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public Rule clone() {
		return new Rule(getPosition(),getContext(),id,Lists.transform(body,new Function<Expression,Expression>() {
			@Override
			public Expression apply(Expression x) {
				return x.substitute(NO_SUBTITUTIONS);
			}}),
		(FunctionInvocation)head.substitute(NO_SUBTITUTIONS));
	}

	public void addToBody(Expression expression) {
		body.add(expression);
	}
}
