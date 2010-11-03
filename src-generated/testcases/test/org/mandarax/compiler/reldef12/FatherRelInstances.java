package test.org.mandarax.compiler.reldef12;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>Father</strong>.
 * @version Nov 3, 2010 1:08:10 PM 
 */
public class FatherRelInstances {
	// object references
	 
	public static Person jens = new Person("Jens");
	 
	public static Person max = new Person("Max");
	 
	public static Person klaus = new Person("Klaus");
	 
	public static Person otto = new Person("Otto");
	 
	public static Person xiomara = new Person("Xiomara");
	
	
	// fields representing annotations
	
	// rule: rule1:  -> Father(jens,max);
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	// rule: rule2:  -> Father(jens,xiomara);
	private final static java.util.Properties _annotations_rule2 = new java.util.Properties();
	
	// rule: rule3:  -> Father(klaus,jens);
	private final static java.util.Properties _annotations_rule3 = new java.util.Properties();
	
	// rule: rule4:  -> Father(otto,klaus);
	private final static java.util.Properties _annotations_rule4 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1:  -> Father(jens,max);
		
		// rule annotations for rule  rule1:  -> Father(jens,max);
		
		
	
		// relationship annotations for rule  rule2:  -> Father(jens,xiomara);
		
		// rule annotations for rule  rule2:  -> Father(jens,xiomara);
		
		
	
		// relationship annotations for rule  rule3:  -> Father(klaus,jens);
		
		// rule annotations for rule  rule3:  -> Father(klaus,jens);
		
		
	
		// relationship annotations for rule  rule4:  -> Father(otto,klaus);
		
		// rule annotations for rule  rule4:  -> Father(otto,klaus);
		
		
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
		return new IteratorChain<FatherRel>(4) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father(jens,max);
                			return getFather_0(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 1: {
                			// invoke rule2:  -> Father(jens,xiomara);
                			return getFather_1(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 2: {
                			// invoke rule3:  -> Father(klaus,jens);
                			return getFather_2(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 3: {
                			// invoke rule4:  -> Father(otto,klaus);
                			return getFather_3(_derivation.pop(_derivationlevel) ,  child );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<FatherRel> isFather ( final DerivationController _derivation ,  final Person father ,  final Person child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(4) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father(jens,max);
                			return isFather_0(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 1: {
                			// invoke rule2:  -> Father(jens,xiomara);
                			return isFather_1(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 2: {
                			// invoke rule3:  -> Father(klaus,jens);
                			return isFather_2(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 3: {
                			// invoke rule4:  -> Father(otto,klaus);
                			return isFather_3(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<FatherRel> getChildren ( final DerivationController _derivation ,  final Person father  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(4) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father(jens,max);
                			return getChildren_0(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 1: {
                			// invoke rule2:  -> Father(jens,xiomara);
                			return getChildren_1(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 2: {
                			// invoke rule3:  -> Father(klaus,jens);
                			return getChildren_2(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 3: {
                			// invoke rule4:  -> Father(otto,klaus);
                			return getChildren_3(_derivation.pop(_derivationlevel) ,  father );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<FatherRel> getFatherAndChild ( final DerivationController _derivation   ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(4) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father(jens,max);
                			return getFatherAndChild_0(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 1: {
                			// invoke rule2:  -> Father(jens,xiomara);
                			return getFatherAndChild_1(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 2: {
                			// invoke rule3:  -> Father(klaus,jens);
                			return getFatherAndChild_2(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 3: {
                			// invoke rule4:  -> Father(otto,klaus);
                			return getFatherAndChild_3(_derivation.pop(_derivationlevel)  );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: getFather
	// rule: rule1:  -> Father(jens,max);
	private static ResourceIterator<FatherRel> getFather_0 (final DerivationController _derivation ,  final Person child ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,max) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = jens;
		_result.child = max;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father(jens,xiomara);
	private static ResourceIterator<FatherRel> getFather_1 (final DerivationController _derivation ,  final Person child ) {
		
		_derivation.log("Father.rule2", DerivationController.RULE, _annotations_rule2);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,xiomara) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = jens;
		_result.child = xiomara;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father(klaus,jens);
	private static ResourceIterator<FatherRel> getFather_2 (final DerivationController _derivation ,  final Person child ) {
		
		_derivation.log("Father.rule3", DerivationController.RULE, _annotations_rule3);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,jens) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = klaus;
		_result.child = jens;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father(otto,klaus);
	private static ResourceIterator<FatherRel> getFather_3 (final DerivationController _derivation ,  final Person child ) {
		
		_derivation.log("Father.rule4", DerivationController.RULE, _annotations_rule4);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,klaus) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = otto;
		_result.child = klaus;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: isFather
	// rule: rule1:  -> Father(jens,max);
	private static ResourceIterator<FatherRel> isFather_0 (final DerivationController _derivation ,  final Person father ,  final Person child ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,jens) && Equals.compare(child,max) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = jens;
		_result.child = max;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father(jens,xiomara);
	private static ResourceIterator<FatherRel> isFather_1 (final DerivationController _derivation ,  final Person father ,  final Person child ) {
		
		_derivation.log("Father.rule2", DerivationController.RULE, _annotations_rule2);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,jens) && Equals.compare(child,xiomara) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = jens;
		_result.child = xiomara;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father(klaus,jens);
	private static ResourceIterator<FatherRel> isFather_2 (final DerivationController _derivation ,  final Person father ,  final Person child ) {
		
		_derivation.log("Father.rule3", DerivationController.RULE, _annotations_rule3);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,klaus) && Equals.compare(child,jens) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = klaus;
		_result.child = jens;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father(otto,klaus);
	private static ResourceIterator<FatherRel> isFather_3 (final DerivationController _derivation ,  final Person father ,  final Person child ) {
		
		_derivation.log("Father.rule4", DerivationController.RULE, _annotations_rule4);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,otto) && Equals.compare(child,klaus) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = otto;
		_result.child = klaus;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: getChildren
	// rule: rule1:  -> Father(jens,max);
	private static ResourceIterator<FatherRel> getChildren_0 (final DerivationController _derivation ,  final Person father ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,jens) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = jens;
		_result.child = max;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father(jens,xiomara);
	private static ResourceIterator<FatherRel> getChildren_1 (final DerivationController _derivation ,  final Person father ) {
		
		_derivation.log("Father.rule2", DerivationController.RULE, _annotations_rule2);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,jens) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = jens;
		_result.child = xiomara;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father(klaus,jens);
	private static ResourceIterator<FatherRel> getChildren_2 (final DerivationController _derivation ,  final Person father ) {
		
		_derivation.log("Father.rule3", DerivationController.RULE, _annotations_rule3);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,klaus) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = klaus;
		_result.child = jens;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father(otto,klaus);
	private static ResourceIterator<FatherRel> getChildren_3 (final DerivationController _derivation ,  final Person father ) {
		
		_derivation.log("Father.rule4", DerivationController.RULE, _annotations_rule4);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,otto) ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = otto;
		_result.child = klaus;
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: getFatherAndChild
	// rule: rule1:  -> Father(jens,max);
	private static ResourceIterator<FatherRel> getFatherAndChild_0 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = jens;
		_result.child = max;
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	// rule: rule2:  -> Father(jens,xiomara);
	private static ResourceIterator<FatherRel> getFatherAndChild_1 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule2", DerivationController.RULE, _annotations_rule2);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = jens;
		_result.child = xiomara;
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	// rule: rule3:  -> Father(klaus,jens);
	private static ResourceIterator<FatherRel> getFatherAndChild_2 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule3", DerivationController.RULE, _annotations_rule3);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = klaus;
		_result.child = jens;
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	// rule: rule4:  -> Father(otto,klaus);
	private static ResourceIterator<FatherRel> getFatherAndChild_3 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule4", DerivationController.RULE, _annotations_rule4);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = otto;
		_result.child = klaus;
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	
	
}
