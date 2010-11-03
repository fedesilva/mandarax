package test.org.mandarax.compiler.reldef4;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>GrandFather</strong>.
 * @version Nov 3, 2010 1:08:09 PM 
 */
public class GrandFatherRelInstances {
	// object references
	
	
	// fields representing annotations
	
	// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
		
		// rule annotations for rule  rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<GrandFatherRel> getGrandChildren (  String grandFather  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<GrandFatherRel>(getGrandChildren ( _derivation ,  grandFather ),_derivation);
	} 
	 
	public ResultSet<GrandFatherRel> getGrandFather (  String grandChild  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<GrandFatherRel>(getGrandFather ( _derivation ,  grandChild ),_derivation);
	} 
	 
	public ResultSet<GrandFatherRel> isGrandFather (  String grandFather ,  String grandChild  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<GrandFatherRel>(isGrandFather ( _derivation ,  grandFather ,  grandChild ),_derivation);
	} 
	 
	public ResultSet<GrandFatherRel> getAll (  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<GrandFatherRel>(getAll ( _derivation  ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<GrandFatherRel> getGrandChildren ( final DerivationController _derivation ,  final String grandFather  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<GrandFatherRel>(1) {
			
			public ResourceIterator<GrandFatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
                			return getGrandChildren_0(_derivation.pop(_derivationlevel) ,  grandFather );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<GrandFatherRel> getGrandFather ( final DerivationController _derivation ,  final String grandChild  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<GrandFatherRel>(1) {
			
			public ResourceIterator<GrandFatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
                			return getGrandFather_0(_derivation.pop(_derivationlevel) ,  grandChild );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<GrandFatherRel> isGrandFather ( final DerivationController _derivation ,  final String grandFather ,  final String grandChild  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<GrandFatherRel>(1) {
			
			public ResourceIterator<GrandFatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
                			return isGrandFather_0(_derivation.pop(_derivationlevel) ,  grandFather ,  grandChild );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<GrandFatherRel> getAll ( final DerivationController _derivation   ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<GrandFatherRel>(1) {
			
			public ResourceIterator<GrandFatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
                			return getAll_0(_derivation.pop(_derivationlevel)  );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: getGrandChildren
	// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
	private static ResourceIterator<GrandFatherRel> getGrandChildren_0 (final DerivationController _derivation ,  final String grandFather ) {
		
		_derivation.log("GrandFather.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
		// prereqs: [Father(x,y), Father(y,z)]
		class _Bindings {
			private java.lang.String x = grandFather;
			private java.lang.String z = null;
			private java.lang.String y = null;
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite Father(x,y)
		final ResourceIterator<FatherRel> iterator1 = FatherRelInstances.getChildren(_derivation.push(),_bindings.x);
		
		
		 
		// apply prerequisite Father(y,z)
		final ResourceIterator<FatherRel> iterator2 =  new NestedIterator<FatherRel, FatherRel>(iterator1) {
                	public ResourceIterator<FatherRel> getNextIterator(FatherRel _object) {
                				// bind parameters from Father(x,y)
						_bindings.y = _object.child;
						
									return FatherRelInstances.getChildren(_derivation.push(),_bindings.y);
                	}
            	};
		
		
		
		// rule head
		return new NestedIterator<FatherRel, GrandFatherRel>(iterator2) {
                	public ResourceIterator<GrandFatherRel> getNextIterator(FatherRel _object) {
						// bind parameters from Father(y,z)
						_bindings.z = _object.child;
						
                    				return new SingletonIterator(new GrandFatherRel(_bindings.x,_bindings.z));
                	}
        	};
        
		
		
		
	

		
	}
	// query: getGrandFather
	// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
	private static ResourceIterator<GrandFatherRel> getGrandFather_0 (final DerivationController _derivation ,  final String grandChild ) {
		
		_derivation.log("GrandFather.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
		// prereqs: [Father(x,y), Father(y,z)]
		class _Bindings {
			private java.lang.String x = null;
			private java.lang.String z = grandChild;
			private java.lang.String y = null;
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
						
									return FatherRelInstances.isFather(_derivation.push(),_bindings.y,_bindings.z);
                	}
            	};
		
		
		
		// rule head
		return new NestedIterator<FatherRel, GrandFatherRel>(iterator2) {
                	public ResourceIterator<GrandFatherRel> getNextIterator(FatherRel _object) {
						// bind parameters from Father(y,z)
						
                    				return new SingletonIterator(new GrandFatherRel(_bindings.x,_bindings.z));
                	}
        	};
        
		
		
		
	

		
	}
	// query: isGrandFather
	// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
	private static ResourceIterator<GrandFatherRel> isGrandFather_0 (final DerivationController _derivation ,  final String grandFather ,  final String grandChild ) {
		
		_derivation.log("GrandFather.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
		// prereqs: [Father(x,y), Father(y,z)]
		class _Bindings {
			private java.lang.String x = grandFather;
			private java.lang.String z = grandChild;
			private java.lang.String y = null;
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite Father(x,y)
		final ResourceIterator<FatherRel> iterator1 = FatherRelInstances.getChildren(_derivation.push(),_bindings.x);
		
		
		 
		// apply prerequisite Father(y,z)
		final ResourceIterator<FatherRel> iterator2 =  new NestedIterator<FatherRel, FatherRel>(iterator1) {
                	public ResourceIterator<FatherRel> getNextIterator(FatherRel _object) {
                				// bind parameters from Father(x,y)
						_bindings.y = _object.child;
						
									return FatherRelInstances.isFather(_derivation.push(),_bindings.y,_bindings.z);
                	}
            	};
		
		
		
		// rule head
		return new NestedIterator<FatherRel, GrandFatherRel>(iterator2) {
                	public ResourceIterator<GrandFatherRel> getNextIterator(FatherRel _object) {
						// bind parameters from Father(y,z)
						
                    				return new SingletonIterator(new GrandFatherRel(_bindings.x,_bindings.z));
                	}
        	};
        
		
		
		
	

		
	}
	// query: getAll
	// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
	private static ResourceIterator<GrandFatherRel> getAll_0 (final DerivationController _derivation  ) {
		
		_derivation.log("GrandFather.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
		// prereqs: [Father(x,y), Father(y,z)]
		class _Bindings {
			private java.lang.String x = null;
			private java.lang.String z = null;
			private java.lang.String y = null;
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
						
                    				return new SingletonIterator(new GrandFatherRel(_bindings.x,_bindings.z));
                	}
        	};
        
		
		
		
	

		
	}
	
	
}
