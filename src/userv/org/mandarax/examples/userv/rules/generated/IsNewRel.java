package org.mandarax.examples.userv.rules.generated;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.mandarax.examples.userv.domain.*;

/**
 * Class representing the relationship <strong>IsNew</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version Nov 22, 2010 11:30:40 AM 
 */
public class IsNewRel {

	// fields created for slots 
	public Car car = null;
	
	// constructors
	public IsNewRel () {
		super();
	}
	
	public IsNewRel ( Car car ) {
		super();
		this.car=car; 
		
	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
   		if (obj == null) { return false; }
   		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
   		IsNewRel _tmp = (IsNewRel) obj;
   		return new EqualsBuilder()
            .append(car, _tmp.car)
            .isEquals();
  	}
  
  	@Override
   	public int hashCode() {
    	return new HashCodeBuilder(17, 37)
        	.append(car)
            .toHashCode();
   	}
   	@Override
   	public String toString() {
    	return new ToStringBuilder(this)
    		.append("car",car)
    		.toString();
   }
   

}

