package test.org.mandarax.compiler.reldef7;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Discount</strong>.
 * @version Nov 17, 2010 10:34:19 AM 
 */
public class DiscountRel {

	// constructors
	public DiscountRel () {
		super();
	}
	
	public DiscountRel ( Customer customer , Discount discount ) {
		super();
		this.customer=customer; 
		this.discount=discount; 
		
	}

	// instance variables created for slots
	 
	public Customer customer = null; 
	 
	public Discount discount = null; 
	

}

