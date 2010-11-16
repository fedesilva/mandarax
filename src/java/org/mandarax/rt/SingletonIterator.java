/*
 * Copyright 2010 Jens Dietrich Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.gnu.org/licenses/agpl.html Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */


package org.mandarax.rt;


/**
 * Singleton iterator. This iterator index' a "Collection" containing only one single
 * object. This object can be accessed only one single time.
 * @author jens dietrich
 * @param <T> the type of the iterated element
 */
public class SingletonIterator<T> extends AbstractIterator<T> {
	private T object = null;
	private boolean closed = false;
	
	/**
	 * Construct a new <code>SingletonIterator</code> indexing the provided object.
	 * @param object the element indexed by this Iterator.
	 */
	public SingletonIterator(T object) {
		super();
		assert(object!=null);
		this.object = object;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return !closed && object!=null;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public T next() {
		if (closed) throw new IteratorClosedException();
		T next = object;
		object = null;
		return next;
	}

	/* (non-Javadoc)
	 * @see org.mandarax.compiler.rt.ResourceIterator#close()
	 */
	public void close() {
		closed = true;
	}




}
