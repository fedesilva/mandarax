package test.org.mandarax.compiler.reldef5;
 
import test.org.mandarax.compiler.Person;

/**
 * Class representing the relationship <strong>Father</strong>.
 * @version Nov 16, 2010 4:04:07 PM 
 */
public class FatherRel {

	// constructors
	public FatherRel () {
		super();
	}
	
	public FatherRel ( String father , String child ) {
		super();
		this.father=father; 
		this.child=child; 
		
	}

	// instance variables created for slots
	 
	public String father = null; 
	 
	public String child = null; 
	

}

