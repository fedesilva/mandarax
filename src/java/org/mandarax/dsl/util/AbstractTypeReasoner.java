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

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.mandarax.dsl.*;

/**
 * Abstract superclass that can be used to implement TypeReasoners.
 * @author jens dietrich
 */
public abstract class AbstractTypeReasoner implements TypeReasoner {

	@Override
	public Class getType(Expression expression,Resolver resolver) throws TypeReasoningException {
		try {
			Class clazz = expression.getClass();
			Method printer = this.getClass().getMethod("getType",new Class[]{clazz,Resolver.class});
			return (Class) printer.invoke(this,new Object[]{expression,resolver});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void exception(Object... tokens) throws TypeReasoningException {
		StringBuffer b = new StringBuffer();
		for (Object s:tokens) b.append(s);
		throw new TypeReasoningException(b.toString());
	}
	private void exception(Exception cause,Object... tokens) throws TypeReasoningException {
		StringBuffer b = new StringBuffer();
		for (Object s:tokens) b.append(s);
		throw new TypeReasoningException(b.toString(),cause);
	}
	private void failed(Expression expression,String reason,Exception cause) throws TypeReasoningException {
		exception(cause,"Cannot calculate type for expression expression ",expression," defined in ",expression.getPosition(),(reason==null?"":(" - "+reason)));
	}
	private void failed(Expression expression,String reason) throws TypeReasoningException {
		exception("Cannot calculate type for expression expression ",expression," defined in ",expression.getPosition(),(reason==null?"":(" - "+reason)));
	}
	private void failed(Expression expression) throws TypeReasoningException {
		failed(expression,null);
	}
	private void mismatch(Expression expression,Class expectedType,Class foundType) throws TypeReasoningException {
		exception("Expression ",toString(expression)," defined in ",expression.getPosition()," is of type ",foundType," but expected type is ",expectedType);
	}
	private String toString(Expression x) {
		return x.toString();
	}

	public Class getType (BinaryExpression expression,Resolver resolver) throws TypeReasoningException{
		Class type1 = getType(expression.getLeft(),resolver);
		Class type2 = getType(expression.getRight(),resolver);
		BinOp op = expression.getOperator();
		
		// boolean operators
		if (op==BinOp.AND || op==BinOp.OR) {
			if (type1!=Boolean.class) mismatch(expression.getLeft(),Boolean.class,type1);
			if (type2!=Boolean.class) mismatch(expression.getRight(),Boolean.class,type2);
			return Boolean.class;
		}
		// string concat, convert second argument to string using toString()
		if (op==BinOp.PLUS && type1==String.class) {
			return String.class;
		} 
		// arithmentic
		if (op==BinOp.PLUS || op==BinOp.TIMES || op==BinOp.DIV || op==BinOp.MOD) {
			if (isNumType(type1) && isNumType(type2)) {
				return getNumericCompositionType(type1,type2);
			}
		}
		
		failed(expression);
		return null; // will never reach this
		
	}
	public Class getType (BooleanLiteral expression,Resolver resolver) throws TypeReasoningException{
		return Boolean.class;
	}
	public Class getType (CastExpression expression,Resolver resolver) throws TypeReasoningException{
		try {
			return resolver.getType(expression.getType());
		} catch (ResolverException e) {
			failed(expression,null,e);
		}
		return null; // will never reach this
	}
	public Class getType (ConditionalExpression expression,Resolver resolver) throws TypeReasoningException{
		Class type1 = getType(expression.getCondition(),resolver);
		if (type1!=Boolean.class) mismatch(expression.getCondition(),Boolean.class,type1);
		
		Class type2 = getType(expression.getIfTrue(),resolver);
		Class type3 = getType(expression.getIfTrue(),resolver);
		
		if (type2==type3) return type2;
		else if (type2.isAssignableFrom(type3)) return type2;
		else if (type3.isAssignableFrom(type2)) return type3;
		else if (isNumType(type1) && isNumType(type2)) {
			return getNumericCompositionType(type1,type2);
		}
		else {
			mismatch(expression.getIfFalse(),type2,type3);
			return null; // will never reach this
		}
		
	}
	public Class getType (InstanceOfExpression expression,Resolver resolver) {
		return Boolean.class;
	}
	public Class getType (IntLiteral expression,Resolver resolver) {
		return Integer.class;
	}
	public Class getType (MemberAccess expression,Resolver resolver) throws TypeReasoningException {
		String name = expression.getMember();
		Class type = getType(expression.getObjectReference(),resolver);
		List<String> paramTypes = new ArrayList<String>();
		for (Expression param:expression.getParameters()) {
			paramTypes.add(getType(param,resolver).getName());
		}
		
		Member member = null;
		try {
			String[] paramNames = expression.isMethod()?paramTypes.toArray(new String[paramTypes.size()]):null;
			member = resolver.getMember(name,type.getName(),paramNames);
		} catch (ResolverException e) {
			failed(expression,null,e);
		}
		if (member==null) {
			failed(expression,"cannot find member " + name + " in type " + type.getName());
		}
		else if (member instanceof Method) {
			return ((Method)member).getReturnType();
		} 
		else if (member instanceof Field) {
			return ((Field)member).getType();
		}
		failed(expression);
		return null; // will never reach this
		
	}
	public Class getType (StringLiteral expression,Resolver resolver) {
		return String.class;
	}
	public Class getType (UnaryExpression expression,Resolver resolver) throws TypeReasoningException{
		UnOp op = expression.getOperator();
		Class type = getType(expression.getPart(),resolver);
		if (op==UnOp.NOT) {
			if (type==Boolean.class) return Boolean.class;
			else mismatch(expression.getPart(),Boolean.class,type);
		}
		else if (op==UnOp.MINUS) {
			if (isNumType(type)) return type;
			else failed(expression.getPart(),"numeric type expected here");
		}
		else if (op==UnOp.COMPL) {
			if (isIntType(type)) {
				if (type==Long.class) return Long.class;
				else return Integer.class;
			}
			else failed(expression.getPart(),"integer type expected here");
		}
		failed(expression);
		return null; // will never reach this
	}
	
	private boolean isIntType(Class type) {
		return type==Integer.class || type==Long.class || type==Short.class || type==Character.class || type==Byte.class;
	} 
	private boolean isNumType(Class type) {
		return type==Integer.class || type==Long.class || type==Short.class || type==Character.class || type==Byte.class || type==Double.class || type==Float.class;
	} 
	private Class getNumericCompositionType(Class type1,Class type2) {
		assert (isNumType(type1));
		assert (isNumType(type2));
		if (isIntType(type1) && isIntType(type2)) {
			if (type1==Long.class || type2==Long.class) {
				return Long.class;
			}
			else {
				return Integer.class;
			}
		}
		else if (type1==Float.class && type2==Float.class) {
			return Float.class;
		}
		else {
			return Double.class;
		}
	}

	public abstract Class getType (Variable expression,Resolver resolver) ;
	
}
