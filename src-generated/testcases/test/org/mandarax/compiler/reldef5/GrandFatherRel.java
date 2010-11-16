package test.org.mandarax.compiler.reldef5;
 
import test.org.mandarax.compiler.Person;

/**
 * Class representing the relationship <strong>GrandFather</strong>.
 * @version Nov 17, 2010 10:34:19 AM 
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

