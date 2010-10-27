package test.org.mandarax.compiler.reldef7;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>DiscountRel</strong>.
 * @version Oct 27, 2010 2:11:54 PM 
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

