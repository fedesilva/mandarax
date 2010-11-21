package org.mandarax.examples.userv.rules.generated;
 
import org.mandarax.examples.userv.domain.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>HasTrainingCertification</strong>.
 * Generated by org.mandarax.compiler.impl.DefaultCompiler.
 * @version Nov 22, 2010 11:30:40 AM 
 */
public class HasTrainingCertificationRelInstances {
	// object references
	
	
	// fields representing annotations
	
	// rule: DE_DAC07: _driver.getHasDriversTrainingFromSchool() -> HasTrainingCertification(_driver);
	private final static java.util.Properties _annotations_DE_DAC07 = new java.util.Properties();
	
	// rule: DE_DAC08: _driver.getHasDriversTrainingFromLicensedDriverTrainingCompany() -> HasTrainingCertification(_driver);
	private final static java.util.Properties _annotations_DE_DAC08 = new java.util.Properties();
	
	// rule: DE_DAC09: _driver.getHasTakenASeniorCitizenDriversRefresherCourse() -> HasTrainingCertification(_driver);
	private final static java.util.Properties _annotations_DE_DAC09 = new java.util.Properties();
	
	
	// initialise annotations
	static {
		// relationship annotations for rule  DE_DAC07: _driver.getHasDriversTrainingFromSchool() -> HasTrainingCertification(_driver);
		_annotations_DE_DAC07.put("category","Driver Eligibility Rule Set");
		_annotations_DE_DAC07.put("author","Jens Dietrich");
		_annotations_DE_DAC07.put("lastupdated","19/11/10");
		
		// rule annotations for rule  DE_DAC07: _driver.getHasDriversTrainingFromSchool() -> HasTrainingCertification(_driver);
		_annotations_DE_DAC07.put("description","If driver has taken driver's training from school then driver has training certification");
		
		
	
		// relationship annotations for rule  DE_DAC08: _driver.getHasDriversTrainingFromLicensedDriverTrainingCompany() -> HasTrainingCertification(_driver);
		_annotations_DE_DAC08.put("category","Driver Eligibility Rule Set");
		_annotations_DE_DAC08.put("author","Jens Dietrich");
		_annotations_DE_DAC08.put("lastupdated","19/11/10");
		
		// rule annotations for rule  DE_DAC08: _driver.getHasDriversTrainingFromLicensedDriverTrainingCompany() -> HasTrainingCertification(_driver);
		_annotations_DE_DAC08.put("description","If driver has taken driver's training from a licensed driver training company, then driver has training certification");
		
		
	
		// relationship annotations for rule  DE_DAC09: _driver.getHasTakenASeniorCitizenDriversRefresherCourse() -> HasTrainingCertification(_driver);
		_annotations_DE_DAC09.put("category","Driver Eligibility Rule Set");
		_annotations_DE_DAC09.put("author","Jens Dietrich");
		_annotations_DE_DAC09.put("lastupdated","19/11/10");
		
		// rule annotations for rule  DE_DAC09: _driver.getHasTakenASeniorCitizenDriversRefresherCourse() -> HasTrainingCertification(_driver);
		_annotations_DE_DAC09.put("description","If driver had taken a senior citizen driver's refresher course, then driver has training certification");
		
		
	}
		
	

	// interface generated for queries	
	 
	public static ResultSet<HasTrainingCertificationRel> hasDertification (  Driver driver  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<HasTrainingCertificationRel>(hasDertification ( _derivation ,  driver ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<HasTrainingCertificationRel> hasDertification ( final DerivationController _derivation ,  final Driver driver  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<HasTrainingCertificationRel>(3) {
			
			public ResourceIterator<HasTrainingCertificationRel> getNextIterator(int pos) {
			switch (pos) {
				
                		case 0: {
                			// invoke DE_DAC07: _driver.getHasDriversTrainingFromSchool() -> HasTrainingCertification(_driver);
                			return hasDertification_0(_derivation.pop(_derivationlevel) ,  driver );
                		}
				
                		case 1: {
                			// invoke DE_DAC08: _driver.getHasDriversTrainingFromLicensedDriverTrainingCompany() -> HasTrainingCertification(_driver);
                			return hasDertification_1(_derivation.pop(_derivationlevel) ,  driver );
                		}
				
                		case 2: {
                			// invoke DE_DAC09: _driver.getHasTakenASeniorCitizenDriversRefresherCourse() -> HasTrainingCertification(_driver);
                			return hasDertification_2(_derivation.pop(_derivationlevel) ,  driver );
                		}
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: hasDertification
	// rule: DE_DAC07: _driver.getHasDriversTrainingFromSchool() -> HasTrainingCertification(_driver);
	private static ResourceIterator<HasTrainingCertificationRel> hasDertification_0 (final DerivationController _derivation ,  final Driver driver ) {
		
		_derivation.log("HasTrainingCertification.DE_DAC07", DerivationController.RULE, _annotations_DE_DAC07);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: DE_DAC07: _driver.getHasDriversTrainingFromSchool() -> HasTrainingCertification(_driver);
		// prereqs: [_driver.getHasDriversTrainingFromSchool()]
		class _Bindings {
			private org.mandarax.examples.userv.domain.Driver _driver = driver;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite _driver.getHasDriversTrainingFromSchool()
		
		
		 // case 4
					if (!(_bindings._driver.hasDriversTrainingFromSchool())) {return EmptyIterator.DEFAULT;} 
					
		
		
		// rule head
		
		return new SingletonIterator(new HasTrainingCertificationRel(_bindings._driver));
        
		
		
		
	

	}
	// rule: DE_DAC08: _driver.getHasDriversTrainingFromLicensedDriverTrainingCompany() -> HasTrainingCertification(_driver);
	private static ResourceIterator<HasTrainingCertificationRel> hasDertification_1 (final DerivationController _derivation ,  final Driver driver ) {
		
		_derivation.log("HasTrainingCertification.DE_DAC08", DerivationController.RULE, _annotations_DE_DAC08);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: DE_DAC08: _driver.getHasDriversTrainingFromLicensedDriverTrainingCompany() -> HasTrainingCertification(_driver);
		// prereqs: [_driver.getHasDriversTrainingFromLicensedDriverTrainingCompany()]
		class _Bindings {
			private org.mandarax.examples.userv.domain.Driver _driver = driver;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite _driver.getHasDriversTrainingFromLicensedDriverTrainingCompany()
		
		
		 // case 4
					if (!(_bindings._driver.hasDriversTrainingFromLicensedDriverTrainingCompany())) {return EmptyIterator.DEFAULT;} 
					
		
		
		// rule head
		
		return new SingletonIterator(new HasTrainingCertificationRel(_bindings._driver));
        
		
		
		
	

	}
	// rule: DE_DAC09: _driver.getHasTakenASeniorCitizenDriversRefresherCourse() -> HasTrainingCertification(_driver);
	private static ResourceIterator<HasTrainingCertificationRel> hasDertification_2 (final DerivationController _derivation ,  final Driver driver ) {
		
		_derivation.log("HasTrainingCertification.DE_DAC09", DerivationController.RULE, _annotations_DE_DAC09);
		
			
	
		
		// utility class used to keep track of variables bindings
		// rule: DE_DAC09: _driver.getHasTakenASeniorCitizenDriversRefresherCourse() -> HasTrainingCertification(_driver);
		// prereqs: [_driver.getHasTakenASeniorCitizenDriversRefresherCourse()]
		class _Bindings {
			private org.mandarax.examples.userv.domain.Driver _driver = driver;
		}
		final _Bindings _bindings = new _Bindings();
		ResourceIterator<?> _tmp = null;
		
		 
		
		
		
		
		

		 
		
		
		
		// apply prerequisite _driver.getHasTakenASeniorCitizenDriversRefresherCourse()
		
		
		 // case 4
					if (!(_bindings._driver.hasTakenASeniorCitizenDriversRefresherCourse())) {return EmptyIterator.DEFAULT;} 
					
		
		
		// rule head
		
		return new SingletonIterator(new HasTrainingCertificationRel(_bindings._driver));
        
		
		
		
	

	}
	
	
	// methods representing aggregation functions
	
	
}


