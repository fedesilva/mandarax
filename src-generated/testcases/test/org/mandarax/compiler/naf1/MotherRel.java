package test.org.mandarax.compiler.naf1;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Mother</strong>.
 * @version Nov 16, 2010 10:05:51 PM 
 */
public class MotherRel {

	// constructors
	public MotherRel () {
		super();
	}
	
	public MotherRel ( Person mother , Person child ) {
		super();
		this.mother=mother; 
		this.child=child; 
		
	}

	// instance variables created for slots
	 
	public Person mother = null; 
	 
	public Person child = null; 
	

}

