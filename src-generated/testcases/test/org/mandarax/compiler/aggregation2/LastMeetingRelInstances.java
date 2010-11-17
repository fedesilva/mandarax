package test.org.mandarax.compiler.aggregation2;
 
import java.util.*;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>LastMeeting</strong>.
 * @version Nov 17, 2010 9:21:54 PM 
 */
public class LastMeetingRelInstances {
	// object references
	
	
	// fields representing annotations
	
	// rule: rule1:  -> LastMeeting(v,max t in Meeting(v,t));
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1:  -> LastMeeting(v,max t in Meeting(v,t));
		
		// rule annotations for rule  rule1:  -> LastMeeting(v,max t in Meeting(v,t));
		
		
	}
		
	

	// interface generated for queries	
	 
	public static ResultSet<LastMeetingRel> getLastMeeting (  String venue  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<LastMeetingRel>(getLastMeeting ( _derivation ,  venue ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<LastMeetingRel> getLastMeeting ( final DerivationController _derivation ,  final String venue  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<LastMeetingRel>(1) {
			
			public ResourceIterator<LastMeetingRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> LastMeeting(v,max t in Meeting(v,t));
                			return getLastMeeting_0(_derivation.pop(_derivationlevel) ,  venue );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: getLastMeeting
	// rule: rule1:  -> LastMeeting(v,max t in Meeting(v,t));
	private static ResourceIterator<LastMeetingRel> getLastMeeting_0 (final DerivationController _derivation ,  final String venue ) {
		
		_derivation.log("LastMeeting.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1:  -> LastMeeting(v,_max_0(MeetingRelInstances.getMeetings(v)));
		// prereqs: []
		class _Bindings {
			private java.lang.String v = venue;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		
		
		// rule head
		
		return new SingletonIterator(new LastMeetingRel(_bindings.v,_max_0(MeetingRelInstances.getMeetings(_bindings.v))));
        
		
		
		
	

	}
	
	
	// methods representing aggregation functions
	 
	
	
	
	// agg _max_0(MeetingRelInstances.getMeetings(v)) type is max
	 	static java.util.Calendar _max_0 (ResourceIterator<MeetingRel> _rel) {
		java.util.Calendar _v = null ;
		boolean f = true;
		while (_rel.hasNext()) {
		    MeetingRel _c = _rel.next();
		    if (f) {
		    	 _v = _c.time;
		    	
		    	f = false;
		    }
		    else {
				 if (_c.time.compareTo(_v)>0) {_v = _c.time;}
				
			}
		}
		_rel.close();
		return _v;
	}
	

	
	
	
}


