package test.org.mandarax.compiler.naf1;
 
import test.org.mandarax.compiler.*;

import org.mandarax.rt.*;

/**
 * Interface for queries for relationship <strong>Mother</strong>.
 * @version Nov 9, 2010 11:29:30 AM 
 */
public class MotherRelInstances {
	// object references
	 
	public static Person m1 = new Person("m1",25);
	 
	public static Person m2 = new Person("m2",5);
	 
	public static Person m3 = new Person("m3",5);
	 
	public static Person f1 = new Person("f1",25);
	
	
	// fields representing annotations
	
	
	// initialise annotations
	static {}
		
	

	// interface generated for queries	
	 
	public ResultSet<MotherRel> getMother (  Person child  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<MotherRel>(getMother ( _derivation ,  child ),_derivation);
	} 
	 
	public ResultSet<MotherRel> isMother (  Person mother ,  Person child  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<MotherRel>(isMother ( _derivation ,  mother ,  child ),_derivation);
	} 
	 
	public ResultSet<MotherRel> getChildren (  Person mother  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<MotherRel>(getChildren ( _derivation ,  mother ),_derivation);
	} 
	 
	public ResultSet<MotherRel> getMotherAndChild (  ) {
		DerivationController _derivation = new DefaultDerivationController();
		return new ResultSet<MotherRel>(getMotherAndChild ( _derivation  ),_derivation);
	} 
	
	
	// implementations - these methods are referenced by code generated from other rules in this package
	// and therefore kept static 
	 
	static ResourceIterator<MotherRel> getMother ( final DerivationController _derivation ,  final Person child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<MotherRel>(0) {
			
			public ResourceIterator<MotherRel> getNextIterator(int pos) {
			switch (pos) {
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<MotherRel> isMother ( final DerivationController _derivation ,  final Person mother ,  final Person child  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<MotherRel>(0) {
			
			public ResourceIterator<MotherRel> getNextIterator(int pos) {
			switch (pos) {
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<MotherRel> getChildren ( final DerivationController _derivation ,  final Person mother  ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<MotherRel>(0) {
			
			public ResourceIterator<MotherRel> getNextIterator(int pos) {
			switch (pos) {
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	 
	static ResourceIterator<MotherRel> getMotherAndChild ( final DerivationController _derivation   ) {
		final int _derivationlevel = _derivation.size();
		return new IteratorChain<MotherRel>(0) {
			
			public ResourceIterator<MotherRel> getNextIterator(int pos) {
			switch (pos) {
				
				default: return EmptyIterator.DEFAULT;
			}}
		};
	} 
	
	
	
	// private methods - each method represents the invocation of a single rule for a certain query
	// query: getMother
	// query: isMother
	// query: getChildren
	// query: getMotherAndChild
	
	
}

