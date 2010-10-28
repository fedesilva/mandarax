package test.org.mandarax.compiler.reldef6;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>FrequentCustomer</strong>.
 * @version Oct 28, 2010 4:07:09 PM 
 */
public class FrequentCustomerInstances {
	// object references
	 
	public static Discount goldDiscount = new Discount(20,true);
	 
	public static Discount silverDiscount = new Discount(10,true);
	 
	public static Discount specialDiscount = new Discount(5,false);
	
	
	// fields representing annotations
	
	// rule: rule1: (c.getTransactionCount())>5 -> FrequentCustomer(c);
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();
	
	// rule: rule2: (c.getTransactionCount())>3 & (c.getTurnover())>500 -> FrequentCustomer(c);
	private final static java.util.Properties _annotations_rule2 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  rule1: (c.getTransactionCount())>5 -> FrequentCustomer(c);
		
		// rule annotations for rule  rule1: (c.getTransactionCount())>5 -> FrequentCustomer(c);
		
		
	
		// relationship annotations for rule  rule2: (c.getTransactionCount())>3 & (c.getTurnover())>500 -> FrequentCustomer(c);
		
		// rule annotations for rule  rule2: (c.getTransactionCount())>3 & (c.getTurnover())>500 -> FrequentCustomer(c);
		
		
	}
		
	

	// interface generated for queries	
	 
	public ResultSet<FrequentCustomer> isFrequentCustomer (  Customer customer  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<FrequentCustomer>(isFrequentCustomer ( _derivation ,  customer ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<FrequentCustomer> isFrequentCustomer ( final DerivationController _derivation ,  final Customer customer  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<FrequentCustomer>(2) {
			
			public ResourceIterator<FrequentCustomer> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			return isFrequentCustomer_0(_derivation.pop(_derivationlevel) ,  customer );
                		}
				
                		case 1: {
                			return isFrequentCustomer_1(_derivation.pop(_derivationlevel) ,  customer );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: isFrequentCustomer
	// rule: rule1: (c.getTransactionCount())>5 -> FrequentCustomer(c);
	private static ResourceIterator<FrequentCustomer> isFrequentCustomer_0 (final DerivationController _derivation ,  final Customer customer ) {
		

		_derivation.log("FrequentCustomer.rule1", DerivationController.RULE, _annotations_rule1);
		
		
		
			
	
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite (c.getTransactionCount())>5
		 
					if (!((_bindings.c.getTransactionCount())>5)) {return EmptyIterator.DEFAULT;} 
		
		
		// rule head
		return new SingletonIterator(new FrequentCustomer(_bindings.c));
        
		
		
		
	

		
	}
	// rule: rule2: (c.getTransactionCount())>3 & (c.getTurnover())>500 -> FrequentCustomer(c);
	private static ResourceIterator<FrequentCustomer> isFrequentCustomer_1 (final DerivationController _derivation ,  final Customer customer ) {
		

		_derivation.log("FrequentCustomer.rule2", DerivationController.RULE, _annotations_rule2);
		
		
		
			
	
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite (c.getTransactionCount())>3
		 
					if (!((_bindings.c.getTransactionCount())>3)) {return EmptyIterator.DEFAULT;} 
		 
		// apply prerequisite (c.getTurnover())>500
		 
					if (!((_bindings.c.getTurnover())>500)) {return EmptyIterator.DEFAULT;} 
		
		
		// rule head
		return new SingletonIterator(new FrequentCustomer(_bindings.c));
        
		
		
		
	

		
	}
	
	
}

