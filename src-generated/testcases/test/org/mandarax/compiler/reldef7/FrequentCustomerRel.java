package test.org.mandarax.compiler.reldef7;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>FrequentCustomer</strong>.
 * @version Oct 29, 2010 2:37:45 PM 
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

