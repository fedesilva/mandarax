package test.org.mandarax.compiler.reldef5;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import test.org.mandarax.compiler.Person;

/**
 * Class representing the relationship <strong>GrandFather</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version 25/12/2010 7:02:45 AM 
 */
public class GrandFatherRel {

	// fields created for slots 
	public Person grandFather = null; 
	public Person grandChild = null;
	
	// constructors
	public GrandFatherRel () {
		super();
	}
	
	public GrandFatherRel ( Person grandFather , Person grandChild ) {
		super();
		this.grandFather=grandFather; 
		this.grandChild=grandChild; 
		
	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
   		if (obj == null) { return false; }
   		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
   		GrandFatherRel _tmp = (GrandFatherRel) obj;
   		return new EqualsBuilder()
            .append(grandFather, _tmp.grandFather)
            .append(grandChild, _tmp.grandChild)
            .isEquals();
  	}
  
  	@Override
   	public int hashCode() {
    	return new HashCodeBuilder(17, 37)
        	.append(grandFather)
            .append(grandChild)
            .toHashCode();
   	}
   	@Override
   	public String toString() {
    	return new ToStringBuilder(this)
    		.append("grandFather",grandFather)
    		.append("grandChild",grandChild)
    		.toString();
   }
   

}

