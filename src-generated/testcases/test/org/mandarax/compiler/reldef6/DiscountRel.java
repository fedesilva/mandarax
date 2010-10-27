package test.org.mandarax.compiler.reldef6;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>DiscountRel</strong>.
 * @version Oct 28, 2010 11:58:22 AM 
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

