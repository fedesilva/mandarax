package org.mandarax.dsl;

/**
 * Visitor interface.
 * @author jens dietrich
 */

public interface Visitor {
	public boolean visit(BinaryExpression x);
	public boolean visit(BooleanLiteral x);
	public boolean visit(CastExpression x);
	public boolean visit(ConditionalExpression x);
	public boolean visit(InstanceOfExpression x);
	public boolean visit(IntLiteral x);
	public boolean visit(MemberAccess x);
	public boolean visit(StringLiteral x);
	public boolean visit(UnaryExpression x);
	public boolean visit(Variable x);	
	public void endVisit(BinaryExpression x);
	public void endVisit(BooleanLiteral x);
	public void endVisit(CastExpression x);
	public void endVisit(ConditionalExpression x);
	public void endVisit(InstanceOfExpression x);
	public void endVisit(IntLiteral x);
	public void endVisit(MemberAccess x);
	public void endVisit(StringLiteral x);
	public void endVisit(UnaryExpression x);
	public void endVisit(Variable x);
}
