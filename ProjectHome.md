The new mandarax is a derivation rule compiler for Java, based on ideas of the old [mandarax project](http://mandarax.sf.net) (an interpreter based rule engine, this project dates back to 2000), and the [take rule compiler](http://code.google.com/p/take).

The new mandarax has the following features:

  * An expressive and simple DSL to define relationships through derivation rules. The language re-uses many Java language elements. In particular, the expression syntax is Java based.

  * A modular, template based compiler.

  * Support for advanced features such as negation as failure, In Domain expressions, relationship inheritance and aggregations.


The software is licensed under the [GNU AFFERO GENERAL PUBLIC LICENSE, Version 3](http://www.gnu.org/licenses/agpl.html). Contact us if you are interested in a commercial, non-GPL license.

## Update 27 Jan  2011 ##

**Version 1.1.0 has been released** !! The main new feature is support for **includes** - references to expressions that are iterables of relationship instances. This feature can be used to define (large) sets of facts outside Mandarax, for instances using relational databases or web services, and to reference (not import!) them within the rule definitions.

## Update 2 Dec  2010 ##

**Version 1.0.1 has been released** !! There is only one minor change -  a new class `org.mandarax.compiler.impl.FileSystemLocation` that is useful to simplify compilation scripts.

## Update 24 Nov 2010 ##

**Version 1.0.0 has been released** !! There is also a sample application based on the Product Derby scenario, this application can be started (using Java web start) by clicking on [this link](http://www.google.com/url?q=http%3A%2F%2Fwww-ist.massey.ac.nz%2FJBDietrich%2Fuserv-mdrx%2Fuserv.jnlp&sa=D&sntz=1&usg=AFQjCNEmZHmIMScqc-LHTatM99tAos2OWA). A manual can be found [here](https://docs.google.com/document/d/1ZbRxh_QMgb1-3uWGqW5woCE798c7t6vPcUMFevLXcZM/edit?hl=en#heading=h.i2m6diq77oxe).