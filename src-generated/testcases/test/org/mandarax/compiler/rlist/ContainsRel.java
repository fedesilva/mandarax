package test.org.mandarax.compiler.rlist;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Contains</strong>.
 * @version Nov 15, 2010 11:28:46 AM 
 */
public class ContainsRel {

	// constructors
	public ContainsRel () {
		super();
	}
	
	public ContainsRel ( RList list , Object element ) {
		super();
		this.list=list; 
		this.element=element; 
		
	}

	// instance variables created for slots
	 
	public RList list = null; 
	 
	public Object element = null; 
	

}

