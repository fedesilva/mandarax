/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package org.mandarax.dsl.util;

import java.io.PrintStream;

import org.mandarax.dsl.*;

/**
 * Utility to print the AST structure of an expression.
 * @author jens dietrich
 */

public class ExpressionStructurePrinter implements ExpressionVisitor {

	private PrintStream out = System.out;
	private int indent = 0; 
	private int maxIndent = 5;
	
	private void d() {indent = indent+1;}
	private void u() {indent = indent-1;}
	private void i() {
		for (int i=0;i<indent;i++) out.print('\t');
	}
	private void annotate(Expression x) {
		int ind = maxIndent-indent;
		if (ind<1) ind=1;
		for (int i=0;i<ind;i++) out.print('\t');
		out.print("// ");
		out.print(x.getClass().getName());
		out.print("  (");
		out.print(x.getPosition().getLine());
		out.print("  ");
		out.print(x.getPosition().getCharPosInLine());
		out.println(")");
	}
	
	public void println() {
		out.println();
	}
	@Override
	public boolean visit(BinaryExpression x) {
		i();
		out.print(x.getOperator());
		annotate(x);
		d();
		return true;
	}

	@Override
	public boolean visit(BooleanLiteral x) {
		i();
		out.print(x.getValue());
		annotate(x);
		return true;
	}


	@Override
	public boolean visit(CastExpression x) {
		i();
		out.print("cast to "+x.getType());
		annotate(x);
		d();
		return true;
	}

	@Override
	public boolean visit(ConditionalExpression x) {
		i();
		out.print("conditional");
		annotate(x);
		d();
		return true;
	}

	@Override
	public boolean visit(InstanceOfExpression x) {
		i();
		out.print("instance of "+x.getType());
		annotate(x);
		d();
		return true;
	}

	@Override
	public boolean visit(IntLiteral x) {
		i();
		out.print(x.getValue());
		annotate(x);
		return true;
	}

	@Override
	public boolean visit(MemberAccess x) {
		i();
		out.print(x.getMember());
		annotate(x);
		d();
		return true;
	}
	
	@Override
	public boolean visit(FunctionInvocation x) {
		i();
		out.print(x.getFunction());
		annotate(x);
		d();
		return true;
	}


	@Override
	public boolean visit(StringLiteral x) {
		i();
		out.print(x.getValue());
		annotate(x);
		return true;
	}

	@Override
	public boolean visit(UnaryExpression x) {
		i();
		out.print(x.getOperator());
		annotate(x);
		d();
		return true;
	}

	@Override
	public boolean visit(Variable x) {
		i();
		out.print(x.getName());
		annotate(x);
		return true;
	}

	@Override
	public void endVisit(BinaryExpression x) {
		u();
	}

	@Override
	public void endVisit(BooleanLiteral x) {}

	@Override
	public void endVisit(CastExpression x) {
		u();
	}

	@Override
	public void endVisit(ConditionalExpression x) {
		u();
	}

	@Override
	public void endVisit(InstanceOfExpression x) {
		u();
	}

	@Override
	public void endVisit(IntLiteral x) {}

	@Override
	public void endVisit(MemberAccess x) {
		u();
	}

	@Override
	public void endVisit(FunctionInvocation x) {
		u();
	}

	@Override
	public void endVisit(StringLiteral x) {}

	@Override
	public void endVisit(UnaryExpression x) {
		u();
	}

	@Override
	public void endVisit(Variable x) {}

}
