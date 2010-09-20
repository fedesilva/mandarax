package org.mandarax.dsl;

import java.util.List;
/**
 * Function declarations: [public|private] name(list of parameter names)
 * @author jens dietrich
 *
 */
public class FunctionDeclaration extends ASTNode {
	
	private Visibility visibility;
	private List<String> parameterNames = null;
	private String name = null;
	
	public FunctionDeclaration(Position position, Context context,Visibility visibility, String name, List<String> parameterNames) {
		super(position, context);
		this.visibility = visibility;
		this.parameterNames = parameterNames;
		this.name = name;
	}

	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public List<String> getParameterNames() {
		return parameterNames;
	}

	public String getName() {
		return name;
	}
	
	protected void appendTo(StringBuffer b) {
		b.append(visibility.name());
		b.append(' ');
		b.append(name);
		appendListOfStrings(parameterNames,b,true);
	}

	public boolean hasParameters() {
		return this.parameterNames!=null && !this.parameterNames.isEmpty();
	}
}
