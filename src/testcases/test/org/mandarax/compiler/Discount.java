

package test.org.mandarax.compiler;

/**
 * Domain class for testing.
 * @author jens dietrich
 */
public class Discount {
	private int value = 0;
	private boolean relative = false;
	
	public Discount(int value, boolean relative) {
		super();
		this.value = value;
		this.relative = relative;
	}

	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isRelative() {
		return relative;
	}
	public void setRelative(boolean relative) {
		this.relative = relative;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (relative ? 1231 : 1237);
		result = prime * result + value;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		if (relative != other.relative)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
}
