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

package test.org.mandarax.compiler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import test.org.mandarax.compiler.externalfacts2.FatherRel;

/**
 * External fact set used in test cases.
 * This class has methods that retrieve records for sets of query parameters. 
 * The compiler must recognize those methods, and generate code using them. 
 * @author jens dietrich
 */

public class FatherRelRecords2 implements Iterable<FatherRel>{
	private List<FatherRel> data = null;

	public FatherRelRecords2() {
		super();
		data = new ArrayList<FatherRel>();
		data.add(new FatherRel("Jens","Max"));
		data.add(new FatherRel("Jens","Xiomara"));
		data.add(new FatherRel("Klaus","Jens"));
		data.add(new FatherRel("Otto","Klaus"));
	}

	@Override
	public Iterator<FatherRel> iterator() {
		return data.iterator();
	}
	
	public Iterator<FatherRel> getFather(final String child) {
		Iterator<FatherRel> iterator = data.iterator();
		Predicate<FatherRel> filter = new Predicate<FatherRel>() {
			@Override
			public boolean apply(FatherRel rel) {
				return rel.child.equals(child);
			}
			
		};
		return Iterators.filter(iterator,filter);
	}
	
	public Iterator<FatherRel> isFather(final String father,final String child) {
		Iterator<FatherRel> iterator = data.iterator();
		Predicate<FatherRel> filter = new Predicate<FatherRel>() {
			@Override
			public boolean apply(FatherRel rel) {
				return rel.child.equals(child) && rel.father.equals(father);
			}
			
		};
		return Iterators.filter(iterator,filter);
	}
	
}
