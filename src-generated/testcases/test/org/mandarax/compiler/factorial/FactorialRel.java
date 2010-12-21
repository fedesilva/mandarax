package test.org.mandarax.compiler.factorial;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Class representing the relationship <strong>Factorial</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version 22/12/2010 4:19:10 AM 
 */
public class FactorialRel {

	// fields created for slots 
	public int i = 0; 
	public int f = 0;
	
	// constructors
	public FactorialRel () {
		super();
	}
	
	public FactorialRel ( int i , int f ) {
		super();
		this.i=i; 
		this.f=f; 
		
	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
   		if (obj == null) { return false; }
   		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
   		FactorialRel _tmp = (FactorialRel) obj;
   		return new EqualsBuilder()
            .append(i, _tmp.i)
            .append(f, _tmp.f)
            .isEquals();
  	}
  
  	@Override
   	public int hashCode() {
    	return new HashCodeBuilder(17, 37)
        	.append(i)
            .append(f)
            .toHashCode();
   	}
   	@Override
   	public String toString() {
    	return new ToStringBuilder(this)
    		.append("i",i)
    		.append("f",f)
    		.toString();
   }
   

}

