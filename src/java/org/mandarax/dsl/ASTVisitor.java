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

/**
 * Visitor used to traverse the AST.
 * @author jens dietrich
 */
public interface ASTVisitor {
	public boolean visit(CompilationUnit x);
	public boolean visit(RelationshipDefinition x);
	public boolean visit(Rule x);
	public boolean visit(Annotation x);
	public boolean visit(FunctionDeclaration x);
	public boolean visit(ImportDeclaration x);
	public boolean visit(ObjectDeclaration x);
	public boolean visit(PackageDeclaration x);
	public boolean visit(VariableDeclaration x);
	public boolean visit(BinaryExpression x);
	public boolean visit(BooleanLiteral x);
	public boolean visit(CastExpression x);
	public boolean visit(ConditionalExpression x);
	public boolean visit(InstanceOfExpression x);
	public boolean visit(IntLiteral x);
	public boolean visit(DoubleLiteral x);
	public boolean visit(MemberAccess x);
	public boolean visit(StringLiteral x);
	public boolean visit(UnaryExpression x);
	public boolean visit(Variable x);	
	public boolean visit(FunctionInvocation x);
	public boolean visit(ConstructorInvocation x);
	public boolean visit(NullValue x);
	public boolean visit(Aggregation x);
	
	public void endVisit(CompilationUnit x);
	public void endVisit(RelationshipDefinition x);
	public void endVisit(Rule x);
	public void endVisit(Annotation x);
	public void endVisit(FunctionDeclaration x);
	public void endVisit(ImportDeclaration x);
	public void endVisit(ObjectDeclaration x);
	public void endVisit(PackageDeclaration x);
	public void endVisit(VariableDeclaration x);
	public void endVisit(BinaryExpression x);
	public void endVisit(BooleanLiteral x);
	public void endVisit(CastExpression x);
	public void endVisit(ConditionalExpression x);
	public void endVisit(InstanceOfExpression x);
	public void endVisit(IntLiteral x);
	public void endVisit(DoubleLiteral x);
	public void endVisit(MemberAccess x);
	public void endVisit(StringLiteral x);
	public void endVisit(UnaryExpression x);
	public void endVisit(Variable x);
	public void endVisit(FunctionInvocation x);
	public void endVisit(ConstructorInvocation x);
	public void endVisit(NullValue x);
	public void endVisit(Aggregation x);
	
}
