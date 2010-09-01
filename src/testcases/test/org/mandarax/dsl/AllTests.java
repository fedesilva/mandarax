package test.org.mandarax.dsl;
import org.junit.runner.RunWith;

import org.junit.runners.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ParserTestsArithmetic.class,
	ParserTestsRelationalExpressions.class,
	ParserTestsFlatExpressions.class,
	ParserTestsMemberAccess.class,
	ParserTestsMiscExpressions.class,
	ParserTestsComplexExpressions.class
})
public class AllTests {

}

