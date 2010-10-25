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

import com.google.common.base.Predicate;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Simple filtered iterator.
 * @author jens dietrich
 */
public class FilteredIterator<T> extends com.google.common.collect.AbstractIterator<T> implements ResourceIterator<T> {
	private ResourceIterator<T> unfiltered = null;
	private Predicate<? super T> predicate = null;
	private boolean closed = false;

	public FilteredIterator(ResourceIterator<T> unfiltered, Predicate<? super T> predicate) {
		super();
		this.unfiltered = unfiltered;
		this.predicate = predicate;
	}


	@Override
	protected T computeNext() {
		if (closed) throw new IteratorClosedException();
		
		checkNotNull(unfiltered);
		checkNotNull(predicate);
		while (unfiltered.hasNext()) {
			T element = unfiltered.next();
			if (predicate.apply(element)) {
				return element;
			}
		}
		return endOfData();
	}

	@Override
	public synchronized void close() {
		closed = true;
		unfiltered.close();
	}

	
}
