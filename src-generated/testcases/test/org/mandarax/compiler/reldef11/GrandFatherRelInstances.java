package test.org.mandarax.compiler.reldef11;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>GrandFather</strong>.
 * @version Nov 2, 2010 7:09:58 PM 
 */
public class GrandFatherRelInstances {
	// object references
	 
	public static Person jens = new Person("Jens");
	 
	public static Person max = new Person("Max");
	 
	public static Person klaus = new Person("Klaus");
	 
	public static Person otto = new Person("Otto");
	 
	public static Person xiomara = new Person("Xiomara");
	
	
	// fields representing annotations
	
	// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x.getName(),z.getName());
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1: Father(x,y) & Father(y,z) -> GrandFather(x.getName(),z.getName());
		
		// rule annotations for rule  rule1: Father(x,y) & Father(y,z) -> GrandFather(x.getName(),z.getName());
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<GrandFatherRel> getAll (  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<GrandFatherRel>(getAll ( _derivation  ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<GrandFatherRel> getAll ( final DerivationController _derivation   ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<GrandFatherRel>(1) {
			
			public ResourceIterator<GrandFatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: Father(x,y) & Father(y,z) -> GrandFather(x.getName(),z.getName());
                			return getAll_0(_derivation.pop(_derivationlevel)  );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: getAll
	// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x.getName(),z.getName());
	private static ResourceIterator<GrandFatherRel> getAll_0 (final DerivationController _derivation  ) {
		
		_derivation.log("GrandFather.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Person x = null;
			private test.org.mandarax.compiler.Person z = null;
			private test.org.mandarax.compiler.Person y = null;
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite Father(x,y)
		final ResourceIterator<FatherRel> iterator1 = FatherRelInstances.getFatherAndChild(_derivation.push());
		
		
		 
		// apply prerequisite Father(y,z)
		final ResourceIterator<FatherRel> iterator2 =  new NestedIterator<FatherRel, FatherRel>(iterator1) {
                	public ResourceIterator<FatherRel> getNextIterator(FatherRel _object) {
                				// bind parameters from Father(x,y)
						_bindings.x = _object.father;
						_bindings.y = _object.child;
						
									return FatherRelInstances.getChildren(_derivation.push(),_bindings.y);
                	}
            	};
		
		
		
		// rule head
		return new NestedIterator<FatherRel, GrandFatherRel>(iterator2) {
                	public ResourceIterator<GrandFatherRel> getNextIterator(FatherRel _object) {
						// bind parameters from Father(y,z)
						_bindings.z = _object.child;
						
                    				return new SingletonIterator(new GrandFatherRel(_bindings.x.getName(),_bindings.z.getName()));
                	}
        	};
        
		
		
		
	

		
	}
	
	
}

