package test.org.mandarax.compiler.reldef11;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Father</strong>.
 * @version Nov 9, 2010 3:23:58 PM 
 */
public class FatherRel {

	// constructors
	public FatherRel () {
		super();
	}
	
	public FatherRel ( Person father , Person child ) {
		super();
		this.father=father; 
		this.child=child; 
		
	}

	// instance variables created for slots
	 
	public Person father = null; 
	 
	public Person child = null; 
	

}

