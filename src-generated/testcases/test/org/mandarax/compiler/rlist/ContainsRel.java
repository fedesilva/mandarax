package test.org.mandarax.compiler.rlist;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Contains</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version 25/12/2010 7:02:49 AM 
 */
public class ContainsRel {

	// fields created for slots 
	public RList list = null; 
	public Object element = null;
	
	// constructors
	public ContainsRel () {
		super();
	}
	
	public ContainsRel ( RList list , Object element ) {
		super();
		this.list=list; 
		this.element=element; 
		
	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
   		if (obj == null) { return false; }
   		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
   		ContainsRel _tmp = (ContainsRel) obj;
   		return new EqualsBuilder()
            .append(list, _tmp.list)
            .append(element, _tmp.element)
            .isEquals();
  	}
  
  	@Override
   	public int hashCode() {
    	return new HashCodeBuilder(17, 37)
        	.append(list)
            .append(element)
            .toHashCode();
   	}
   	@Override
   	public String toString() {
    	return new ToStringBuilder(this)
    		.append("list",list)
    		.append("element",element)
    		.toString();
   }
   

}

