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
import test.org.mandarax.compiler.externalfacts1.FatherRel;

/**
 * External fact set used in test cases.
 * @author jens dietrich
 */

public class FatherRelRecords1 implements Iterable<FatherRel>{
	private List<FatherRel> data = null;

	public FatherRelRecords1() {
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
	
}
