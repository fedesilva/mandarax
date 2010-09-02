/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package org.mandarax.dsl.util;

import java.lang.reflect.Member;
/**
 * Utility to map names used in scripts to classes and types that can be analysed and invoked using reflection.
 * @author jens dietrich
 */
public interface Resolver {
	/**
	 * Get the member for the given name. 
	 * postcondition: The member should be either a Method or a Field. 
	 * The reason to have a general method covering both fields and methods is to treat properties transparently: 
	 * a property used field syntax, but is represented by a getter method.
	 * @param name
	 * @param className
	 * @param paramTypeNames the array is null if this is a field reference!
	 * @return
	 */
	Member getMember(String name,String className,String... paramTypeNames) throws ResolverException ;
	/**
	 * Get the class for the given name. 
	 * @param name
	 * @return
	 */
	Class getType(String name) throws ResolverException ;
}	
