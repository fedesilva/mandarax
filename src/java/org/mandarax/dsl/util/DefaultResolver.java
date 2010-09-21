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

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.mandarax.dsl.Context;
import org.mandarax.dsl.ImportDeclaration;

/**
 * Default resolver. Will try to resolve missing field references using property getters defined using the Java Beans spec.
 * E.g., in obj.firstName , if there is no public field firstName, the resolver will try to find the method getFirstName().
 * @author jens dietrich
 */

public class DefaultResolver implements Resolver {
	
	private ClassLoader classloader = null;

	public DefaultResolver(ClassLoader classloader) {
		super();
		this.classloader = classloader;
	}
	public DefaultResolver() {
		super();
	}

	@Override
	public Member getMember(Context context,String name, String className,String... paramTypeNames) throws ResolverException {
		Class clazz = getType(context,name);
		boolean isFieldRef = paramTypeNames==null;
		if (isFieldRef) {
			Field field = null;
			try {
				field = clazz.getField(name);
			} catch (Exception e) {
				// TODO: logging
			}
			if (field!=null && Modifier.isPublic(field.getModifiers())) {
				return field;
			}
			
			// try to use bean framework
			try {
				PropertyDescriptor[] properties = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
				for (PropertyDescriptor p:properties) {
					if (p.getName().equals(name)) {
						return p.getReadMethod();
					}
				}
			} catch (IntrospectionException e) {
				throw new ResolverException("Cannot introspect class " + clazz.getName(),e);
			}
			throw new ResolverException("Cannot resolve property " + name + " in "+ clazz.getName());
		}
		
		// method access
		Class[] paramTypes = new Class[paramTypeNames.length];
		for (int i=0;i<paramTypeNames.length;i++) {
			paramTypes[i] = getType(context,paramTypeNames[i]);
		}
		
		try {
			Method m =  clazz.getMethod(name,paramTypes);
			if (Modifier.isPublic(m.getModifiers())) {
				return m;
			}
			else {
				throw new ResolverException("Method " + m + " in "+ clazz.getName() + " is not visible");
			}
		} catch (Exception e) {
			throw new ResolverException("Cannot resolve method " + name + " in "+ clazz.getName(),e);
		}
		
	}

	@Override
	public Class getType(Context context,String name) throws ResolverException {
		Class clazz = tryToLoad(name);
		if (clazz!=null) return clazz;
		
		// try to load class in the package defined in the context
		if (context.getPackageDeclaration()!=null) {
			clazz = tryToLoad(""+context.getPackageDeclaration().getName()+'.'+name);
			if (clazz!=null) return clazz;
		}
		
		// try to load class from java.lang
		clazz = tryToLoad("java.lang."+name);
		if (clazz!=null) return clazz;
		
		// try to load class from imported classes
		for (ImportDeclaration imp:context.getImportDeclarations()) {
			if (!imp.isUsingWildcard() && imp.getName().endsWith("."+name)) {
				clazz = tryToLoad(imp.getName());
				if (clazz!=null) return clazz;
			}
		}

		// try to load class from imported packages
		for (ImportDeclaration imp:context.getImportDeclarations()) {
			if (imp.isUsingWildcard()) {
				clazz = tryToLoad(imp.getName()+'.'+name);
				if (clazz!=null) return clazz;
			}
		}
		
		// nothing worked - throw exception	
		throw new ResolverException("Cannot find class " + name);
	}
	
	private Class tryToLoad(String name) {
		try {
			return (classloader==null)?Class.forName(name):classloader.loadClass(name);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	
	/**
	 * Get the function for the given name. 
	 * A function is a static method. 
	 * Functions are defined externally, and imported using static imports. 
	 * <b>postcondition: The method should be static. </b>
	 * @param context the context (that has the imports)
	 * @param name the function name
	 * @param paramTypeNames the array is null if this is a field reference!
	 * @return a static method
	 */
	public Method getFunction(Context context,String name,String... paramTypeNames) throws ResolverException {
		Method function = null;
		Class clazz = null;
		
		Class[] paramTypes = new Class[paramTypeNames.length];
		for (int i=0;i<paramTypeNames.length;i++) {
			try {
				paramTypes[i]=getType(context,paramTypeNames[i]);
			}
			catch (Exception x) {
				throw new ResolverException("Cannot find method " + name + " with parameters " + paramTypeNames);
			}
		}
		
		// try to load method from imported classes
		for (ImportDeclaration imp:context.getStaticImportDeclarations()) {
			if (!imp.isUsingWildcard() && imp.getName().endsWith("."+name)) {
				String className = imp.getName().substring(0,imp.getName().lastIndexOf(name)-1);
				return tryToLoadStaticMethod(context,name,className,paramTypes);
			}
		}
		
		// try to load method from imported packages
		for (ImportDeclaration imp:context.getStaticImportDeclarations()) {
			if (imp.isUsingWildcard()) {
				return tryToLoadStaticMethod(context,name,imp.getName(),paramTypes);
			}
		}
		
		throw new ResolverException("Cannot resolve static method " + name);
	}
	
	private Method tryToLoadMethod(Context context,String name,String className,Class[] paramTypes) throws ResolverException {
		Class clazz = null;
		Method method = null;
		try {
			clazz = getType(context,className);
		}
		catch (Exception x) {
			throw new ResolverException("Cannot find class that defines static method " + name,x);
		}
		try {
			method = clazz.getMethod(name, paramTypes);
		}
		catch (Exception x) {
			throw new ResolverException("Cannot find method " + name,x);
		}
		
		if (Modifier.isPublic(method.getModifiers())) {
			return method;
		}
		else {
			throw new ResolverException("Method " + method + " in "+ clazz.getName() + " is not visible");
		}
		
	}
	
	private Method tryToLoadStaticMethod(Context context,String name,String className,Class[] paramTypes) throws ResolverException {
		Method function = tryToLoadMethod(context,name,className,paramTypes);
		if (!Modifier.isStatic(function.getModifiers())) {
			throw new ResolverException("Imported functions should be static, but this one is not: " + name);
		}
		return function;
	}
	
}
