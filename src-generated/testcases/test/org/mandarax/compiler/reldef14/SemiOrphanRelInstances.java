package test.org.mandarax.compiler.reldef14;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>SemiOrphan</strong>.
 * @version Nov 8, 2010 3:21:16 PM 
 */
public class SemiOrphanRelInstances {
	// object references
	 
	public static Person m1 = new Person("m1",25);
	 
	public static Person m2 = new Person("m2",5);
	 
	public static Person m3 = new Person("m3",5);
	 
	public static Person f1 = new Person("f1",25);
	
	
	// fields representing annotations
	
	// rule: rule1: isYoung(p) & Father(f,p) & not Mother(m,p) -> SemiOrphan(p);
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	// rule: rule2: isYoung(p) & not Father(f,p) & Mother(m,p) -> SemiOrphan(p);
	private final static java.util.Properties _annotations_rule2 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1: isYoung(p) & Father(f,p) & not Mother(m,p) -> SemiOrphan(p);
		
		// rule annotations for rule  rule1: isYoung(p) & Father(f,p) & not Mother(m,p) -> SemiOrphan(p);
		
		
	
		// relationship annotations for rule  rule2: isYoung(p) & not Father(f,p) & Mother(m,p) -> SemiOrphan(p);
		
		// rule annotations for rule  rule2: isYoung(p) & not Father(f,p) & Mother(m,p) -> SemiOrphan(p);
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<SemiOrphanRel> isSemiOrphan (  Person person  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<SemiOrphanRel>(isSemiOrphan ( _derivation ,  person ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<SemiOrphanRel> isSemiOrphan ( final DerivationController _derivation ,  final Person person  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<SemiOrphanRel>(2) {
			
			public ResourceIterator<SemiOrphanRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: isYoung(p) & Father(f,p) & not Mother(m,p) -> SemiOrphan(p);
                			return isSemiOrphan_0(_derivation.pop(_derivationlevel) ,  person );
                		}
				
                		case 1: {
                			// invoke rule2: isYoung(p) & not Father(f,p) & Mother(m,p) -> SemiOrphan(p);
                			return isSemiOrphan_1(_derivation.pop(_derivationlevel) ,  person );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: isSemiOrphan
	// rule: rule1: isYoung(p) & Father(f,p) & not Mother(m,p) -> SemiOrphan(p);
	private static ResourceIterator<SemiOrphanRel> isSemiOrphan_0 (final DerivationController _derivation ,  final Person person ) {
		
		_derivation.log("SemiOrphan.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: isYoung(p) & Father(f,p) & not Mother(m,p) -> SemiOrphan(p);
		// prereqs: [isYoung(p), Father(f,p), not Mother(m,p)]
		class _Bindings {
			private test.org.mandarax.compiler.Person p = person;
			private test.org.mandarax.compiler.Person f = null;
			private test.org.mandarax.compiler.Person m = null;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 

		 
		
		
		
		// apply prerequisite isYoung(p)
		
		
		 // case 4
					
					_tmp = isYoungRelInstances.isYoung(_derivation.push(),_bindings.p);
					
					if (!_tmp.hasNext()) {
						_tmp.close();
						return EmptyIterator.DEFAULT;
					}
					
					
		 
		
		
		
		// apply prerequisite Father(f,p)
		
		
		  // case 1
		final ResourceIterator<FatherRel> _iterator2 = FatherRelInstances.getFather(_derivation.push(),_bindings.p);
		
		
		
		 
		
		
		
		// apply prerequisite not Mother(m,p)
		
		
		  // case 3
					
					com.google.common.base.Predicate<FatherRel> _filter3 = new com.google.common.base.Predicate<FatherRel>() {
						public boolean apply(FatherRel _object) {
						        // bind parameters from Father(f,p)
								_bindings.f = _object.father;
								
								
									ResourceIterator<MotherRel> _r =  MotherRelInstances.getMother(_derivation.push(),_bindings.p);
									boolean _b = _r.hasNext();
									_r.close();
									return !_b;
								
							}
					};
					final ResourceIterator<FatherRel> _iterator3 =  new FilteredIterator<FatherRel>(_iterator2,_filter3);
		
					 
		
		
		// rule head
		
		return new NestedIterator<FatherRel, SemiOrphanRel>(_iterator3) {
                	public ResourceIterator<SemiOrphanRel> getNextIterator(FatherRel _object) {
						// bind parameters from Father(f,p)
						_bindings.f = _object.father;
						
                    				return new SingletonIterator(new SemiOrphanRel(_bindings.p));
                	}
        	};
        
		
		
		
	

		
	}
	// rule: rule2: isYoung(p) & not Father(f,p) & Mother(m,p) -> SemiOrphan(p);
	private static ResourceIterator<SemiOrphanRel> isSemiOrphan_1 (final DerivationController _derivation ,  final Person person ) {
		
		_derivation.log("SemiOrphan.rule2", DerivationController.RULE, _annotations_rule2);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule2: isYoung(p) & not Father(f,p) & Mother(m,p) -> SemiOrphan(p);
		// prereqs: [isYoung(p), Mother(m,p), not Father(f,p)]
		class _Bindings {
			private test.org.mandarax.compiler.Person p = person;
			private test.org.mandarax.compiler.Person f = null;
			private test.org.mandarax.compiler.Person m = null;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 

		 
		
		
		
		// apply prerequisite isYoung(p)
		
		
		 // case 4
					
					_tmp = isYoungRelInstances.isYoung(_derivation.push(),_bindings.p);
					
					if (!_tmp.hasNext()) {
						_tmp.close();
						return EmptyIterator.DEFAULT;
					}
					
					
		 
		
		
		
		// apply prerequisite Mother(m,p)
		
		
		  // case 1
		final ResourceIterator<MotherRel> _iterator2 = MotherRelInstances.getMother(_derivation.push(),_bindings.p);
		
		
		
		 
		
		
		
		// apply prerequisite not Father(f,p)
		
		
		  // case 3
					
					com.google.common.base.Predicate<MotherRel> _filter3 = new com.google.common.base.Predicate<MotherRel>() {
						public boolean apply(MotherRel _object) {
						        // bind parameters from Mother(m,p)
								_bindings.m = _object.mother;
								
								
									ResourceIterator<FatherRel> _r =  FatherRelInstances.getFather(_derivation.push(),_bindings.p);
									boolean _b = _r.hasNext();
									_r.close();
									return !_b;
								
							}
					};
					final ResourceIterator<MotherRel> _iterator3 =  new FilteredIterator<MotherRel>(_iterator2,_filter3);
		
					 
		
		
		// rule head
		
		return new NestedIterator<MotherRel, SemiOrphanRel>(_iterator3) {
                	public ResourceIterator<SemiOrphanRel> getNextIterator(MotherRel _object) {
						// bind parameters from Mother(m,p)
						_bindings.m = _object.mother;
						
                    				return new SingletonIterator(new SemiOrphanRel(_bindings.p));
                	}
        	};
        
		
		
		
	

		
	}
	
	
}

