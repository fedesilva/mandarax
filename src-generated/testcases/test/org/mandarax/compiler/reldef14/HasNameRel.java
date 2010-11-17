package test.org.mandarax.compiler.reldef14;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>HasName</strong>.
 * @version Nov 18, 2010 9:53:04 AM 
 */
public class HasNameRel {

	// constructors
	public HasNameRel () {
		super();
	}
	
	public HasNameRel ( Person person , String name ) {
		super();
		this.person=person; 
		this.name=name; 
		
	}

	// instance variables created for slots
	 
	public Person person = null; 
	 
	public String name = null; 
	

}

