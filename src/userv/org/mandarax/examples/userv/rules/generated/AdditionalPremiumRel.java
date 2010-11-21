package org.mandarax.examples.userv.rules.generated;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.mandarax.examples.userv.domain.*;

/**
 * Class representing the relationship <strong>AdditionalPremium</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version Nov 22, 2010 11:30:40 AM 
 */
public class AdditionalPremiumRel {

	// fields created for slots 
	public Policy policy = null; 
	public Car car = null; 
	public int premium = 0;
	
	// constructors
	public AdditionalPremiumRel () {
		super();
	}
	
	public AdditionalPremiumRel ( Policy policy , Car car , int premium ) {
		super();
		this.policy=policy; 
		this.car=car; 
		this.premium=premium; 
		
	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
   		if (obj == null) { return false; }
   		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
   		AdditionalPremiumRel _tmp = (AdditionalPremiumRel) obj;
   		return new EqualsBuilder()
            .append(policy, _tmp.policy)
            .append(car, _tmp.car)
            .append(premium, _tmp.premium)
            .isEquals();
  	}
  
  	@Override
   	public int hashCode() {
    	return new HashCodeBuilder(17, 37)
        	.append(policy)
            .append(car)
            .append(premium)
            .toHashCode();
   	}
   	@Override
   	public String toString() {
    	return new ToStringBuilder(this)
    		.append("policy",policy)
    		.append("car",car)
    		.append("premium",premium)
    		.toString();
   }
   

}
