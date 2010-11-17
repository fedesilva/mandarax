package test.org.mandarax.compiler.aggregation1;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Transaction</strong>.
 * @version Nov 17, 2010 9:21:54 PM 
 */
public class TransactionRel {

	// constructors
	public TransactionRel () {
		super();
	}
	
	public TransactionRel ( String customer , int value ) {
		super();
		this.customer=customer; 
		this.value=value; 
		
	}

	// instance variables created for slots
	 
	public String customer = null; 
	 
	public int value = 0; 
	

}

