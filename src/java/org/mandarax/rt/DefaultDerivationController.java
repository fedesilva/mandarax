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

package org.mandarax.rt;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Data structure to store the reference to elements in the knowledge base.
 * Used to record derivation trees, and can be used to cancel the inference process.
 * Recorded are string ids of the rules, facts etc, applications can use this strings to 
 * access artefacts in the knowledge base.
 * @author jens dietrich
 */

public class DefaultDerivationController  implements DerivationController {
	private List<String> ids = new ArrayList<String>();
	private List<Integer> types = new ArrayList<Integer>();
	private List<Properties> annotations = new ArrayList<Properties>();
	private int depth = 0;
	private boolean cancelled = false;
	private int derivationCount = 0;
	private DerivationListener derivationListener = null;
	
	private static final Properties NO_ANNOTATIONS = new Properties();
	/**
	 * Log the use of a clause set
	 * this implementation does not record the parameters
	 */
	public synchronized void log(String ruleRef,int kind,Properties annotations) {
		if (cancelled) 
			throw new DerivationCancelledException();
		
		// System.out.println("Log@" + depth + " : " + ruleRef);
		this.ids.add(depth,ruleRef);	
		this.types.add(depth,kind);
		this.annotations.add(annotations==null?NO_ANNOTATIONS:annotations);
		this.derivationCount = this.derivationCount+1;
		
		if (derivationListener!=null)
			derivationListener.step(ruleRef, depth, derivationCount);
	}
	/**
	 * Get a copy of the derivation log. 
	 * @return a list
	 */
	public synchronized List<DerivationLogEntry> getLog() {
		List<DerivationLogEntry> list = new ArrayList<DerivationLogEntry>();
		for (int i=0;i<=depth;i++) {
			String s = this.ids.get(i);
			if (s!=null) {
				list.add(new DerivationLogEntry(this.ids.get(i),this.types.get(i),this.annotations.get(i)));
			}
		}
			
		return list;
	}
	
	/**
	 * Print the log to a print stream.
	 * @param out a print stream 
	 */
	public synchronized void printLog(PrintStream out) {
		for (int i=0;i<=depth;i++) {
			String s = this.ids.get(i);
			if (s!=null) {
				out.print(i+1);
				out.print(". ");
				out.println(s);	
			}
		}
	}
	/**
	 * Print the log to System.out.
	 */
	public synchronized void printLog() {
		printLog(System.out);
	}
	/**
	 * Get the derivation level.
	 * @return
	 */
	public synchronized int size() {
		return depth;
	}
	/**
	 * Increase the derivation level.
	 * @return this
	 */
	public synchronized DefaultDerivationController push() {
		this.depth = depth+1;
		return this;
	}
	/**
	 * Reset the derivation level.
	 * @param value
	 * @return this
	 */
	public synchronized DefaultDerivationController pop(int value) {
		assert value<=depth;
		assert value>=0;
		this.depth = value;
		return this;
	}
	/**
	 * Cancel the derivation.
	 */
	public synchronized void cancel() {
		this.cancelled = true;
	}
	/**
	 * Whether the derivation has been cancelled.
	 * @return the cancelled status
	 */
	public synchronized boolean isCancelled() {
		return this.cancelled;
	}
	/**
	 * Get the number of derivation steps performed so far.
	 * @return an int
	 */
	public synchronized int getDerivationCount() {
		return derivationCount;
	}
	public synchronized DerivationListener getDerivationListener() {
		return derivationListener;
	}
	public synchronized void setDerivationListener(DerivationListener derivationListener) {
		this.derivationListener = derivationListener;
	}
}
