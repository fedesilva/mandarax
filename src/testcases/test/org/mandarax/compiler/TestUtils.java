package test.org.mandarax.compiler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;

import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.Rule;
import org.mandarax.dsl.parser.ScriptReader;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;


/**
 * Test utilities.
 * @author jens dietrich
 */

public class TestUtils {

	public static CompilationUnit readCUFromCP(String name) throws Exception {
		InputStream in = TestUtils.class.getResourceAsStream("/test/org/mandarax/compiler/"+name);
		CompilationUnit cu = new ScriptReader().readCompilationUnit(in);
		in.close();
		return cu;
	}
	
	public static Rule readRule(String s) throws Exception {
		InputStream in = getStream(s);
		return new ScriptReader().readRule(in);
	}
	
	public static InputStream getStream(String s) throws Exception {
		return new ByteArrayInputStream(s.getBytes("UTF-8"));
	}
	
	public static Collection<String> toStrings(Collection<Expression> expressions) {
		return Collections2.transform(expressions, new Function<Expression,String>() {
			@Override
			public String apply(Expression x) {return x.toString();}});
	}
	
}
