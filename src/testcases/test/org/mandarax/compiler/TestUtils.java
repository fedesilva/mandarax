package test.org.mandarax.compiler;

import java.io.InputStream;
import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.parser.ScriptReader;


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
	
}
