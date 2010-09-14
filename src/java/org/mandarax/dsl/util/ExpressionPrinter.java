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
		out.print(x.getTypeName());
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
		out.print(x.getTypeName());		
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
	
	public void print(FunctionInvocation x) {
		out.print(x.getFunction());
		out.print('(');
		boolean f = true;
		for (Expression p:x.getParameters()) {
			if (f) f = false;
			else out.print(',');
			printPart(p);
		}
		out.print(')');
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
