package org.mandarax.dsl.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.mandarax.dsl.Expression;

/**
 * Utility to read artefacts from scripts.
 * @author jens dietrich
 */
public class ExpressionReader {
	public Expression readExpression(InputStream in) throws Exception {
		MandaraxLexer lexer = new MandaraxLexer(new ANTLRInputStream(in));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MandaraxParser parser = new MandaraxParser(tokens);
		return parser.expression().value;
	}
	
	public Expression readExpression(String expression) throws Exception {
		InputStream in = new ByteArrayInputStream(expression.getBytes("UTF-8"));
		//InputStream in = new StringBufferInputStream(expression);
		return readExpression(in);
	}
	
}
