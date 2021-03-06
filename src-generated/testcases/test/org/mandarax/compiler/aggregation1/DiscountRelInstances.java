package test.org.mandarax.compiler.aggregation1;

import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>Discount</strong>. Generated
 * by org.mandarax.compiler.impl.DefaultCompiler.
 * 
 * @version Jan 16, 2011 9:27:45 PM
 */
public class DiscountRelInstances {
	// object references

	// fields representing annotations

	// rule: rule1: (max d in Transaction(c,d))>1000 -> Discount(c,30);
	private final static java.util.Properties _annotations_rule1 = new java.util.Properties();

	// rule: rule2: (avg d in Transaction(c,d))>50 -> Discount(c,20);
	private final static java.util.Properties _annotations_rule2 = new java.util.Properties();

	// rule: rule3: (sum d in Transaction(c,d))>30 -> Discount(c,10);
	private final static java.util.Properties _annotations_rule3 = new java.util.Properties();

	// rule: rule4: 2<(count d in Transaction(c,d)) -> Discount(c,5);
	private final static java.util.Properties _annotations_rule4 = new java.util.Properties();

	// rule: rule5: 10<(min d in Transaction(c,d)) -> Discount(c,3);
	private final static java.util.Properties _annotations_rule5 = new java.util.Properties();

	// initialise annotations
	static {
		// relationship annotations for rule rule1: (max d in
		// Transaction(c,d))>1000 -> Discount(c,30);

		// rule annotations for rule rule1: (max d in Transaction(c,d))>1000 ->
		// Discount(c,30);

		// relationship annotations for rule rule2: (avg d in
		// Transaction(c,d))>50 -> Discount(c,20);

		// rule annotations for rule rule2: (avg d in Transaction(c,d))>50 ->
		// Discount(c,20);

		// relationship annotations for rule rule3: (sum d in
		// Transaction(c,d))>30 -> Discount(c,10);

		// rule annotations for rule rule3: (sum d in Transaction(c,d))>30 ->
		// Discount(c,10);

		// relationship annotations for rule rule4: 2<(count d in
		// Transaction(c,d)) -> Discount(c,5);

		// rule annotations for rule rule4: 2<(count d in Transaction(c,d)) ->
		// Discount(c,5);

		// relationship annotations for rule rule5: 10<(min d in
		// Transaction(c,d)) -> Discount(c,3);

		// rule annotations for rule rule5: 10<(min d in Transaction(c,d)) ->
		// Discount(c,3);

	}

	// interface generated for queries

	public static ResultSet<DiscountRel> getDiscount(String customer) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<DiscountRel>(getDiscount(_derivation, customer), _derivation);
	}

	// implementations - these methods are referenced by code generated from
	// other rules in this package
	// and therefore kept static

	static ResourceIterator<DiscountRel> getDiscount(final DerivationController _derivation, final String customer) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<DiscountRel>(5) {

			public ResourceIterator<DiscountRel> getNextIterator(int pos) {
				switch (pos) {

				case 0: {
					// invoke rule1: (max d in Transaction(c,d))>1000 ->
					// Discount(c,30);
					return getDiscount_0(_derivation.pop(_derivationlevel), customer);
				}

				case 1: {
					// invoke rule2: (avg d in Transaction(c,d))>50 ->
					// Discount(c,20);
					return getDiscount_1(_derivation.pop(_derivationlevel), customer);
				}

				case 2: {
					// invoke rule3: (sum d in Transaction(c,d))>30 ->
					// Discount(c,10);
					return getDiscount_2(_derivation.pop(_derivationlevel), customer);
				}

				case 3: {
					// invoke rule4: 2<(count d in Transaction(c,d)) ->
					// Discount(c,5);
					return getDiscount_3(_derivation.pop(_derivationlevel), customer);
				}

				case 4: {
					// invoke rule5: 10<(min d in Transaction(c,d)) ->
					// Discount(c,3);
					return getDiscount_4(_derivation.pop(_derivationlevel), customer);
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
	// rule: rule1: (max d in Transaction(c,d))>1000 -> Discount(c,30);
	private static ResourceIterator<DiscountRel> getDiscount_0(final DerivationController _derivation, final String customer) {

		_derivation.log("test.org.mandarax.compiler.aggregation1.Discount.rule1", DerivationController.RULE, _annotations_rule1);

		// utility class used to keep track of variables bindings
		// rule: rule1: (max d in Transaction(c,d))>1000 -> Discount(c,30);
		// prereqs: [(_max_0(TransactionRelInstances.getTransactions(c)))>1000]
		class _Bindings {
			private java.lang.String c = customer;
			private int d = 0;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// check conditions
		boolean _checkfailed = false;

		if (_checkfailed) {
			return EmptyIterator.DEFAULT;
		}

		// apply prerequisite
		// (_max_0(TransactionRelInstances.getTransactions(c)))>1000

		// case 4
		if (!((_max_0(TransactionRelInstances.getTransactions(_bindings.c))) > 1000)) {
			return EmptyIterator.DEFAULT;
		}

		// rule head

		return new SingletonIterator(new DiscountRel(_bindings.c, 30));

	}

	// rule: rule2: (avg d in Transaction(c,d))>50 -> Discount(c,20);
	private static ResourceIterator<DiscountRel> getDiscount_1(final DerivationController _derivation, final String customer) {

		_derivation.log("test.org.mandarax.compiler.aggregation1.Discount.rule2", DerivationController.RULE, _annotations_rule2);

		// utility class used to keep track of variables bindings
		// rule: rule2: (avg d in Transaction(c,d))>50 -> Discount(c,20);
		// prereqs: [(_avg_0(TransactionRelInstances.getTransactions(c)))>50]
		class _Bindings {
			private java.lang.String c = customer;
			private int d = 0;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// check conditions
		boolean _checkfailed = false;

		if (_checkfailed) {
			return EmptyIterator.DEFAULT;
		}

		// apply prerequisite
		// (_avg_0(TransactionRelInstances.getTransactions(c)))>50

		// case 4
		if (!((_avg_0(TransactionRelInstances.getTransactions(_bindings.c))) > 50)) {
			return EmptyIterator.DEFAULT;
		}

		// rule head

		return new SingletonIterator(new DiscountRel(_bindings.c, 20));

	}

	// rule: rule3: (sum d in Transaction(c,d))>30 -> Discount(c,10);
	private static ResourceIterator<DiscountRel> getDiscount_2(final DerivationController _derivation, final String customer) {

		_derivation.log("test.org.mandarax.compiler.aggregation1.Discount.rule3", DerivationController.RULE, _annotations_rule3);

		// utility class used to keep track of variables bindings
		// rule: rule3: (sum d in Transaction(c,d))>30 -> Discount(c,10);
		// prereqs: [(_sum_0(TransactionRelInstances.getTransactions(c)))>30]
		class _Bindings {
			private java.lang.String c = customer;
			private int d = 0;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// check conditions
		boolean _checkfailed = false;

		if (_checkfailed) {
			return EmptyIterator.DEFAULT;
		}

		// apply prerequisite
		// (_sum_0(TransactionRelInstances.getTransactions(c)))>30

		// case 4
		if (!((_sum_0(TransactionRelInstances.getTransactions(_bindings.c))) > 30)) {
			return EmptyIterator.DEFAULT;
		}

		// rule head

		return new SingletonIterator(new DiscountRel(_bindings.c, 10));

	}

	// rule: rule4: 2<(count d in Transaction(c,d)) -> Discount(c,5);
	private static ResourceIterator<DiscountRel> getDiscount_3(final DerivationController _derivation, final String customer) {

		_derivation.log("test.org.mandarax.compiler.aggregation1.Discount.rule4", DerivationController.RULE, _annotations_rule4);

		// utility class used to keep track of variables bindings
		// rule: rule4: 2<(count d in Transaction(c,d)) -> Discount(c,5);
		// prereqs: [2<(_count_0(TransactionRelInstances.getTransactions(c)))]
		class _Bindings {
			private java.lang.String c = customer;
			private int d = 0;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// check conditions
		boolean _checkfailed = false;

		if (_checkfailed) {
			return EmptyIterator.DEFAULT;
		}

		// apply prerequisite
		// 2<(_count_0(TransactionRelInstances.getTransactions(c)))

		// case 4
		if (!(2 < (_count_0(TransactionRelInstances.getTransactions(_bindings.c))))) {
			return EmptyIterator.DEFAULT;
		}

		// rule head

		return new SingletonIterator(new DiscountRel(_bindings.c, 5));

	}

	// rule: rule5: 10<(min d in Transaction(c,d)) -> Discount(c,3);
	private static ResourceIterator<DiscountRel> getDiscount_4(final DerivationController _derivation, final String customer) {

		_derivation.log("test.org.mandarax.compiler.aggregation1.Discount.rule5", DerivationController.RULE, _annotations_rule5);

		// utility class used to keep track of variables bindings
		// rule: rule5: 10<(min d in Transaction(c,d)) -> Discount(c,3);
		// prereqs: [10<(_min_0(TransactionRelInstances.getTransactions(c)))]
		class _Bindings {
			private java.lang.String c = customer;
			private int d = 0;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// check conditions
		boolean _checkfailed = false;

		if (_checkfailed) {
			return EmptyIterator.DEFAULT;
		}

		// apply prerequisite
		// 10<(_min_0(TransactionRelInstances.getTransactions(c)))

		// case 4
		if (!(10 < (_min_0(TransactionRelInstances.getTransactions(_bindings.c))))) {
			return EmptyIterator.DEFAULT;
		}

		// rule head

		return new SingletonIterator(new DiscountRel(_bindings.c, 3));

	}

	// methods representing aggregation functions

	// agg _max_0(TransactionRelInstances.getTransactions(c)) type is max
	static int _max_0(ResourceIterator<TransactionRel> _rel) {
		int _v = 0;
		boolean f = true;
		while (_rel.hasNext()) {
			TransactionRel _c = _rel.next();
			if (f) {
				_v = _c.value;

				f = false;
			} else {
				if (_c.value > _v) {
					_v = _c.value;
				}

			}
		}
		_rel.close();
		return _v;
	}

	// agg _avg_0(TransactionRelInstances.getTransactions(c)) type is avg
	static double _avg_0(ResourceIterator<TransactionRel> _rel) {
		double _v = 0;
		int _c = 0;

		while (_rel.hasNext()) {
			_v = _v + _rel.next().value;
			_c = _c + 1;
		}
		_rel.close();
		return _c == 0 ? 0 : (_v / _c);
	}

	// agg _sum_0(TransactionRelInstances.getTransactions(c)) type is sum
	static int _sum_0(ResourceIterator<TransactionRel> _rel) {
		int _v = 0;

		while (_rel.hasNext()) {
			_v = _v + _rel.next().value;
		}
		_rel.close();
		return _v;
	}

	// agg _count_0(TransactionRelInstances.getTransactions(c)) type is count
	static int _count_0(ResourceIterator<TransactionRel> _rel) {
		int _v = 0;

		while (_rel.hasNext()) {
			_rel.next();
			_v = _v + 1;
		}
		_rel.close();
		return _v;
	}

	// agg _min_0(TransactionRelInstances.getTransactions(c)) type is min
	static int _min_0(ResourceIterator<TransactionRel> _rel) {
		int _v = 0;
		boolean f = true;
		while (_rel.hasNext()) {
			TransactionRel _c = _rel.next();
			if (f) {
				_v = _c.value;

				f = false;
			} else {
				if (_c.value < _v) {
					_v = _c.value;
				}

			}
		}
		_rel.close();
		return _v;
	}

}
