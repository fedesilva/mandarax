package test.org.mandarax.compiler.reldef8;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>Father</strong>.
 * @version Oct 29, 2010 2:37:45 PM 
 */
public class FatherRelInstances {
	// object references
	
	
	// fields representing annotations
	
	// rule: rule1:  -> Father("Jens","Max");
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	// rule: rule2:  -> Father("Jens","Xiomara");
	private final static java.util.Properties _annotations_rule2 = new java.util.Properties();
	
	// rule: rule3:  -> Father("Klaus","Jens");
	private final static java.util.Properties _annotations_rule3 = new java.util.Properties();
	
	// rule: rule4:  -> Father("Otto","Klaus");
	private final static java.util.Properties _annotations_rule4 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1:  -> Father("Jens","Max");
		
		// rule annotations for rule  rule1:  -> Father("Jens","Max");
		
		
	
		// relationship annotations for rule  rule2:  -> Father("Jens","Xiomara");
		
		// rule annotations for rule  rule2:  -> Father("Jens","Xiomara");
		
		
	
		// relationship annotations for rule  rule3:  -> Father("Klaus","Jens");
		
		// rule annotations for rule  rule3:  -> Father("Klaus","Jens");
		
		
	
		// relationship annotations for rule  rule4:  -> Father("Otto","Klaus");
		
		// rule annotations for rule  rule4:  -> Father("Otto","Klaus");
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<FatherRel> getFather (  String child  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FatherRel>(getFather ( _derivation ,  child ),_derivation);
	} 
	 
	public ResultSet<FatherRel> isFather (  String father ,  String child  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FatherRel>(isFather ( _derivation ,  father ,  child ),_derivation);
	} 
	 
	public ResultSet<FatherRel> getChildren (  String father  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FatherRel>(getChildren ( _derivation ,  father ),_derivation);
	} 
	 
	public ResultSet<FatherRel> getFatherAndChild (  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FatherRel>(getFatherAndChild ( _derivation  ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<FatherRel> getFather ( final DerivationController _derivation ,  final String child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(4) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father("Jens","Max");
                			return getFather_0(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 1: {
                			// invoke rule2:  -> Father("Jens","Xiomara");
                			return getFather_1(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 2: {
                			// invoke rule3:  -> Father("Klaus","Jens");
                			return getFather_2(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 3: {
                			// invoke rule4:  -> Father("Otto","Klaus");
                			return getFather_3(_derivation.pop(_derivationlevel) ,  child );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<FatherRel> isFather ( final DerivationController _derivation ,  final String father ,  final String child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(4) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father("Jens","Max");
                			return isFather_0(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 1: {
                			// invoke rule2:  -> Father("Jens","Xiomara");
                			return isFather_1(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 2: {
                			// invoke rule3:  -> Father("Klaus","Jens");
                			return isFather_2(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 3: {
                			// invoke rule4:  -> Father("Otto","Klaus");
                			return isFather_3(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<FatherRel> getChildren ( final DerivationController _derivation ,  final String father  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FatherRel>(4) {
			
			public ResourceIterator<FatherRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke rule1:  -> Father("Jens","Max");
                			return getChildren_0(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 1: {
                			// invoke rule2:  -> Father("Jens","Xiomara");
                			return getChildren_1(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 2: {
                			// invoke rule3:  -> Father("Klaus","Jens");
                			return getChildren_2(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 3: {
                			// invoke rule4:  -> Father("Otto","Klaus");
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
                			// invoke rule1:  -> Father("Jens","Max");
                			return getFatherAndChild_0(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 1: {
                			// invoke rule2:  -> Father("Jens","Xiomara");
                			return getFatherAndChild_1(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 2: {
                			// invoke rule3:  -> Father("Klaus","Jens");
                			return getFatherAndChild_2(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 3: {
                			// invoke rule4:  -> Father("Otto","Klaus");
                			return getFatherAndChild_3(_derivation.pop(_derivationlevel)  );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: getFather
	// rule: rule1:  -> Father("Jens","Max");
	private static ResourceIterator<FatherRel> getFather_0 (final DerivationController _derivation ,  final String child ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,"Max") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Jens";
		_result.child = "Max";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father("Jens","Xiomara");
	private static ResourceIterator<FatherRel> getFather_1 (final DerivationController _derivation ,  final String child ) {
		
		_derivation.log("Father.rule2", DerivationController.RULE, _annotations_rule2);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,"Xiomara") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Jens";
		_result.child = "Xiomara";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father("Klaus","Jens");
	private static ResourceIterator<FatherRel> getFather_2 (final DerivationController _derivation ,  final String child ) {
		
		_derivation.log("Father.rule3", DerivationController.RULE, _annotations_rule3);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,"Jens") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Klaus";
		_result.child = "Jens";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father("Otto","Klaus");
	private static ResourceIterator<FatherRel> getFather_3 (final DerivationController _derivation ,  final String child ) {
		
		_derivation.log("Father.rule4", DerivationController.RULE, _annotations_rule4);
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,"Klaus") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Otto";
		_result.child = "Klaus";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: isFather
	// rule: rule1:  -> Father("Jens","Max");
	private static ResourceIterator<FatherRel> isFather_0 (final DerivationController _derivation ,  final String father ,  final String child ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Jens") && Equals.compare(child,"Max") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Jens";
		_result.child = "Max";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father("Jens","Xiomara");
	private static ResourceIterator<FatherRel> isFather_1 (final DerivationController _derivation ,  final String father ,  final String child ) {
		
		_derivation.log("Father.rule2", DerivationController.RULE, _annotations_rule2);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Jens") && Equals.compare(child,"Xiomara") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Jens";
		_result.child = "Xiomara";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father("Klaus","Jens");
	private static ResourceIterator<FatherRel> isFather_2 (final DerivationController _derivation ,  final String father ,  final String child ) {
		
		_derivation.log("Father.rule3", DerivationController.RULE, _annotations_rule3);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Klaus") && Equals.compare(child,"Jens") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Klaus";
		_result.child = "Jens";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father("Otto","Klaus");
	private static ResourceIterator<FatherRel> isFather_3 (final DerivationController _derivation ,  final String father ,  final String child ) {
		
		_derivation.log("Father.rule4", DerivationController.RULE, _annotations_rule4);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Otto") && Equals.compare(child,"Klaus") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Otto";
		_result.child = "Klaus";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: getChildren
	// rule: rule1:  -> Father("Jens","Max");
	private static ResourceIterator<FatherRel> getChildren_0 (final DerivationController _derivation ,  final String father ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Jens") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Jens";
		_result.child = "Max";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father("Jens","Xiomara");
	private static ResourceIterator<FatherRel> getChildren_1 (final DerivationController _derivation ,  final String father ) {
		
		_derivation.log("Father.rule2", DerivationController.RULE, _annotations_rule2);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Jens") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Jens";
		_result.child = "Xiomara";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father("Klaus","Jens");
	private static ResourceIterator<FatherRel> getChildren_2 (final DerivationController _derivation ,  final String father ) {
		
		_derivation.log("Father.rule3", DerivationController.RULE, _annotations_rule3);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Klaus") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Klaus";
		_result.child = "Jens";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father("Otto","Klaus");
	private static ResourceIterator<FatherRel> getChildren_3 (final DerivationController _derivation ,  final String father ) {
		
		_derivation.log("Father.rule4", DerivationController.RULE, _annotations_rule4);
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Otto") ) {
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Otto";
		_result.child = "Klaus";
		
		return new SingletonIterator<FatherRel>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: getFatherAndChild
	// rule: rule1:  -> Father("Jens","Max");
	private static ResourceIterator<FatherRel> getFatherAndChild_0 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule1", DerivationController.RULE, _annotations_rule1);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Jens";
		_result.child = "Max";
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	// rule: rule2:  -> Father("Jens","Xiomara");
	private static ResourceIterator<FatherRel> getFatherAndChild_1 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule2", DerivationController.RULE, _annotations_rule2);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Jens";
		_result.child = "Xiomara";
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	// rule: rule3:  -> Father("Klaus","Jens");
	private static ResourceIterator<FatherRel> getFatherAndChild_2 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule3", DerivationController.RULE, _annotations_rule3);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Klaus";
		_result.child = "Jens";
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	// rule: rule4:  -> Father("Otto","Klaus");
	private static ResourceIterator<FatherRel> getFatherAndChild_3 (final DerivationController _derivation  ) {
		
		_derivation.log("Father.rule4", DerivationController.RULE, _annotations_rule4);
		
			
		
		
		
		// create new instance of relationship
		FatherRel _result = new FatherRel();
		
		// set values
		_result.father = "Otto";
		_result.child = "Klaus";
		
		return new SingletonIterator<FatherRel>(_result);
		

		
	}
	
	
}

