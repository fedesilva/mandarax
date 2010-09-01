package org.mandarax.dsl.util;

import java.io.PrintStream;
import java.lang.reflect.Method;

import org.mandarax.dsl.*;

import static org.mandarax.dsl.Utils.*;

/**
 * Utility to print expressions.
 * @author jens dietrich
 */

public class ExpressionPrinter {
	private PrintStream out = System.out; 
	
	public void print(Expression x) {
		
		try {
			Class clazz = x.getClass();
			Method printer = this.getClass().getMethod("print",new Class[]{clazz});
			printer.invoke(this,new Object[]{x});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void printPart(Expression x) {
		boolean nb = x.isFlat() || x instanceof MemberAccess;
		if (!nb) out.print('(');
		print(x);
		if (!nb) out.print(')');
	}
	
	public void print(BinaryExpression x) {
		print(x.getLeft());
		out.print(" ");
		out.print(nameForBinOp(x.getOperator()));
		out.print(" ");
		print(x.getRight());
	}
	public void print(BooleanLiteral x) {
		out.print(x.getValue());
	}
	public void print(CastExpression x) {
		out.print('(');
		out.print(x.getType());
		out.print(')');
		printPart(x.getObjectReference());
	}
	public void print(ConditionalExpression x) {
		printPart(x.getCondition());
		out.print('?');
		printPart(x.getIfTrue());
		out.print(':');
		printPart(x.getIfFalse());
	}
	public void print(InstanceOfExpression x) {
		printPart(x.getObjectReference());
		out.print(" instanceof ");
		out.print(x.getType());		
	}
	public void print(IntLiteral x) {
		out.print(x.getValue());
	}
	public void print(MemberAccess x) {
		printPart(x.getObjectReference());
		out.print('.');
		out.print(x.getMember());
		if (x.isMethod()) {
			out.print('(');
			boolean f = true;
			for (Expression p:x.getParameters()) {
				if (f) f = false;
				else out.print(',');
				printPart(p);
			}
			out.print(')');
		}
	}

	public void print(StringLiteral x) {
		out.print("\"");
		out.print(x.getValue()); // TODO escape chars
		out.print("\"");
	}
	public void print(UnaryExpression x) {
		out.print(nameForUnOp(x.getOperator()));
		printPart(x.getPart());
	}
	public void print(Variable x) {
		out.print(x.getName());
	}
	
}
