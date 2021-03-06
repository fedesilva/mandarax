package test.org.mandarax.compiler.aggregation2;

import java.util.*;

import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>Meeting</strong>. Generated by
 * org.mandarax.compiler.impl.DefaultCompiler.
 * 
 * @version Jan 16, 2011 9:27:45 PM
 */
public class MeetingRelInstances {
	// object references

	// fields representing annotations

	// rule: fact1: -> Meeting("NY",new GregorianCalendar(2000,1,2));
	private final static java.util.Properties _annotations_fact1 = new java.util.Properties();

	// rule: fact2: -> Meeting("NY",new GregorianCalendar(2002,2,3));
	private final static java.util.Properties _annotations_fact2 = new java.util.Properties();

	// rule: fact3: -> Meeting("NY",new GregorianCalendar(2001,3,4));
	private final static java.util.Properties _annotations_fact3 = new java.util.Properties();

	// initialise annotations
	static {
		// relationship annotations for rule fact1: -> Meeting("NY",new
		// GregorianCalendar(2000,1,2));

		// rule annotations for rule fact1: -> Meeting("NY",new
		// GregorianCalendar(2000,1,2));

		// relationship annotations for rule fact2: -> Meeting("NY",new
		// GregorianCalendar(2002,2,3));

		// rule annotations for rule fact2: -> Meeting("NY",new
		// GregorianCalendar(2002,2,3));

		// relationship annotations for rule fact3: -> Meeting("NY",new
		// GregorianCalendar(2001,3,4));

		// rule annotations for rule fact3: -> Meeting("NY",new
		// GregorianCalendar(2001,3,4));

	}

	// interface generated for queries

	public static ResultSet<MeetingRel> getAll() {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<MeetingRel>(getAll(_derivation), _derivation);
	}

	public static ResultSet<MeetingRel> getMeetings(String venue) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<MeetingRel>(getMeetings(_derivation, venue), _derivation);
	}

	// implementations - these methods are referenced by code generated from
	// other rules in this package
	// and therefore kept static

	static ResourceIterator<MeetingRel> getAll(final DerivationController _derivation) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<MeetingRel>(3) {

			public ResourceIterator<MeetingRel> getNextIterator(int pos) {
				switch (pos) {

				case 0: {
					// invoke fact1: -> Meeting("NY",new
					// GregorianCalendar(2000,1,2));
					return getAll_0(_derivation.pop(_derivationlevel));
				}

				case 1: {
					// invoke fact2: -> Meeting("NY",new
					// GregorianCalendar(2002,2,3));
					return getAll_1(_derivation.pop(_derivationlevel));
				}

				case 2: {
					// invoke fact3: -> Meeting("NY",new
					// GregorianCalendar(2001,3,4));
					return getAll_2(_derivation.pop(_derivationlevel));
				}

				default:
					return EmptyIterator.DEFAULT;
				}
			}
		};
	}

	static ResourceIterator<MeetingRel> getMeetings(final DerivationController _derivation, final String venue) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<MeetingRel>(3) {

			public ResourceIterator<MeetingRel> getNextIterator(int pos) {
				switch (pos) {

				case 0: {
					// invoke fact1: -> Meeting("NY",new
					// GregorianCalendar(2000,1,2));
					return getMeetings_0(_derivation.pop(_derivationlevel), venue);
				}

				case 1: {
					// invoke fact2: -> Meeting("NY",new
					// GregorianCalendar(2002,2,3));
					return getMeetings_1(_derivation.pop(_derivationlevel), venue);
				}

				case 2: {
					// invoke fact3: -> Meeting("NY",new
					// GregorianCalendar(2001,3,4));
					return getMeetings_2(_derivation.pop(_derivationlevel), venue);
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
	// rule: fact1: -> Meeting("NY",new GregorianCalendar(2000,1,2));
	private static ResourceIterator<MeetingRel> getAll_0(final DerivationController _derivation) {

		_derivation.log("test.org.mandarax.compiler.aggregation2.Meeting.fact1", DerivationController.RULE, _annotations_fact1);

		// utility class used to keep track of variables bindings
		// rule: fact1: -> Meeting("NY",new GregorianCalendar(2000,1,2));
		// prereqs: []
		class _Bindings {
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// rule head

		return new SingletonIterator(new MeetingRel("NY", new GregorianCalendar(2000, 1, 2)));

	}

	// rule: fact2: -> Meeting("NY",new GregorianCalendar(2002,2,3));
	private static ResourceIterator<MeetingRel> getAll_1(final DerivationController _derivation) {

		_derivation.log("test.org.mandarax.compiler.aggregation2.Meeting.fact2", DerivationController.RULE, _annotations_fact2);

		// utility class used to keep track of variables bindings
		// rule: fact2: -> Meeting("NY",new GregorianCalendar(2002,2,3));
		// prereqs: []
		class _Bindings {
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// rule head

		return new SingletonIterator(new MeetingRel("NY", new GregorianCalendar(2002, 2, 3)));

	}

	// rule: fact3: -> Meeting("NY",new GregorianCalendar(2001,3,4));
	private static ResourceIterator<MeetingRel> getAll_2(final DerivationController _derivation) {

		_derivation.log("test.org.mandarax.compiler.aggregation2.Meeting.fact3", DerivationController.RULE, _annotations_fact3);

		// utility class used to keep track of variables bindings
		// rule: fact3: -> Meeting("NY",new GregorianCalendar(2001,3,4));
		// prereqs: []
		class _Bindings {
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// rule head

		return new SingletonIterator(new MeetingRel("NY", new GregorianCalendar(2001, 3, 4)));

	}

	// query: getMeetings
	// rule: fact1: -> Meeting("NY",new GregorianCalendar(2000,1,2));
	private static ResourceIterator<MeetingRel> getMeetings_0(final DerivationController _derivation, final String venue) {

		_derivation.log("test.org.mandarax.compiler.aggregation2.Meeting.fact1", DerivationController.RULE, _annotations_fact1);

		// utility class used to keep track of variables bindings
		// rule: fact1: -> Meeting("NY",new GregorianCalendar(2000,1,2));
		// prereqs: []
		class _Bindings {
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// check conditions
		boolean _checkfailed = false;

		_checkfailed = _checkfailed || !Equals.compare(venue, "NY");

		if (_checkfailed) {
			return EmptyIterator.DEFAULT;
		}

		// rule head

		return new SingletonIterator(new MeetingRel("NY", new GregorianCalendar(2000, 1, 2)));

	}

	// rule: fact2: -> Meeting("NY",new GregorianCalendar(2002,2,3));
	private static ResourceIterator<MeetingRel> getMeetings_1(final DerivationController _derivation, final String venue) {

		_derivation.log("test.org.mandarax.compiler.aggregation2.Meeting.fact2", DerivationController.RULE, _annotations_fact2);

		// utility class used to keep track of variables bindings
		// rule: fact2: -> Meeting("NY",new GregorianCalendar(2002,2,3));
		// prereqs: []
		class _Bindings {
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// check conditions
		boolean _checkfailed = false;

		_checkfailed = _checkfailed || !Equals.compare(venue, "NY");

		if (_checkfailed) {
			return EmptyIterator.DEFAULT;
		}

		// rule head

		return new SingletonIterator(new MeetingRel("NY", new GregorianCalendar(2002, 2, 3)));

	}

	// rule: fact3: -> Meeting("NY",new GregorianCalendar(2001,3,4));
	private static ResourceIterator<MeetingRel> getMeetings_2(final DerivationController _derivation, final String venue) {

		_derivation.log("test.org.mandarax.compiler.aggregation2.Meeting.fact3", DerivationController.RULE, _annotations_fact3);

		// utility class used to keep track of variables bindings
		// rule: fact3: -> Meeting("NY",new GregorianCalendar(2001,3,4));
		// prereqs: []
		class _Bindings {
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;

		// check conditions
		boolean _checkfailed = false;

		_checkfailed = _checkfailed || !Equals.compare(venue, "NY");

		if (_checkfailed) {
			return EmptyIterator.DEFAULT;
		}

		// rule head

		return new SingletonIterator(new MeetingRel("NY", new GregorianCalendar(2001, 3, 4)));

	}

	// methods representing aggregation functions

}
