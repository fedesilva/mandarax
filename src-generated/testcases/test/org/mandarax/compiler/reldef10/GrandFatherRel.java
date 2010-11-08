package test.org.mandarax.compiler.reldef10;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>GrandFather</strong>.
 * @version Nov 9, 2010 11:29:29 AM 
 */
public class GrandFatherRel {

	// constructors
	public GrandFatherRel () {
		super();
	}
	
	public GrandFatherRel ( MalePerson grandFather , MalePerson grandChild ) {
		super();
		this.grandFather=grandFather; 
		this.grandChild=grandChild; 
		
	}

	// instance variables created for slots
	 
	public MalePerson grandFather = null; 
	 
	public MalePerson grandChild = null; 
	

}

