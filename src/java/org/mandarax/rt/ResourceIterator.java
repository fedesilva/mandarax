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

import java.util.Iterator;
/**
 * Resource iterator.
 * This is an iterator that can be closed. The close operation releases resources, e.g.
 * closes database connections.
 * Note that close(), next() and hasNext() may throw unchecked exceptions IteratorClosedException.
 * @see DerivationException
 * @author jens dietrich
 */
public interface ResourceIterator<T> extends Iterator<T>{


	/**
	 * Close the iterator.
	 */
	public void close();



}
