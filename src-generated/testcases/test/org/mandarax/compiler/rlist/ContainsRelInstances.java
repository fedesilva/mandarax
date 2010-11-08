package test.org.mandarax.compiler.rlist;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>Contains</strong>.
 * @version Nov 9, 2010 11:29:30 AM 
 */
public class ContainsRelInstances {
	// object references
	
	
	// fields representing annotations
	
	// rule: rule1: true -> Contains(l,l.getHead());
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	// rule: rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
	private final static java.util.Properties _annotations_rule2 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1: true -> Contains(l,l.getHead());
		
		// rule annotations for rule  rule1: true -> Contains(l,l.getHead());
		
		
	
		// relationship annotations for rule  rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
		
		// rule annotations for rule  rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<ContainsRel> contains (  RList list ,  Object element  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<ContainsRel>(contains ( _derivation ,  list ,  element ),_derivation);
	} 
	 
	public ResultSet<ContainsRel> getElements (  RList list  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<ContainsRel>(getElements ( _derivation ,  list ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<ContainsRel> contains ( final DerivationController _derivation ,  final RList list ,  final Object element  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<ContainsRel>(2) {
			
			public ResourceIterator<ContainsRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: true -> Contains(l,l.getHead());
                			return contains_0(_derivation.pop(_derivationlevel) ,  list ,  element );
                		}
				
                		case 1: {
                			// invoke rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
                			return contains_1(_derivation.pop(_derivationlevel) ,  list ,  element );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<ContainsRel> getElements ( final DerivationController _derivation ,  final RList list  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<ContainsRel>(2) {
			
			public ResourceIterator<ContainsRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1: true -> Contains(l,l.getHead());
                			return getElements_0(_derivation.pop(_derivationlevel) ,  list );
                		}
				
                		case 1: {
                			// invoke rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
                			return getElements_1(_derivation.pop(_derivationlevel) ,  list );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: contains
	// rule: rule1: true -> Contains(l,l.getHead());
	private static ResourceIterator<ContainsRel> contains_0 (final DerivationController _derivation ,  final RList list ,  final Object element ) {
		
		_derivation.log("Contains.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: true & __t0==(l.getHead()) -> Contains(l,__t0);
		// prereqs: [true, __t0==(l.getHead())]
		class _Bindings {
			private test.org.mandarax.compiler.RList l = list;
			private java.lang.Object __t0 = element;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite true
		
		
		 // case 4
					if (!(true)) {return EmptyIterator.DEFAULT;} 
					
		 
		
		
		
		// apply prerequisite __t0==(l.getHead())
		
		
		 // case 4
					if (!(org.mandarax.rt.Equals.compare(_bindings.__t0,_bindings.l.getHead()))) {return EmptyIterator.DEFAULT;} 
					
		
		
		// rule head
		
		return new SingletonIterator(new ContainsRel(_bindings.l,_bindings.__t0));
        
		
		
		
	

	}
	// rule: rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
	private static ResourceIterator<ContainsRel> contains_1 (final DerivationController _derivation ,  final RList list ,  final Object element ) {
		
		_derivation.log("Contains.rule2", DerivationController.RULE, _annotations_rule2);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
		// prereqs: [(l.getTail())!=null, Contains(l.getTail(),e)]
		class _Bindings {
			private test.org.mandarax.compiler.RList l = list;
			private java.lang.Object e = element;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite (l.getTail())!=null
		
		
		 // case 4
					if (!((_bindings.l.getTail())!=null)) {return EmptyIterator.DEFAULT;} 
					
		 
		
		
		
		// apply prerequisite Contains(l.getTail(),e)
		
		
		 // case 4
					
					_tmp = ContainsRelInstances.contains(_derivation.push(),_bindings.l.getTail(),_bindings.e);
					
					if (!_tmp.hasNext()) {
						_tmp.close();
						return EmptyIterator.DEFAULT;
					}
					
					
		
		
		// rule head
		
		return new SingletonIterator(new ContainsRel(_bindings.l,_bindings.e));
        
		
		
		
	

	}
	// query: getElements
	// rule: rule1: true -> Contains(l,l.getHead());
	private static ResourceIterator<ContainsRel> getElements_0 (final DerivationController _derivation ,  final RList list ) {
		
		_derivation.log("Contains.rule1", DerivationController.RULE, _annotations_rule1);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: true -> Contains(l,l.getHead());
		// prereqs: [true]
		class _Bindings {
			private test.org.mandarax.compiler.RList l = list;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite true
		
		
		 // case 4
					if (!(true)) {return EmptyIterator.DEFAULT;} 
					
		
		
		// rule head
		
		return new SingletonIterator(new ContainsRel(_bindings.l,_bindings.l.getHead()));
        
		
		
		
	

	}
	// rule: rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
	private static ResourceIterator<ContainsRel> getElements_1 (final DerivationController _derivation ,  final RList list ) {
		
		_derivation.log("Contains.rule2", DerivationController.RULE, _annotations_rule2);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: rule2: (l.getTail())!=null & Contains(l.getTail(),e) -> Contains(l,e);
		// prereqs: [(l.getTail())!=null, Contains(l.getTail(),e)]
		class _Bindings {
			private test.org.mandarax.compiler.RList l = list;
			private java.lang.Object e = null;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite (l.getTail())!=null
		
		
		 // case 4
					if (!((_bindings.l.getTail())!=null)) {return EmptyIterator.DEFAULT;} 
					
		 
		
		
		
		// apply prerequisite Contains(l.getTail(),e)
		
		
		  // case 1
		final ResourceIterator<ContainsRel> _iterator2 = ContainsRelInstances.getElements(_derivation.push(),_bindings.l.getTail());
		
		
		
		
		
		// rule head
		
		return new NestedIterator<ContainsRel, ContainsRel>(_iterator2) {
                	public ResourceIterator<ContainsRel> getNextIterator(ContainsRel _object) {
						// bind parameters from Contains(l.getTail(),e)
						_bindings.e = _object.element;
						
                    				return new SingletonIterator(new ContainsRel(_bindings.l,_bindings.e));
                	}
        	};
        
		
		
		
	

	}
	
	
}

