package org.mandarax.examples.userv.rules.generated;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.mandarax.examples.userv.domain.*;

/**
 * Class representing the relationship <strong>IsSpecialLocation</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version Dec 2, 2010 1:53:24 PM 
 */
public class IsSpecialLocationRel {

	// fields created for slots 
	public Driver driver = null;
	
	// constructors
	public IsSpecialLocationRel () {
		super();
	}
	
	public IsSpecialLocationRel ( Driver driver ) {
		super();
		this.driver=driver; 
		
	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
   		if (obj == null) { return false; }
   		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
   		IsSpecialLocationRel _tmp = (IsSpecialLocationRel) obj;
   		return new EqualsBuilder()
            .append(driver, _tmp.driver)
            .isEquals();
  	}
  
  	@Override
   	public int hashCode() {
    	return new HashCodeBuilder(17, 37)
        	.append(driver)
            .toHashCode();
   	}
   	@Override
   	public String toString() {
    	return new ToStringBuilder(this)
    		.append("driver",driver)
    		.toString();
   }
   

}

