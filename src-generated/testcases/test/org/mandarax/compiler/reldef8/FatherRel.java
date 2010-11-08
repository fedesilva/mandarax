package test.org.mandarax.compiler.reldef8;

/**
 * Class representing the relationship <strong>Father</strong>.
 * @version Nov 8, 2010 3:21:16 PM 
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

