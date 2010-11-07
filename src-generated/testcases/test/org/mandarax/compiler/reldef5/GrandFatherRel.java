package test.org.mandarax.compiler.reldef5;
 
import test.org.mandarax.compiler.Person;

/**
 * Class representing the relationship <strong>GrandFather</strong>.
 * @version Nov 8, 2010 11:45:59 AM 
 */
public class GrandFatherRel {

	// constructors
	public GrandFatherRel () {
		super();
	}
	
	public GrandFatherRel ( Person grandFather , Person grandChild ) {
		super();
		this.grandFather=grandFather; 
		this.grandChild=grandChild; 
		
	}

	// instance variables created for slots
	 
	public Person grandFather = null; 
	 
	public Person grandChild = null; 
	

}

