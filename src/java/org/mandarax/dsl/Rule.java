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

/**
 * Represents a rule.
 * @author jens dietrich
 */
public class Rule extends AnnotatableNode {
	
	private String id = null;
	private List<Expression> body = null;
	private FunctionInvocation head = null;

	public Rule(Position position, Context context,String id,Expression body,FunctionInvocation head) {
		super(position, context);
		this.id = id;
		this.body = flatten(body);
		this.head = head;
		
		// check whether head is flat
		for (Expression term:head.getParameters()) {
			if (!term.isFlat()) {
				throw new InternalScriptException("Only flat terms (variables and terms) are allowed in rule heads, but this rule is violated by " + term + " " + term.getPosition());
			}
		}
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
	
	protected void appendTo(StringBuffer b) {
		b.append(id);
		b.append(": ");
		this.appendListOfNodes(body, b,false," & ") ; 
		b.append(" -> ");
		head.appendTo(b);
		b.append(';');
	}
	
	public boolean isFact() {
		return this.body==null || this.body.isEmpty();
	}

}
