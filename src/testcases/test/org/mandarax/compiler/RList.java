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

package test.org.mandarax.compiler;

/**
 * Simple recursive list structure used in test cases.
 * @author jens dietrich
 */
public class RList {
	private Object head = null;
	private RList tail = null;
	
	public RList(Object head, RList tail) {
		super();
		this.head = head;
		this.tail = tail;
	}
	
	public RList(Object head) {
		super();
		this.head = head;
	}

	public Object getHead() {
		return head;
	}
	public void setHead(Object head) {
		this.head = head;
	}
	public RList getTail() {
		return tail;
	}
	public void setTail(RList tail) {
		this.tail = tail;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
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
		RList other = (RList) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (tail == null) {
			if (other.tail != null)
				return false;
		} else if (!tail.equals(other.tail))
			return false;
		return true;
	}
}
