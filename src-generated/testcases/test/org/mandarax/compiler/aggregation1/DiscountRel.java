package test.org.mandarax.compiler.aggregation1;
 
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
	
	public DiscountRel ( String customer , int discount ) {
		super();
		this.customer=customer; 
		this.discount=discount; 
		
	}

	// instance variables created for slots
	 
	public String customer = null; 
	 
	public int discount = 0; 
	

}
