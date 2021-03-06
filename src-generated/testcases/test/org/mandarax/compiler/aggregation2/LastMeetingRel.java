package test.org.mandarax.compiler.aggregation2;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.*;
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>LastMeeting</strong>. Generated
 * by org.mandarax.compiler.impl.DefaultCompiler.
 * 
 * @version Jan 16, 2011 9:27:45 PM
 */
public class LastMeetingRel {

	// fields created for slots
	public String venue = null;
	public Calendar time = null;

	// constructors
	public LastMeetingRel() {
		super();
	}

	public LastMeetingRel(String venue, Calendar time) {
		super();
		this.venue = venue;
		this.time = time;

	}

	// standard methods
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		LastMeetingRel _tmp = (LastMeetingRel) obj;
		return new EqualsBuilder().append(venue, _tmp.venue).append(time, _tmp.time).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(venue).append(time).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("venue", venue).append("time", time).toString();
	}

}
