package test.org.mandarax.compiler.reldef4;

/**
 * Class representing the relationship <strong>GrandFather</strong>.
 * @version Nov 9, 2010 11:29:29 AM 
 */
public class GrandFatherRel {

	// constructors
	public GrandFatherRel () {
		super();
	}
	
	public GrandFatherRel ( String grandFather , String grandChild ) {
		super();
		this.grandFather=grandFather; 
		this.grandChild=grandChild; 
		
	}

	// instance variables created for slots
	 
	public String grandFather = null; 
	 
	public String grandChild = null; 
	

}

