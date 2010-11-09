package test.org.mandarax.compiler.reldef10;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>GrandFather</strong>.
 * @version Nov 9, 2010 3:23:58 PM 
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

