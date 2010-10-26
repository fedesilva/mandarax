package test.org.mandarax.compiler.reldef4;

/**
 * Class representing the relationship <strong>GrandFather</strong>.
 * @version Oct 27, 2010 12:47:25 PM 
 */
public class GrandFather {

	// constructors
	public GrandFather () {
		super();
	}
	
	public GrandFather ( String grandFather , String grandChild ) {
		super();
		this.grandFather=grandFather; 
		this.grandChild=grandChild; 
		
	}

	// instance variables created for slots
	 
	public String grandFather = null; 
	 
	public String grandChild = null; 
	

}

