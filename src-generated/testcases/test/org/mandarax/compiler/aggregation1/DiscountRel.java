package test.org.mandarax.compiler.aggregation1;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Discount</strong>.
 * @version Nov 17, 2010 10:34:20 AM 
 */
public class DiscountRel {

	// constructors
	public DiscountRel () {
		super();
	}
	
	public DiscountRel ( String customer , int discount ) {
		super();
		this.customer=customer; 
		this.discount=discount; 
		
	}

	// instance variables created for slots
	 
	public String customer = null; 
	 
	public int discount = 0; 
	

}

