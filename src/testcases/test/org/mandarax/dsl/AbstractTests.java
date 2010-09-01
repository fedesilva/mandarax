package test.org.mandarax.dsl;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.util.ExpressionPrinter;
import org.mandarax.dsl.util.ExpressionStructurePrinter;



abstract class AbstractTests {
	
	private boolean DEBUG_MODE = true;

	protected void print(Expression expression) {
		if (DEBUG_MODE) {
			System.out.println("-- starts --");
			ExpressionPrinter printer1 = new ExpressionPrinter();
			printer1.print(expression); 
			//System.out.println();
			ExpressionStructurePrinter printer = new ExpressionStructurePrinter();
			printer.println();
			expression.accept(printer);
			System.out.println("-- ends --");
			System.out.println();
		}
	}

}
