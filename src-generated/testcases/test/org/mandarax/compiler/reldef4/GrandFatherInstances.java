package test.org.mandarax.compiler.reldef4;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>GrandFather</strong>.
 * 
 * @version Oct 8, 2010 4:55:23 PM
 */
public class GrandFatherInstances {

	// interface generated for queries

	public ResultSet<GrandFather> getAll() {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<GrandFather>(getAll(_derivation), _derivation);
	}

	// implementations - these methods are referenced by code generated from
	// other rules in this package
	// and therefore kept static

	static ResourceIterator<GrandFather> getAll(final DerivationController _derivation) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<GrandFather>(1) {

			public ResourceIterator<GrandFather> getNextIterator(int pos) {
				switch (pos) {

				// code generated for rule: PUBLIC getAll()
				case 0: {
					return getAll_0(_derivation.pop(_derivationlevel));
				}

				default:
					return EmptyIterator.DEFAULT;
				}
			}
		};
	}

	// private methods - each method represents the invocation of a single rule
	// for a certain query

	// query: getAll
	// rule: rule1: Father(x,y) & Father(y,z) -> GrandFather(x,z);
	private static ResourceIterator<GrandFather> getAll_0(final DerivationController _derivation) {

		_derivation.log("rule1", DerivationController.RULE, DerivationController.NIL);

		// utility class used to keep track of variables bindings
		class _Bindings {
			private java.lang.String x = null;
			private java.lang.String z = null;
			private java.lang.String y = null;
		}
		final _Bindings _bindings = new _Bindings();

		// apply prerequisite Father(x,y)
		final ResourceIterator<Father> iterator1 = FatherInstances.getFatherAndChild(_derivation.push());

		// apply prerequisite Father(y,z)
		final ResourceIterator<Father> iterator2 = new NestedIterator<Father, Father>(iterator1) {
			public ResourceIterator<Father> getNextIterator(Father _object) {
				// bind parameters from Father(x,y)
				_bindings.x = _object.father;
				_bindings.y = _object.child;

				return FatherInstances.getChildren(_derivation.push(), _bindings.y);
			}
		};

		// rule head
		return new NestedIterator<Father, GrandFather>(iterator2) {
			public ResourceIterator<GrandFather> getNextIterator(Father _object) {
				// bind parameters from Father(y,z)
				_bindings.z = _object.child;

				return new SingletonIterator(new GrandFather(_bindings.x, _bindings.z));
			}
		};

	}

}
