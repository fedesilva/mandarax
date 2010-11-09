package test.org.mandarax.compiler.naf1;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>isYoung</strong>.
 * @version Nov 9, 2010 3:23:58 PM 
 */
public class isYoungRelInstances {
	// object references
	 
	public static Person m1 = new Person("m1",25);
	 
	public static Person m2 = new Person("m2",5);
	 
	public static Person m3 = new Person("m3",5);
	 
	public static Person f1 = new Person("f1",25);
	
	
	// fields representing annotations
	
	// rule: rule1: (p.getAge())<=18 -> isYoung(p);
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1: (p.getAge())<=18 -> isYoung(p);
		
		// rule annotations for rule  rule1: (p.getAge())<=18 -> isYoung(p);
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<isYoungRel> isYoung (  Person person  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<isYoungRel>(isYoung ( _derivation ,  person ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<isYoungRel> isYoung ( final DerivationController _derivation ,  final Person person  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<isYoungRel>(1) {
			
			public ResourceIterator<isYoungRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: (p.getAge())<=18 -> isYoung(p);
                			return isYoung_0(_derivation.pop(_derivationlevel) ,  person );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: isYoung
	// rule: rule1: (p.getAge())<=18 -> isYoung(p);
	private static ResourceIterator<isYoungRel> isYoung_0 (final DerivationController _derivation ,  final Person person ) {
		
		_derivation.log("isYoung.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: (p.getAge())<=18 -> isYoung(p);
		// prereqs: [(p.getAge())<=18]
		class _Bindings {
			private test.org.mandarax.compiler.Person p = person;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite (p.getAge())<=18
		
		
		 // case 4
					if (!((_bindings.p.getAge())<=18)) {return EmptyIterator.DEFAULT;} 
					
		
		
		// rule head
		
		return new SingletonIterator(new isYoungRel(_bindings.p));
        
		
		
		
	

	}
	
	
}
