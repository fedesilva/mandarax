package test.org.mandarax.compiler.reldef7;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>FrequentCustomer</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version 25/12/2010 9:57:06 AM 
 */
public class FrequentCustomerRel {

	// fields created for slots 
	public Customer customer = null;
	
	// constructors
	public FrequentCustomerRel () {
		super();
	}
	
	public FrequentCustomerRel ( Customer customer ) {
		super();
		this.customer=customer; 
		
	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
   		if (obj == null) { return false; }
   		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
   		FrequentCustomerRel _tmp = (FrequentCustomerRel) obj;
   		return new EqualsBuilder()
            .append(customer, _tmp.customer)
            .isEquals();
  	}
  
  	@Override
   	public int hashCode() {
    	return new HashCodeBuilder(17, 37)
        	.append(customer)
            .toHashCode();
   	}
   	@Override
   	public String toString() {
    	return new ToStringBuilder(this)
    		.append("customer",customer)
    		.toString();
   }
   

}

