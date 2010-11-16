package test.org.mandarax.compiler.indomain1;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Discount</strong>.
 * @version Nov 16, 2010 4:04:09 PM 
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

