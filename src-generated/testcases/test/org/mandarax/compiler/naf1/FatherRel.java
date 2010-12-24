package test.org.mandarax.compiler.naf1;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>Father</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version 25/12/2010 9:57:10 AM 
 */
public class FatherRel {

	// fields created for slots 
	public Person father = null; 
	public Person child = null;
	
	// constructors
	public FatherRel () {
		super();
	}
	
	public FatherRel ( Person father , Person child ) {
		super();
		this.father=father; 
		this.child=child; 
		
	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
   		if (obj == null) { return false; }
   		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
   		FatherRel _tmp = (FatherRel) obj;
   		return new EqualsBuilder()
            .append(father, _tmp.father)
            .append(child, _tmp.child)
            .isEquals();
  	}
  
  	@Override
   	public int hashCode() {
    	return new HashCodeBuilder(17, 37)
        	.append(father)
            .append(child)
            .toHashCode();
   	}
   	@Override
   	public String toString() {
    	return new ToStringBuilder(this)
    		.append("father",father)
    		.append("child",child)
    		.toString();
   }
   

}

