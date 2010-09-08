package org.mandarax.dsl;

import java.util.ArrayList;
import java.util.List;

public class CompilationUnit extends ASTNode {

	private List<RelationshipDefinition> relationshipDefinitions = new ArrayList<RelationshipDefinition>() ;
	
	public CompilationUnit(Position position, Context context) {
		super(position, context);
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void appendTo(StringBuffer b) {
		// TODO Auto-generated method stub
	}

	public List<RelationshipDefinition> getRelationshipDefinitions() {
		return relationshipDefinitions;
	}
	
	public void add(RelationshipDefinition relDef) {
		this.relationshipDefinitions.add(relDef);
	}

}
