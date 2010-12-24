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

package org.mandarax.dsl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.common.base.Function;
import static org.mandarax.dsl.Utils.*;

/**
 * Represents external facts imported using an iterable.
 * @author jens dietrich
 */
public class ExternalFacts extends RelationshipDefinitionPart {
	
	private Expression iterable = null;
	private FunctionInvocation head = null;

	public ExternalFacts(Position position, Context context,String id,Expression iterable) {
		super(position, context,id);
		this.iterable = iterable;
	}
	
	
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

	public Expression getIterable() {
		return iterable;
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(id);
		b.append(": include ");
		b.append(iterable.toString());
		return b.toString();
	}
		
	@Override
	public ExternalFacts clone() {
		ExternalFacts r = new ExternalFacts(getPosition(),getContext(),id,iterable.substitute(NO_SUBTITUTIONS));
		r.copyPropertiesTo(this);
		return r;
	}
	
	public ExternalFacts substitute(final Map<Expression,? extends Expression> substitutions) {
		return this.clone();
	}

	
}
