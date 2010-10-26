package test.org.mandarax.compiler.reldef6;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>FrequentCustomer</strong>.
 * @version Oct 27, 2010 12:47:25 PM 
 */
public class FrequentCustomer {

	// constructors
	public FrequentCustomer () {
		super();
	}
	
	public FrequentCustomer ( Customer customer ) {
		super();
		this.customer=customer; 
		
	}

	// instance variables created for slots
	 
	public Customer customer = null; 
	

}

