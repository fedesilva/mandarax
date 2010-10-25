package test.org.mandarax.compiler.reldef6;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>Discount</strong>.
 * @version Oct 25, 2010 9:56:39 PM 
 */
public class DiscountInstances {

	// interface generated for queries
	
	 
	public ResultSet<Discount> getDiscount (  Customer customer  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<Discount>(getDiscount ( _derivation ,  customer ),_derivation);
	} 
	 
	public ResultSet<Discount> qualifiesForDiscount (  Customer customer ,  Discount discount  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<Discount>(qualifiesForDiscount ( _derivation ,  customer ,  discount ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<Discount> getDiscount ( final DerivationController _derivation ,  final Customer customer  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<Discount>(3) {
			
			public ResourceIterator<Discount> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			return getDiscount_0(_derivation.pop(_derivationlevel) ,  customer );
                		}
				
                		case 1: {
                			return getDiscount_1(_derivation.pop(_derivationlevel) ,  customer );
                		}
				
                		case 2: {
                			return getDiscount_2(_derivation.pop(_derivationlevel) ,  customer );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<Discount> qualifiesForDiscount ( final DerivationController _derivation ,  final Customer customer ,  final Discount discount  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<Discount>(3) {
			
			public ResourceIterator<Discount> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			return qualifiesForDiscount_0(_derivation.pop(_derivationlevel) ,  customer ,  discount );
                		}
				
                		case 1: {
                			return qualifiesForDiscount_1(_derivation.pop(_derivationlevel) ,  customer ,  discount );
                		}
				
                		case 2: {
                			return qualifiesForDiscount_2(_derivation.pop(_derivationlevel) ,  customer ,  discount );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	
	// query: getDiscount
	// rule: rule1: (c.getTurnover())>1000 -> Discount(c,goldDiscount);
	private static ResourceIterator<Discount> getDiscount_0 (final DerivationController _derivation ,  final Customer customer ) {
		
			
	
		_derivation.log("rule1", DerivationController.RULE, DerivationController.NIL);
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.reldef6.Discount goldDiscount = goldDiscount; // term goldDiscount
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite (c.getTurnover())>1000
		 
					Predicate<Object> filter = new Predicate<Object> {
						public boolean apply(Object _o) {
								return (c.getTurnover())>1000;
							}
					}
					final ResourceIterator iterator1 =  new FilteredIterator{iterator0,filter};
		
		
		// rule head
		
		
		
		
	

		
	}
	// rule: rule2: FrequentCustomer(c) -> Discount(c,goldDiscount);
	private static ResourceIterator<Discount> getDiscount_1 (final DerivationController _derivation ,  final Customer customer ) {
		
			
	
		_derivation.log("rule2", DerivationController.RULE, DerivationController.NIL);
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.reldef6.Discount goldDiscount = goldDiscount; // term goldDiscount
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite FrequentCustomer(c)
		final ResourceIterator<FrequentCustomer> iterator1 = FrequentCustomerInstances.isFrequentCustomer(_derivation.push(),_bindings.c);
		
		
		
		
		// rule head
		return new NestedIterator<FrequentCustomer, Discount>(iterator1) {
                	public ResourceIterator<Discount> getNextIterator(FrequentCustomer _object) {
						// bind parameters from FrequentCustomer(c)
						
                    				return new SingletonIterator(new Discount(_bindings.c,_bindings.goldDiscount));
                	}
        	};
        
		
		
		
	

		
	}
	// rule: rule3: (c.getPaymentMethod())=="CompanyVisa" -> Discount(c,silverDiscount);
	private static ResourceIterator<Discount> getDiscount_2 (final DerivationController _derivation ,  final Customer customer ) {
		
			
	
		_derivation.log("rule3", DerivationController.RULE, DerivationController.NIL);
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.reldef6.Discount silverDiscount = silverDiscount; // term silverDiscount
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite (c.getPaymentMethod())=="CompanyVisa"
		 
					Predicate<Object> filter = new Predicate<Object> {
						public boolean apply(Object _o) {
								return (c.getPaymentMethod())=="CompanyVisa";
							}
					}
					final ResourceIterator iterator1 =  new FilteredIterator{iterator0,filter};
		
		
		// rule head
		
		
		
		
	

		
	}
	// query: qualifiesForDiscount
	// rule: rule1: (c.getTurnover())>1000 -> Discount(c,goldDiscount);
	private static ResourceIterator<Discount> qualifiesForDiscount_0 (final DerivationController _derivation ,  final Customer customer ,  final Discount discount ) {
		
			
	
		_derivation.log("rule1", DerivationController.RULE, DerivationController.NIL);
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.reldef6.Discount goldDiscount = goldDiscount; // term goldDiscount
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite (c.getTurnover())>1000
		 
					Predicate<Object> filter = new Predicate<Object> {
						public boolean apply(Object _o) {
								return (c.getTurnover())>1000;
							}
					}
					final ResourceIterator iterator1 =  new FilteredIterator{iterator0,filter};
		
		
		// rule head
		
		
		
		
	

		
	}
	// rule: rule2: FrequentCustomer(c) -> Discount(c,goldDiscount);
	private static ResourceIterator<Discount> qualifiesForDiscount_1 (final DerivationController _derivation ,  final Customer customer ,  final Discount discount ) {
		
			
	
		_derivation.log("rule2", DerivationController.RULE, DerivationController.NIL);
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.reldef6.Discount goldDiscount = goldDiscount; // term goldDiscount
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite FrequentCustomer(c)
		final ResourceIterator<FrequentCustomer> iterator1 = FrequentCustomerInstances.isFrequentCustomer(_derivation.push(),_bindings.c);
		
		
		
		
		// rule head
		return new NestedIterator<FrequentCustomer, Discount>(iterator1) {
                	public ResourceIterator<Discount> getNextIterator(FrequentCustomer _object) {
						// bind parameters from FrequentCustomer(c)
						
                    				return new SingletonIterator(new Discount(_bindings.c,_bindings.goldDiscount));
                	}
        	};
        
		
		
		
	

		
	}
	// rule: rule3: (c.getPaymentMethod())=="CompanyVisa" -> Discount(c,silverDiscount);
	private static ResourceIterator<Discount> qualifiesForDiscount_2 (final DerivationController _derivation ,  final Customer customer ,  final Discount discount ) {
		
			
	
		_derivation.log("rule3", DerivationController.RULE, DerivationController.NIL);
		
		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.reldef6.Discount silverDiscount = silverDiscount; // term silverDiscount
		}
		final _Bindings _bindings = new _Bindings();
		

		 
		// apply prerequisite (c.getPaymentMethod())=="CompanyVisa"
		 
					Predicate<Object> filter = new Predicate<Object> {
						public boolean apply(Object _o) {
								return (c.getPaymentMethod())=="CompanyVisa";
							}
					}
					final ResourceIterator iterator1 =  new FilteredIterator{iterator0,filter};
		
		
		// rule head
		
		
		
		
	

		
	}
	
	
}

