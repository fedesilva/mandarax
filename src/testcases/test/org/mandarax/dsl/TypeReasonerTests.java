package test.org.mandarax.dsl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.Variable;
import org.mandarax.dsl.parser.ExpressionReader;
import org.mandarax.dsl.util.AbstractTypeReasoner;
import org.mandarax.dsl.util.DefaultResolver;
import org.mandarax.dsl.util.Resolver;
import org.mandarax.dsl.util.TypeReasoner;
import org.mandarax.dsl.util.TypeReasoningException;

public class TypeReasonerTests {
	
	class TestTypeReasoner extends AbstractTypeReasoner {
		Map<String,Class> varTypes = null;
		public TestTypeReasoner(Map<String, Class> varTypes) {
			super();
			this.varTypes = varTypes;
		}
		@Override
		public Class getType(Variable expression, Resolver resolver) throws TypeReasoningException {
			Class clazz = varTypes.get(expression.getName());
			
			return clazz;
		}
		
	}
	private void testType(String expressionDef,Class expectedType,Map<String,Class> varTypes) throws Exception {
		Expression expression = new ExpressionReader().readExpression(expressionDef);
		TypeReasoner typeReasoner = new TestTypeReasoner(varTypes);
		Resolver resolver = new DefaultResolver();
		Class computedType = typeReasoner.getType(expression, resolver);
		assertEquals(expectedType,computedType);
	}
	@Test
	public void testBinaryExpressions1() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		varTypes.put("y",Byte.class);
		testType(expression,Integer.class,varTypes);
	}
	
	@Test(expected=TypeReasoningException.class) // wont work, y not bound
	public void testBinaryExpressions2() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		//varTypes.put("y",Byte.class);
		testType(expression,Integer.class,varTypes);
	}
	
	@Test(expected=TypeReasoningException.class) // wont work, x not bound
	public void testBinaryExpressions3() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		//varTypes.put("x",Integer.class);
		varTypes.put("y",Byte.class);
		testType(expression,Integer.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions4() throws Exception {
		String expression = "x*y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Character.class);
		varTypes.put("y",Byte.class);
		testType(expression,Integer.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions5() throws Exception {
		String expression = "x%y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		varTypes.put("y",Long.class);
		testType(expression,Long.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions6() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Long.class);
		varTypes.put("y",Long.class);
		testType(expression,Long.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions7() throws Exception {
		String expression = "x+y+z";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		varTypes.put("y",Long.class);
		varTypes.put("z",Integer.class);
		testType(expression,Long.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions8() throws Exception {
		String expression = "(x+y)*(z+4)";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Integer.class);
		varTypes.put("y",Integer.class);
		varTypes.put("z",Long.class);
		testType(expression,Long.class,varTypes);
	}
	
	
	@Test
	public void testBinaryExpressions9() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Double.class);
		varTypes.put("y",Integer.class);
		testType(expression,Double.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions10() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Double.class);
		varTypes.put("y",Float.class);
		testType(expression,Double.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions11() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Float.class);
		varTypes.put("y",Float.class);
		testType(expression,Float.class,varTypes);
	}
	
	@Test
	public void testBinaryExpressions12() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",Object.class);
		testType(expression,String.class,varTypes);
	}
	// only works if first argument is string
	@Test(expected=TypeReasoningException.class) 
	public void testBinaryExpressions13() throws Exception {
		String expression = "x+y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",Object.class);
		varTypes.put("y",String.class);
		testType(expression,String.class,varTypes);
	}
	
	// * not supported for strings
	@Test(expected=TypeReasoningException.class) 
	public void testBinaryExpressions14() throws Exception {
		String expression = "x*y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",String.class);
		testType(expression,String.class,varTypes);
	}
	@Test
	public void testConditions1() throws Exception {
		String expression = "x==y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",Exception.class);
		testType(expression,Boolean.class,varTypes);
	}
	@Test
	public void testConditions2() throws Exception {
		String expression = "x<y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",Exception.class);
		testType(expression,Boolean.class,varTypes);
	}
	@Test
	public void testConditions3() throws Exception {
		String expression = "x>y";
		Map<String,Class> varTypes = new HashMap<String,Class>();
		varTypes.put("x",String.class);
		varTypes.put("y",Exception.class);
		testType(expression,Boolean.class,varTypes);
	}
}
