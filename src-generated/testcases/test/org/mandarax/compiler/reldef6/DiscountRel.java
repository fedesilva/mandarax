package test.org.mandarax.compiler.reldef6;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>DiscountRel</strong>.
 * @version Oct 26, 2010 9:28:37 PM 
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

