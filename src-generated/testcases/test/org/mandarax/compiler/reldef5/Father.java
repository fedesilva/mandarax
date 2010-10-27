package test.org.mandarax.compiler.reldef5;
 
import test.org.mandarax.compiler.Person;

/**
 * Class representing the relationship <strong>Father</strong>.
 * @version Oct 28, 2010 11:58:22 AM 
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

