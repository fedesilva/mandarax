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
 * Compares objects and primitives for equality.
 * @author jens dietrich
 */
final public class Equals {
	public static boolean compare(Object o1,Object o2) {
		if (o1==null) return (o2==null);
		else return o1.equals(o2);
	}
	public static boolean compare(int v1,int v2) {
		return v1==v2;
	}
	public static boolean compare(short v1,short v2) {
		return v1==v2;
	}
	public static boolean compare(long v1,long v2) {
		return v1==v2;
	}
	public static boolean compare(byte v1,byte v2) {
		return v1==v2;
	}
	public static boolean compare(char v1,char v2) {
		return v1==v2;
	}
	public static boolean compare(double v1,double v2) {
		return v1==v2;
	}
	public static boolean compare(float v1,float v2) {
		return v1==v2;
	}
	public static boolean compare(boolean v1,boolean v2) {
		return v1==v2;
	}
}
