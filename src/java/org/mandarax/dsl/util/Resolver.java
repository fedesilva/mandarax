package org.mandarax.dsl.util;

import java.lang.reflect.Method;
/**
 * Utility to map names used in scripts to classes and types that can be analysed and invoked using reflection.
 * @author jens dietrich
 */
public interface Resolver {
	Method getMethod(String name,String className,String... paramTypeNames);
	Class getType(String name);
}	
