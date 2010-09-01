package org.mandarax.dsl.util;

import java.io.PrintStream;

import org.mandarax.dsl.*;

/**
 * Utility to print the AST structure of an expression.
 * @author jens dietrich
 */

public class ExpressionStructurePrinter implements Visitor {

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
	public void endVisit(StringLiteral x) {}

	@Override
	public void endVisit(UnaryExpression x) {
		u();
	}

	@Override
	public void endVisit(Variable x) {}

}
