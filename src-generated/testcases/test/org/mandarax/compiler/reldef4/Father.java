package test.org.mandarax.compiler.reldef4;

/**
 * Class representing the relationship <strong>Father</strong>.
 * @version Oct 26, 2010 10:03:31 PM 
 */
public class Father {

	// constructors
	public Father () {
		super();
	}
	
	public Father ( String father , String child ) {
		super();
		this.father=father; 
		this.child=child; 
		
	}

	// instance variables created for slots
	 
	public String father = null; 
	 
	public String child = null; 
	

}

