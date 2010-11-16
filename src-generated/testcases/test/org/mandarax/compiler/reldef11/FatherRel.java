package test.org.mandarax.compiler.reldef11;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Father</strong>.
 * @version Nov 16, 2010 10:05:51 PM 
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

