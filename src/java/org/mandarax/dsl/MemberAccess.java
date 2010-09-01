package org.mandarax.dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * Member (method or field) access expression.
 * @author jens dietrich
 */

public class MemberAccess extends Expression {
	private String member = null;
	private Expression objectReference = null;
	private List<Expression> parameters = new ArrayList<Expression>();
	private boolean isMethod = false;
	
	public MemberAccess(Position position,Expression objectReference,String member,List<Expression> parameters) {
		super(position);
		this.member = member;
		this.objectReference = objectReference;
		this.parameters = parameters;
		this.isMethod = true;
	}
	public MemberAccess(Position position,Expression objectReference,String member) {
		super(position);
		this.member = member;
		this.objectReference = objectReference;
		this.isMethod = false;
	}
	
	public String getMember() {
		return member;
	}
	public Expression getObjectReference() {
		return objectReference;
	}
	public List<Expression> getParameters() {
		return parameters;
	}
	public boolean isMethod() {
		return isMethod;
	}
	public void accept(Visitor visitor) {
		if (visitor.visit(this)) {
			objectReference.accept(visitor);
			for (Expression param:parameters) {
				param.accept(visitor);
			}
		}
		visitor.endVisit(this);
	}


}
