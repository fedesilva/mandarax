package org.mandarax.dsl.util;

import org.mandarax.dsl.Expression;

/**
 * Utility to associate type information with expressions.
 * @author jens dietrich
 */

public interface TypeReasoner {
	Class getType(Expression expression,Resolver resolver);
}
