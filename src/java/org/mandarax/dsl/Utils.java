package org.mandarax.dsl;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Utilities to map strings to operators and vice versa.
 * @author jens dietrich
 */

public class Utils {
	
	private static BiMap<String,BinOp> binOps =HashBiMap.<String,BinOp>create(21);
	private static BiMap<String,UnOp> unOps =HashBiMap.<String,UnOp>create(21);
	
	static {
		binOps.put("+",BinOp.PLUS);
		binOps.put("-",BinOp.MINUS);
		binOps.put("*",BinOp.TIMES);
		binOps.put("/",BinOp.DIV);
		binOps.put("%",BinOp.MOD);
		binOps.put("==",BinOp.EQ);
		binOps.put("!=",BinOp.NEQ);
		binOps.put("|",BinOp.OR);
		binOps.put("&",BinOp.AND);
		binOps.put("<",BinOp.LT);
		binOps.put(">",BinOp.GT);
		binOps.put("<=",BinOp.LTE);
		binOps.put(">=",BinOp.GTE);
		binOps.put(">>>",BinOp.SHIFT_RRR);
		binOps.put(">>",BinOp.SHIFT_RR);
		binOps.put("<<",BinOp.SHIFT_LL);
		binOps.put("+",BinOp.PLUS);
		binOps.put("-",BinOp.MINUS);
		binOps.put("*",BinOp.TIMES);
		binOps.put("%",BinOp.MOD);
		binOps.put("/",BinOp.DIV);	
		
		unOps.put("-",UnOp.MINUS);	
		unOps.put("!",UnOp.NOT);
		unOps.put("~",UnOp.COMPL);
	}
	public static BinOp binOpForName(String t) {
		BinOp op = binOps.get(t);
		if (op==null)
			throw new IllegalArgumentException("no binary operator found for name " + t);
		return op;
	}
	public static String nameForBinOp(BinOp op) {
		String name = binOps.inverse().get(op);
		if (name==null)
			throw new IllegalArgumentException("no name found for operator " + op);
		return name;
	}
	public static UnOp unOpForName(String t) {
		UnOp op = unOps.get(t);
		if (op==null)
			throw new IllegalArgumentException("no unary operator found for name " + t);
		return op;
	}
	public static String nameForUnOp(UnOp op) {
		String name = unOps.inverse().get(op);
		if (name==null)
			throw new IllegalArgumentException("no name found for operator " + op);
		return name;
	}

	
}
