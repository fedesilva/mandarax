package test.org.mandarax.compiler.reldef6;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>FrequentCustomer</strong>.
 * @version Nov 9, 2010 11:29:29 AM 
 */
public class FrequentCustomerRel {

	// constructors
	public FrequentCustomerRel () {
		super();
	}
	
	public FrequentCustomerRel ( Customer customer ) {
		super();
		this.customer=customer; 
		
	}

	// instance variables created for slots
	 
	public Customer customer = null; 
	

}

