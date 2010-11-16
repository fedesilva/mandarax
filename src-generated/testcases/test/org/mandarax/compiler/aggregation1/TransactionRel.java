package test.org.mandarax.compiler.aggregation1;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Transaction</strong>.
 * @version Nov 16, 2010 4:04:09 PM 
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

