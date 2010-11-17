package test.org.mandarax.compiler.aggregation2;
 
import java.util.*;
 
import test.org.mandarax.compiler.*;

/**
 * Class representing the relationship <strong>LastMeeting</strong>.
 * @version Nov 17, 2010 9:21:54 PM 
 */
public class LastMeetingRel {

	// constructors
	public LastMeetingRel () {
		super();
	}
	
	public LastMeetingRel ( String venue , Calendar time ) {
		super();
		this.venue=venue; 
		this.time=time; 
		
	}

	// instance variables created for slots
	 
	public String venue = null; 
	 
	public Calendar time = null; 
	

}

