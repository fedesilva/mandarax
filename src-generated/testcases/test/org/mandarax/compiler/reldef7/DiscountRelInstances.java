package test.org.mandarax.compiler.reldef7;

import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;
import com.google.common.base.Predicate;

/**
 * Interface for queries for relationship <strong>DiscountRel</strong>.
 * 
 * @version Oct 28, 2010 12:17:04 PM
 */
public class DiscountRelInstances {
	// object references

	public static Discount goldDiscount = new Discount(20, true);

	public static Discount silverDiscount = new Discount(10, true);

	public static Discount specialDiscount = new Discount(5, false);

	// interface generated for queries

	public ResultSet<DiscountRel> getDiscount(Customer customer) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<DiscountRel>(getDiscount(_derivation, customer), _derivation);
	}

	public ResultSet<DiscountRel> qualifiesForDiscount(Customer customer, Discount discount) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<DiscountRel>(qualifiesForDiscount(_derivation, customer, discount), _derivation);
	}

	// implementations - these methods are referenced by code generated from
	// other rules in this package
	// and therefore kept static

	static ResourceIterator<DiscountRel> getDiscount(final DerivationController _derivation, final Customer customer) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<DiscountRel>(3) {

			public ResourceIterator<DiscountRel> getNextIterator(int pos) {
				switch (pos) {

				case 0: {
					return getDiscount_0(_derivation.pop(_derivationlevel), customer);
				}

				case 1: {
					return getDiscount_1(_derivation.pop(_derivationlevel), customer);
				}

				case 2: {
					return getDiscount_2(_derivation.pop(_derivationlevel), customer);
				}

				default:
					return EmptyIterator.DEFAULT;
				}
			}
		};
	}

	static ResourceIterator<DiscountRel> qualifiesForDiscount(final DerivationController _derivation, final Customer customer, final Discount discount) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<DiscountRel>(3) {

			public ResourceIterator<DiscountRel> getNextIterator(int pos) {
				switch (pos) {

				case 0: {
					return qualifiesForDiscount_0(_derivation.pop(_derivationlevel), customer, discount);
				}

				case 1: {
					return qualifiesForDiscount_1(_derivation.pop(_derivationlevel), customer, discount);
				}

				case 2: {
					return qualifiesForDiscount_2(_derivation.pop(_derivationlevel), customer, discount);
				}

				default:
					return EmptyIterator.DEFAULT;
				}
			}
		};
	}

	// private methods - each method represents the invocation of a single rule
	// for a certain query

	// query: getDiscount
	// rule: rule1: (c.getTurnover())>1000 -> DiscountRel(c,goldDiscount);
	private static ResourceIterator<DiscountRel> getDiscount_0(final DerivationController _derivation, final Customer customer) {

		java.util.Properties _annotations = new java.util.Properties();
		// relationship annotations
		_annotations.put("author", "jens");
		_annotations.put("lastupdated", "26/10/10");

		// rule annotations
		_annotations.put("lastupdated", "27/10/10");
		_annotations.put("name", "golden rule");

		_derivation.log("DiscountRel.rule1", DerivationController.RULE, _annotations);

		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.Discount goldDiscount = DiscountRelInstances.goldDiscount; // term
																											// goldDiscount
		}
		final _Bindings _bindings = new _Bindings();

		// apply prerequisite (c.getTurnover())>1000

		if (!((_bindings.c.getTurnover()) > 1000)) {
			return EmptyIterator.DEFAULT;
		}

		// rule head
		return new SingletonIterator(new DiscountRel(_bindings.c, _bindings.goldDiscount));

	}

	// rule: rule2: FrequentCustomer(c) -> DiscountRel(c,silverDiscount);
	private static ResourceIterator<DiscountRel> getDiscount_1(final DerivationController _derivation, final Customer customer) {

		java.util.Properties _annotations = new java.util.Properties();
		// relationship annotations
		_annotations.put("author", "jens");
		_annotations.put("lastupdated", "26/10/10");

		// rule annotations
		_annotations.put("name", "\\\"silver\\\" rule");

		_derivation.log("DiscountRel.rule2", DerivationController.RULE, _annotations);

		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.Discount silverDiscount = DiscountRelInstances.silverDiscount; // term
																												// silverDiscount
		}
		final _Bindings _bindings = new _Bindings();

		// apply prerequisite FrequentCustomer(c)
		final ResourceIterator<FrequentCustomer> iterator1 = FrequentCustomerInstances.isFrequentCustomer(_derivation.push(), _bindings.c);

		// rule head
		return new NestedIterator<FrequentCustomer, DiscountRel>(iterator1) {
			public ResourceIterator<DiscountRel> getNextIterator(FrequentCustomer _object) {
				// bind parameters from FrequentCustomer(c)

				return new SingletonIterator(new DiscountRel(_bindings.c, _bindings.silverDiscount));
			}
		};

	}

	// rule: rule3: (c.getPaymentMethod())=="CompanyVisa" ->
	// DiscountRel(c,specialDiscount);
	private static ResourceIterator<DiscountRel> getDiscount_2(final DerivationController _derivation, final Customer customer) {

		java.util.Properties _annotations = new java.util.Properties();
		// relationship annotations
		_annotations.put("author", "jens");
		_annotations.put("lastupdated", "26/10/10");

		// rule annotations
		_annotations.put("name", "special rule");

		_derivation.log("DiscountRel.rule3", DerivationController.RULE, _annotations);

		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.Discount specialDiscount = DiscountRelInstances.specialDiscount; // term
																												// specialDiscount
		}
		final _Bindings _bindings = new _Bindings();

		// apply prerequisite (c.getPaymentMethod())=="CompanyVisa"

		if (!(org.mandarax.rt.Equals.compare(_bindings.c.getPaymentMethod(), "CompanyVisa"))) {
			return EmptyIterator.DEFAULT;
		}

		// rule head
		return new SingletonIterator(new DiscountRel(_bindings.c, _bindings.specialDiscount));

	}

	// query: qualifiesForDiscount
	// rule: rule1: (c.getTurnover())>1000 -> DiscountRel(c,goldDiscount);
	private static ResourceIterator<DiscountRel> qualifiesForDiscount_0(final DerivationController _derivation, final Customer customer, final Discount discount) {

		java.util.Properties _annotations = new java.util.Properties();
		// relationship annotations
		_annotations.put("author", "jens");
		_annotations.put("lastupdated", "26/10/10");

		// rule annotations
		_annotations.put("lastupdated", "27/10/10");
		_annotations.put("name", "golden rule");

		_derivation.log("DiscountRel.rule1", DerivationController.RULE, _annotations);

		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.Discount goldDiscount = DiscountRelInstances.goldDiscount; // term
																											// goldDiscount
		}
		final _Bindings _bindings = new _Bindings();

		// apply prerequisite (c.getTurnover())>1000

		if (!((_bindings.c.getTurnover()) > 1000)) {
			return EmptyIterator.DEFAULT;
		}

		// rule head
		return new SingletonIterator(new DiscountRel(_bindings.c, _bindings.goldDiscount));

	}

	// rule: rule2: FrequentCustomer(c) -> DiscountRel(c,silverDiscount);
	private static ResourceIterator<DiscountRel> qualifiesForDiscount_1(final DerivationController _derivation, final Customer customer, final Discount discount) {

		java.util.Properties _annotations = new java.util.Properties();
		// relationship annotations
		_annotations.put("author", "jens");
		_annotations.put("lastupdated", "26/10/10");

		// rule annotations
		_annotations.put("name", "\\\"silver\\\" rule");

		_derivation.log("DiscountRel.rule2", DerivationController.RULE, _annotations);

		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.Discount silverDiscount = DiscountRelInstances.silverDiscount; // term
																												// silverDiscount
		}
		final _Bindings _bindings = new _Bindings();

		// apply prerequisite FrequentCustomer(c)
		final ResourceIterator<FrequentCustomer> iterator1 = FrequentCustomerInstances.isFrequentCustomer(_derivation.push(), _bindings.c);

		// rule head
		return new NestedIterator<FrequentCustomer, DiscountRel>(iterator1) {
			public ResourceIterator<DiscountRel> getNextIterator(FrequentCustomer _object) {
				// bind parameters from FrequentCustomer(c)

				return new SingletonIterator(new DiscountRel(_bindings.c, _bindings.silverDiscount));
			}
		};

	}

	// rule: rule3: (c.getPaymentMethod())=="CompanyVisa" ->
	// DiscountRel(c,specialDiscount);
	private static ResourceIterator<DiscountRel> qualifiesForDiscount_2(final DerivationController _derivation, final Customer customer, final Discount discount) {

		java.util.Properties _annotations = new java.util.Properties();
		// relationship annotations
		_annotations.put("author", "jens");
		_annotations.put("lastupdated", "26/10/10");

		// rule annotations
		_annotations.put("name", "special rule");

		_derivation.log("DiscountRel.rule3", DerivationController.RULE, _annotations);

		// utility class used to keep track of variables bindings
		class _Bindings {
			private test.org.mandarax.compiler.Customer c = customer; // term c
			private test.org.mandarax.compiler.Discount specialDiscount = DiscountRelInstances.specialDiscount; // term
																												// specialDiscount
		}
		final _Bindings _bindings = new _Bindings();

		// apply prerequisite (c.getPaymentMethod())=="CompanyVisa"

		if (!(org.mandarax.rt.Equals.compare(_bindings.c.getPaymentMethod(), "CompanyVisa"))) {
			return EmptyIterator.DEFAULT;
		}

		// rule head
		return new SingletonIterator(new DiscountRel(_bindings.c, _bindings.specialDiscount));

	}

}
