package test.org.mandarax.compiler.reldef14;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>HasName</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version 25/12/2010 9:37:32 AM 
 */
public class HasNameRelInstances {
	// object references
	
	
	// fields representing annotations
	
	// rule: rule1:  -> HasName(p,p.getName());
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1:  -> HasName(p,p.getName());
		
		// rule annotations for rule  rule1:  -> HasName(p,p.getName());
		
		
	}
		
	

	// interface generated for queries	
	 
	public static ResultSet<HasNameRel> getName (  Person person  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<HasNameRel>(getName ( _derivation ,  person ),_derivation);
	} 
	 
	public static ResultSet<HasNameRel> hasName (  Person person ,  String name  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<HasNameRel>(hasName ( _derivation ,  person ,  name ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<HasNameRel> getName ( final DerivationController _derivation ,  final Person person  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<HasNameRel>(1) {
			
			public ResourceIterator<HasNameRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> HasName(p,p.getName());
                			return getName_0(_derivation.pop(_derivationlevel) ,  person );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<HasNameRel> hasName ( final DerivationController _derivation ,  final Person person ,  final String name  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<HasNameRel>(1) {
			
			public ResourceIterator<HasNameRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> HasName(p,p.getName());
                			return hasName_0(_derivation.pop(_derivationlevel) ,  person ,  name );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: getName
	// rule: rule1:  -> HasName(p,p.getName());
	private static ResourceIterator<HasNameRel> getName_0 (final DerivationController _derivation ,  final Person person ) {
		
		
			_derivation.log("test.org.mandarax.compiler.reldef14.HasName.rule1", DerivationController.RULE, _annotations_rule1);		
				
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1:  -> HasName(p,p.getName());
		// prereqs: []
		class _Bindings {
			private test.org.mandarax.compiler.Person p = person;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		
		
		// rule head
		
		return new SingletonIterator(new HasNameRel(_bindings.p,_bindings.p.getName()));
        
		
		
		
	

		
	}
	// query: hasName
	// rule: rule1:  -> HasName(p,p.getName());
	private static ResourceIterator<HasNameRel> hasName_0 (final DerivationController _derivation ,  final Person person ,  final String name ) {
		
		
			_derivation.log("test.org.mandarax.compiler.reldef14.HasName.rule1", DerivationController.RULE, _annotations_rule1);		
				
	
		
		// utility class used to keep track of variables bindings
		// rule: rule1: __t0==(p.getName()) -> HasName(p,__t0);
		// prereqs: [__t0==(p.getName())]
		class _Bindings {
			private test.org.mandarax.compiler.Person p = person;
			private java.lang.String __t0 = name;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite __t0==(p.getName())
		
		
		 // case 4
					if (!(org.mandarax.rt.Equals.compare(_bindings.__t0,_bindings.p.getName()))) {return EmptyIterator.DEFAULT;} 
					
		
		
		// rule head
		
		return new SingletonIterator(new HasNameRel(_bindings.p,_bindings.__t0));
        
		
		
		
	

		
	}
	
	
	// methods representing aggregation functions
	
	
}


