package test.org.mandarax.compiler.reldef14;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>Father</strong>.
 * @version Nov 8, 2010 10:35:34 AM 
 */
public class FatherRelInstances {
	// object references
	 
	public static Person m1 = new Person("m1",25);
	 
	public static Person m2 = new Person("m2",5);
	 
	public static Person m3 = new Person("m3",5);
	 
	public static Person f1 = new Person("f1",25);
	
	
	// fields representing annotations
	
	// rule: rule1:  -> Father(m1,m2);
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1:  -> Father(m1,m2);
		
		// rule annotations for rule  rule1:  -> Father(m1,m2);
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<FatherRel> getFather (  Person child  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FatherRel>(getFather ( _derivation ,  child ),_derivation);
	} 
	 
	public ResultSet<FatherRel> isFather (  Person father ,  Person child  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FatherRel>(isFather ( _derivation ,  father ,  child ),_derivation);
	} 
	 
	public ResultSet<FatherRel> getChildren (  Person father  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FatherRel>(getChildren ( _derivation ,  father ),_derivation);
	} 
	 
	public ResultSet<FatherRel> getFatherAndChild (  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FatherRel>(getFatherAndChild ( _derivation  ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<FatherRel> getFather ( final DerivationController _derivation ,  final Person child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(1) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father(m1,m2);
                			return getFather_0(_derivation.pop(_derivationlevel) ,  child );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<FatherRel> isFather ( final DerivationController _derivation ,  final Person father ,  final Person child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(1) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father(m1,m2);
                			return isFather_0(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<FatherRel> getChildren ( final DerivationController _derivation ,  final Person father  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(1) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father(m1,m2);
                			return getChildren_0(_derivation.pop(_derivationlevel) ,  father );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<FatherRel> getFatherAndChild ( final DerivationController _derivation   ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(1) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father(m1,m2);
                			return getFatherAndChild_0(_derivation.pop(_derivationlevel)  );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: getFather
	// rule: rule1:  -> Father(m1,m2);
	private static ResourceIterator<FatherRel> getFather_0 (final DerivationController _derivation ,  final Person child ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,m2) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = m1;
		_result.child = m2;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: isFather
	// rule: rule1:  -> Father(m1,m2);
	private static ResourceIterator<FatherRel> isFather_0 (final DerivationController _derivation ,  final Person father ,  final Person child ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,m1) && Equals.compare(child,m2) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = m1;
		_result.child = m2;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: getChildren
	// rule: rule1:  -> Father(m1,m2);
	private static ResourceIterator<FatherRel> getChildren_0 (final DerivationController _derivation ,  final Person father ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,m1) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = m1;
		_result.child = m2;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: getFatherAndChild
	// rule: rule1:  -> Father(m1,m2);
	private static ResourceIterator<FatherRel> getFatherAndChild_0 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = m1;
		_result.child = m2;
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	
	
}

