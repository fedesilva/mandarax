package test.org.mandarax.compiler.reldef8;

/**
 * Class representing the relationship <strong>Father</strong>.
 * @version Nov 3, 2010 1:08:09 PM 
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
