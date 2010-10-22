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

package org.mandarax.dsl;

import com.google.common.base.Function;

/**
 * Represents annotations.
 * @author jens dietrich
 */
public class Annotation extends ASTNode {
	
	private String key = null;
	private String value = null;
	
	public Annotation(Position position, Context context, String key,String value) {
		super(position, context);
		this.key = key;
		this.value = value;
	}

	@Override
	public void appendTo(StringBuffer b,Function<Variable,String> conversion) {
		b.append('@');
		b.append(key);
		b.append("\"");
		b.append(value);
		b.append("\"");
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}
	
}
