package test.org.mandarax.compiler.reldef10;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>GrandFather</strong>.
 * @version Nov 3, 2010 1:08:09 PM 
 */
public class GrandFatherRelInstances {
	// object references
	
	
	// fields representing annotations
	
	// rule: rule1: Father(x.getName(),y.getName()) & Father(y.getName(),z.getName()) -> GrandFather(x,z);
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1: Father(x.getName(),y.getName()) & Father(y.getName(),z.getName()) -> GrandFather(x,z);
		
		// rule annotations for rule  rule1: Father(x.getName(),y.getName()) & Father(y.getName(),z.getName()) -> GrandFather(x,z);
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<GrandFatherRel> isGrandFather (  MalePerson grandFather ,  MalePerson grandChild  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<GrandFatherRel>(isGrandFather ( _derivation ,  grandFather ,  grandChild ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<GrandFatherRel> isGrandFather ( final DerivationController _derivation ,  final MalePerson grandFather ,  final MalePerson grandChild  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<GrandFatherRel>(1) {
			
			public ResourceIterator<GrandFatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: Father(x.getName(),y.getName()) & Father(y.getName(),z.getName()) -> GrandFather(x,z);
                			return isGrandFather_0(_derivation.pop(_derivationlevel) ,  grandFather ,  grandChild );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: isGrandFather
	// rule: rule1: Father(x.getName(),y.getName()) & Father(y.getName(),z.getName()) -> GrandFather(x,z);
	private static ResourceIterator<GrandFatherRel> isGrandFather_0 (final DerivationController _derivation ,  final MalePerson grandFather ,  final MalePerson grandChild ) {
		
		_derivation.log("GrandFather.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: Father(x.getName(),__t0) & Father(__t0,z.getName()) -> GrandFather(x,z);
		// prereqs: [Father(x.getName(),__t0), Father(__t0,z.getName())]
		class _Bindings {
			private test.org.mandarax.compiler.MalePerson x = grandFather;
			private test.org.mandarax.compiler.MalePerson z = grandChild;
			private java.lang.String __t0 = null;
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite Father(x.getName(),__t0)
		final ResourceIterator<FatherRel> iterator1 = FatherRelInstances.getChildren(_derivation.push(),_bindings.x.getName());
		
		
		 
		// apply prerequisite Father(__t0,z.getName())
		final ResourceIterator<FatherRel> iterator2 =  new NestedIterator<FatherRel, FatherRel>(iterator1) {
                	public ResourceIterator<FatherRel> getNextIterator(FatherRel _object) {
                				// bind parameters from Father(x.getName(),__t0)
						_bindings.__t0 = _object.child;
						
									return FatherRelInstances.isFather(_derivation.push(),_bindings.__t0,_bindings.z.getName());
                	}
            	};
		
		
		
		// rule head
		return new NestedIterator<FatherRel, GrandFatherRel>(iterator2) {
                	public ResourceIterator<GrandFatherRel> getNextIterator(FatherRel _object) {
						// bind parameters from Father(__t0,z.getName())
						
                    				return new SingletonIterator(new GrandFatherRel(_bindings.x,_bindings.z));
                	}
        	};
        
		
		
		
	

		
	}
	
	
}

