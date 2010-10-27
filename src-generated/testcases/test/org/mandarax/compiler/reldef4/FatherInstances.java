package test.org.mandarax.compiler.reldef4;

import org.mandarax.rt.*;
import com.google.common.base.Predicate;

/**
 * Interface for queries for relationship <strong>Father</strong>.
 * @version Oct 28, 2010 11:58:22 AM 
 */
public class FatherInstances {
	// object references
	

	// interface generated for queries	
	 
	public ResultSet<Father> getFather (  String child  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<Father>(getFather ( _derivation ,  child ),_derivation);
	} 
	 
	public ResultSet<Father> isFather (  String father ,  String child  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<Father>(isFather ( _derivation ,  father ,  child ),_derivation);
	} 
	 
	public ResultSet<Father> getChildren (  String father  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<Father>(getChildren ( _derivation ,  father ),_derivation);
	} 
	 
	public ResultSet<Father> getFatherAndChild (  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<Father>(getFatherAndChild ( _derivation  ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<Father> getFather ( final DerivationController _derivation ,  final String child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<Father>(4) {
			
			public ResourceIterator<Father> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			return getFather_0(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 1: {
                			return getFather_1(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 2: {
                			return getFather_2(_derivation.pop(_derivationlevel) ,  child );
                		}
				
                		case 3: {
                			return getFather_3(_derivation.pop(_derivationlevel) ,  child );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<Father> isFather ( final DerivationController _derivation ,  final String father ,  final String child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<Father>(4) {
			
			public ResourceIterator<Father> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			return isFather_0(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 1: {
                			return isFather_1(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 2: {
                			return isFather_2(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
                		case 3: {
                			return isFather_3(_derivation.pop(_derivationlevel) ,  father ,  child );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<Father> getChildren ( final DerivationController _derivation ,  final String father  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<Father>(4) {
			
			public ResourceIterator<Father> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			return getChildren_0(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 1: {
                			return getChildren_1(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 2: {
                			return getChildren_2(_derivation.pop(_derivationlevel) ,  father );
                		}
				
                		case 3: {
                			return getChildren_3(_derivation.pop(_derivationlevel) ,  father );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<Father> getFatherAndChild ( final DerivationController _derivation   ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<Father>(4) {
			
			public ResourceIterator<Father> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			return getFatherAndChild_0(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 1: {
                			return getFatherAndChild_1(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 2: {
                			return getFatherAndChild_2(_derivation.pop(_derivationlevel)  );
                		}
				
                		case 3: {
                			return getFatherAndChild_3(_derivation.pop(_derivationlevel)  );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	
	// query: getFather
	// rule: rule1:  -> Father("Jens","Max");
	private static ResourceIterator<Father> getFather_0 (final DerivationController _derivation ,  final String child ) {
		
		
		
		_derivation.log("Father.rule1", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,"Max") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Jens";
		_result.child = "Max";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father("Jens","Xiomara");
	private static ResourceIterator<Father> getFather_1 (final DerivationController _derivation ,  final String child ) {
		
		
		
		_derivation.log("Father.rule2", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,"Xiomara") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Jens";
		_result.child = "Xiomara";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father("Klaus","Jens");
	private static ResourceIterator<Father> getFather_2 (final DerivationController _derivation ,  final String child ) {
		
		
		
		_derivation.log("Father.rule3", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,"Jens") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Klaus";
		_result.child = "Jens";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father("Otto","Klaus");
	private static ResourceIterator<Father> getFather_3 (final DerivationController _derivation ,  final String child ) {
		
		
		
		_derivation.log("Father.rule4", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(child,"Klaus") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Otto";
		_result.child = "Klaus";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: isFather
	// rule: rule1:  -> Father("Jens","Max");
	private static ResourceIterator<Father> isFather_0 (final DerivationController _derivation ,  final String father ,  final String child ) {
		
		
		
		_derivation.log("Father.rule1", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Jens") && Equals.compare(child,"Max") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Jens";
		_result.child = "Max";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father("Jens","Xiomara");
	private static ResourceIterator<Father> isFather_1 (final DerivationController _derivation ,  final String father ,  final String child ) {
		
		
		
		_derivation.log("Father.rule2", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Jens") && Equals.compare(child,"Xiomara") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Jens";
		_result.child = "Xiomara";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father("Klaus","Jens");
	private static ResourceIterator<Father> isFather_2 (final DerivationController _derivation ,  final String father ,  final String child ) {
		
		
		
		_derivation.log("Father.rule3", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Klaus") && Equals.compare(child,"Jens") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Klaus";
		_result.child = "Jens";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father("Otto","Klaus");
	private static ResourceIterator<Father> isFather_3 (final DerivationController _derivation ,  final String father ,  final String child ) {
		
		
		
		_derivation.log("Father.rule4", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Otto") && Equals.compare(child,"Klaus") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Otto";
		_result.child = "Klaus";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: getChildren
	// rule: rule1:  -> Father("Jens","Max");
	private static ResourceIterator<Father> getChildren_0 (final DerivationController _derivation ,  final String father ) {
		
		
		
		_derivation.log("Father.rule1", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Jens") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Jens";
		_result.child = "Max";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule2:  -> Father("Jens","Xiomara");
	private static ResourceIterator<Father> getChildren_1 (final DerivationController _derivation ,  final String father ) {
		
		
		
		_derivation.log("Father.rule2", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Jens") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Jens";
		_result.child = "Xiomara";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule3:  -> Father("Klaus","Jens");
	private static ResourceIterator<Father> getChildren_2 (final DerivationController _derivation ,  final String father ) {
		
		
		
		_derivation.log("Father.rule3", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Klaus") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Klaus";
		_result.child = "Jens";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// rule: rule4:  -> Father("Otto","Klaus");
	private static ResourceIterator<Father> getChildren_3 (final DerivationController _derivation ,  final String father ) {
		
		
		
		_derivation.log("Father.rule4", DerivationController.RULE, null);
		
		
		
			
		
		 
		// check conditions	
		if (Equals.compare(father,"Otto") ) {
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Otto";
		_result.child = "Klaus";
		
		return new SingletonIterator<Father>(_result);
		}
			
		// fact does not match query	
		return EmptyIterator.DEFAULT;
		

		
	}
	// query: getFatherAndChild
	// rule: rule1:  -> Father("Jens","Max");
	private static ResourceIterator<Father> getFatherAndChild_0 (final DerivationController _derivation  ) {
		
		
		
		_derivation.log("Father.rule1", DerivationController.RULE, null);
		
		
		
			
		
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Jens";
		_result.child = "Max";
		
		return new SingletonIterator<Father>(_result);
		

		
	}
	// rule: rule2:  -> Father("Jens","Xiomara");
	private static ResourceIterator<Father> getFatherAndChild_1 (final DerivationController _derivation  ) {
		
		
		
		_derivation.log("Father.rule2", DerivationController.RULE, null);
		
		
		
			
		
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Jens";
		_result.child = "Xiomara";
		
		return new SingletonIterator<Father>(_result);
		

		
	}
	// rule: rule3:  -> Father("Klaus","Jens");
	private static ResourceIterator<Father> getFatherAndChild_2 (final DerivationController _derivation  ) {
		
		
		
		_derivation.log("Father.rule3", DerivationController.RULE, null);
		
		
		
			
		
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Klaus";
		_result.child = "Jens";
		
		return new SingletonIterator<Father>(_result);
		

		
	}
	// rule: rule4:  -> Father("Otto","Klaus");
	private static ResourceIterator<Father> getFatherAndChild_3 (final DerivationController _derivation  ) {
		
		
		
		_derivation.log("Father.rule4", DerivationController.RULE, null);
		
		
		
			
		
		
		
		// create new instance of relationship
		Father _result = new Father();
		
		// set values
		_result.father = "Otto";
		_result.child = "Klaus";
		
		return new SingletonIterator<Father>(_result);
		

		
	}
	
	
}

