package org.mandarax.compiler.impl;

/**
 * Simple counter utility used for code generation.
 * @author jens dietrich
 */
public class Counter {
	private int v = 0;
	public int getValue() {
		return v;
	}
	public int getNext() {
		v=v+1;
		return this.v;
	}
}
