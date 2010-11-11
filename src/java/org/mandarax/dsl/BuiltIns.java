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

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for built-in relationships.
 * @author jens dietrich
 */
public class BuiltIns {
	
	public static RelationshipDefinition[]  getBuiltInRels() {
		return new RelationshipDefinition[]{
			createInDomainRel()
		};
	}
	public static RelationshipDefinition createInDomainRel() {
		
		Context context = new Context(); 
		context.add(new ImportDeclaration(Position.NO_POSITION,context,org.mandarax.rt._InDomainRel.class.getPackage().getName(),false,false));
		
		List<VariableDeclaration> slots = new ArrayList<VariableDeclaration>(2);
		VariableDeclaration v1 = new VariableDeclaration(Position.NO_POSITION,context,Object.class.getName(),"element");
		VariableDeclaration v2 = new VariableDeclaration(Position.NO_POSITION,context,Iterable.class.getName(),"container");
		slots.add(v1);
		slots.add(v2);
		
		List<FunctionDeclaration> queries = new ArrayList<FunctionDeclaration>();
		FunctionDeclaration q1 = new FunctionDeclaration(Position.NO_POSITION,context,Visibility.PUBLIC,"getElements","container");
		FunctionDeclaration q2 = new FunctionDeclaration(Position.NO_POSITION,context,Visibility.PUBLIC,"contains","container","element");
		queries.add(q1);
		queries.add(q2);
		
		RelationshipDefinition rel = new RelationshipDefinition(Position.NO_POSITION,context,"_InDomain",slots,new ArrayList<String>(0),queries);
		rel.setTypeSafe(false);
		return rel;
		
	}
}
