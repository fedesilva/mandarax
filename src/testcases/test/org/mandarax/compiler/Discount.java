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

/**
 * Domain class for testing.
 * @author jens dietrich
 */
public class Discount {
	private int value = 0;
	private boolean relative = false;
	
	public Discount(int value, boolean relative) {
		super();
		this.value = value;
		this.relative = relative;
	}

	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isRelative() {
		return relative;
	}
	public void setRelative(boolean relative) {
		this.relative = relative;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (relative ? 1231 : 1237);
		result = prime * result + value;
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
		Discount other = (Discount) obj;
		if (relative != other.relative)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
}
