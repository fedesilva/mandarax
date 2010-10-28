package test.org.mandarax.compiler.reldef7;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>FrequentCustomer</strong>.
 * @version Oct 28, 2010 1:42:51 PM 
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

