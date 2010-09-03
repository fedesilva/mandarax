package org.mandarax.dsl;

import java.util.List;
/**
 * Function declarations: [public|private] name(list of parameter names)
 * @author jens dietrich
 *
 */
public class FunctionDeclaration extends ASTNode {

	public FunctionDeclaration(Position position, Context context,Visibility visibility, String name, List<String> parameterNames) {
		super(position, context);
		this.visibility = visibility;
		this.parameterNames = parameterNames;
		this.name = name;
	}

	private Visibility visibility;
	private List<String> parameterNames = null;
	private String name = null;

	@Override
	public void accept(ExpressionVisitor visitor) {}

	public Visibility getVisibility() {
		return visibility;
	}

	public List<String> getParameterNames() {
		return parameterNames;
	}

	public String getName() {
		return name;
	}
}
