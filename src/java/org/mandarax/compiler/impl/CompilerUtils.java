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

package org.mandarax.compiler.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mandarax.dsl.Variable;

import com.google.common.collect.Collections2;

/**
 * Utilities.
 * @author jens dietrich
 */
public class CompilerUtils {
	public static final DateFormat DEFAULT_DATE_FORMAT = DateFormat.getDateTimeInstance();
	public static final DateFormat VERSION_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	public static String getTimestamp() {
		return DEFAULT_DATE_FORMAT.format(new Date());
	}
	
	public static String getTimestampAsVersion() {
		return VERSION_DATE_FORMAT.format(new Date());
	}
	// used in templates to iterate over lists by index
	public static List<Integer> getIndices(List list) {
		List<Integer> indices = new ArrayList<Integer>(list.size());
		for (int i=0;i<list.size();i++) indices.add(i);
		return indices;
	}
	// get the variable names
	// try to retain the order, but avoid duplicates
	public static List<String> getNames(List<Variable> variables) {
		List<String> names =  new ArrayList<String>();
		for (Variable var:variables) {
			if (!names.contains(var.getName())) names.add(var.getName());
		}
		return names;
	}
}
