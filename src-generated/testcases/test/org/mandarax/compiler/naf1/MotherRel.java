package test.org.mandarax.compiler.naf1;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Mother</strong>.
 * @version Nov 10, 2010 4:03:55 PM 
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

