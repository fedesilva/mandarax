package org.mandarax.dsl;

import java.util.ArrayList;
import java.util.List;

public class CompilationUnit extends ASTNode {

	private List<RelationshipDefinition> relationshipDefinitions = new ArrayList<RelationshipDefinition>() ;
	private List<ObjectDeclaration> objectDeclarations = new ArrayList<ObjectDeclaration>() ;
	
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
	
	public List<ObjectDeclaration> getObjectDeclarations() {
		return objectDeclarations;
	}
	
	public void add(ObjectDeclaration objDecl) {
		this.objectDeclarations.add(objDecl);
	}
	
	// deep access methods
	public PackageDeclaration getPackageDeclaration() {
		return this.getContext().getPackageDeclaration(); 
	}
	
	public List<ImportDeclaration> getImportDeclarations() {
		return this.getContext().getImportDeclarations(); 
	}
	
	public List<ImportDeclaration> getStaticImportDeclarations() {
		return this.getContext().getStaticImportDeclarations(); 
	}
	
	

}
