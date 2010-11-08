package test.org.mandarax.compiler.reldef14;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Mother</strong>.
 * @version Nov 8, 2010 3:21:16 PM 
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

