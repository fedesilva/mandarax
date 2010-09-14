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

/**
 * Abstract visitor. The entire tree is visited.
 * @author jens dietrich
 */
public class AbstractExpressionVisitor implements ASTVisitor {

	@Override
	public boolean visit(BinaryExpression x) {
		return true;
	}

	@Override
	public boolean visit(BooleanLiteral x) {
		return true;
	}

	@Override
	public boolean visit(CastExpression x) {
		return true;
	}

	@Override
	public boolean visit(ConditionalExpression x) {
		return true;
	}

	@Override
	public boolean visit(InstanceOfExpression x) {
		return true;
	}

	@Override
	public boolean visit(IntLiteral x) {
		return true;
	}

	@Override
	public boolean visit(MemberAccess x) {
		return true;
	}

	@Override
	public boolean visit(StringLiteral x) {
		return true;
	}

	@Override
	public boolean visit(UnaryExpression x) {
		return true;
	}

	@Override
	public boolean visit(Variable x) {
		return true;
	}

	@Override
	public boolean visit(FunctionInvocation x) {
		return true;
	}
	
	@Override
	public boolean visit(ConstructorInvocation x) {
		return true;
	}

	@Override
	public boolean visit(NullValue x) {
		return true;
	}

	@Override
	public boolean visit(RelationshipDefinition x) {
		return true;
	}
	
	@Override
	public boolean visit(Rule x) {
		return true;
	}

	@Override
	public boolean visit(Annotation x) {
		return true;
	}

	@Override
	public boolean visit(FunctionDeclaration x) {
		return true;
	}

	@Override
	public boolean visit(ImportDeclaration x) {
		return true;
	}

	@Override
	public boolean visit(ObjectDeclaration x) {
		return true;
	}

	@Override
	public boolean visit(PackageDeclaration x) {
		return true;
	}

	@Override
	public boolean visit(VariableDeclaration x) {
		return true;
	}

	@Override
	public boolean visit(CompilationUnit x) {
		return true;
	}
	
	@Override
	public void endVisit(BinaryExpression x) {}

	@Override
	public void endVisit(BooleanLiteral x) {}

	@Override
	public void endVisit(CastExpression x) {}

	@Override
	public void endVisit(ConditionalExpression x) {}

	@Override
	public void endVisit(InstanceOfExpression x) {}

	@Override
	public void endVisit(IntLiteral x) {}

	@Override
	public void endVisit(MemberAccess x) {}

	@Override
	public void endVisit(StringLiteral x) {}

	@Override
	public void endVisit(UnaryExpression x) {}

	@Override
	public void endVisit(Variable x) {}

	@Override
	public void endVisit(FunctionInvocation x) {}

	@Override
	public void endVisit(ConstructorInvocation x) {}

	@Override
	public void endVisit(NullValue x) {}

	@Override
	public void endVisit(CompilationUnit x) {}

	@Override
	public void endVisit(RelationshipDefinition x) {}
	
	@Override
	public void endVisit(Rule x) {}

	@Override
	public void endVisit(Annotation x) {}

	@Override
	public void endVisit(FunctionDeclaration x) {}

	@Override
	public void endVisit(ImportDeclaration x) {}

	@Override
	public void endVisit(ObjectDeclaration x) {}

	@Override
	public void endVisit(PackageDeclaration x) {}

	@Override
	public void endVisit(VariableDeclaration x) {}
	
}
