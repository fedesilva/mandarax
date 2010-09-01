package org.mandarax.dsl;


/**
 * Conditional expression (cond?ifTrue:ifFalse).
 * @author jens dietrich
 */

public class ConditionalExpression extends Expression {
	
	private Expression condition = null;
	private Expression ifTrue = null;
	private Expression ifFalse = null;
	
	public ConditionalExpression(Position position,Expression condition, Expression ifTrue,Expression ifFalse) {
		super(position);
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}
	public Expression getCondition() {
		return condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	public Expression getIfTrue() {
		return ifTrue;
	}
	public void setIfTrue(Expression ifTrue) {
		this.ifTrue = ifTrue;
	}
	public Expression getIfFalse() {
		return ifFalse;
	}
	public void setIfFalse(Expression ifFalse) {
		this.ifFalse = ifFalse;
	}
	
	public void accept(Visitor visitor) {
		if (visitor.visit(this)) {
			condition.accept(visitor);
			ifTrue.accept(visitor);
			ifFalse.accept(visitor);
		}
		visitor.endVisit(this);
	}
}
