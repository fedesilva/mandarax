package test.org.mandarax.compiler.reldef5;
 
import test.org.mandarax.compiler.Person;

import org.mandarax.rt.*;
import com.google.common.base.Predicate;

/**
 * Interface for queries for relationship <strong>GrandFather</strong>.
 * @version Oct 26, 2010 9:28:37 PM 
 */
public class GrandFatherInstances {



	// interface generated for queries
	
	 
	public ResultSet<GrandFather> isGrandFather (  Person grandFather ,  Person grandChild  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<GrandFather>(isGrandFather ( _derivation ,  grandFather ,  grandChild ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<GrandFather> isGrandFather ( final DerivationController _derivation ,  final Person grandFather ,  final Person grandChild  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<GrandFather>(1) {
			
			public ResourceIterator<GrandFather> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			return isGrandFather_0(_derivation.pop(_derivationlevel) ,  grandFather ,  grandChild );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	
	// query: isGrandFather
	// rule: rule1: Father(x.getName(),y) & Father(y,z.getName()) -> GrandFather(x,z);
	private static ResourceIterator<GrandFather> isGrandFather_0 (final DerivationController _derivation ,  final Person grandFather ,  final Person grandChild ) {
		
			
	
		_derivation.log("rule1", DerivationController.RULE, DerivationController.NIL);
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Person x = grandFather; // term x
			private test.org.mandarax.compiler.Person z = grandChild; // term z
			private java.lang.String y = null; // term y
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite Father(x.getName(),y)
		final ResourceIterator<Father> iterator1 = FatherInstances.getChildren(_derivation.push(),_bindings.x.getName());
		
		
		 
		// apply prerequisite Father(y,z.getName())
		final ResourceIterator<Father> iterator2 =  new NestedIterator<Father, Father>(iterator1) {
                	public ResourceIterator<Father> getNextIterator(Father _object) {
                				// bind parameters from Father(x.getName(),y)
						_bindings.y = _object.child;
						
									return FatherInstances.isFather(_derivation.push(),_bindings.y,_bindings.z.getName());
                	}
            	};
		
		
		
		// rule head
		return new NestedIterator<Father, GrandFather>(iterator2) {
                	public ResourceIterator<GrandFather> getNextIterator(Father _object) {
						// bind parameters from Father(y,z.getName())
						
                    				return new SingletonIterator(new GrandFather(_bindings.x,_bindings.z));
                	}
        	};
        
		
		
		
	

		
	}
	
	
}

