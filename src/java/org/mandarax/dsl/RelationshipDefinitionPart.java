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
 * Abstract superclass for artefacts used to define the semantics of relationships.
 * @author jens dietrich
 */
public abstract class RelationshipDefinitionPart extends AnnotatableNode implements Cloneable {
	
	protected String id = null;
	
	public String getId() {
		return id;
	}

	public RelationshipDefinitionPart(Position position, Context context,String id) {
		super(position, context);
		this.id = id;
	}
	
}
