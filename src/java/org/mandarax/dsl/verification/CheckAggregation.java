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

package org.mandarax.dsl.verification;

import java.util.ArrayList;
import java.util.Collection;
import org.mandarax.dsl.ASTVisitor;
import org.mandarax.dsl.AbstractASTVisitor;
import org.mandarax.dsl.Aggregation;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.FunctionInvocation;
import org.mandarax.dsl.Variable;
import org.mandarax.dsl.VerificationErrorReporter;
import org.mandarax.dsl.VerificationException;
import org.mandarax.dsl.Verifier;

/**
 * Ensure that the aggregation used in aggregations occur in the relationship.
 * E.g. max v in MyRel(x,y) violates this - v is not a variable in the term MyRel(x,y). 
 * @author jens dietrich
 */
public class CheckAggregation implements Verifier {

	@Override
	public void verify(Collection<CompilationUnit> cus,VerificationErrorReporter errorHandler) throws VerificationException {
		for (CompilationUnit cu:cus) {
			final Collection<Aggregation> aggs = new ArrayList<Aggregation>();
			ASTVisitor visitor = new AbstractASTVisitor(){
				@Override
				public boolean visit(Aggregation agg) {
					aggs.add(agg);
					return super.visit(agg);
				}
				
			};
			cu.accept(visitor);
			for (Aggregation agg:aggs) {
				verify(cu,agg,errorHandler);
			}
		}
		
		
	}

	protected void verify(CompilationUnit cu,Aggregation agg,VerificationErrorReporter errorHandler) throws VerificationException {
		Variable v = agg.getVariable();
		FunctionInvocation fi = agg.getExpression();
		for (Expression param:fi.getParameters()) {
			if (param.equals(v)) return;
		}
		
		errorHandler.reportError(cu,"The variable ",v," in aggregation ",agg," at ",agg.getPosition()," does not occur as parameter in the function invocation");
	}

}
