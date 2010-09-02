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

/**
 * Default resolver. Will try to resolve missing field references using property getters defined using the Java Beans spec.
 * E.g., in obj.firstName , if there is no public field firstName, the resolver will try to find the method getFirstName().
 * @author jens dietrich
 */

public abstract class AbstractResolver implements Resolver {
	
	private ClassLoader classloader = null;

	public AbstractResolver(ClassLoader classloader) {
		super();
		this.classloader = classloader;
	}
	public AbstractResolver() {
		super();
	}

	@Override
	public Member getMember(String name, String className,String... paramTypeNames) throws ResolverException {
		Class clazz = getType(name);
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
			paramTypes[i] = getType(paramTypeNames[i]);
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
	public Class getType(String name) throws ResolverException {
		try {
			return (classloader==null)?Class.forName(name):classloader.loadClass(name);
		} catch (ClassNotFoundException e) {
			throw new ResolverException("Cannot find class " + name,e);
		}
	}

}
