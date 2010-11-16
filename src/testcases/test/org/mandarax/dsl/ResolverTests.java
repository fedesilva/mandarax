/*
 * Copyright 2010 Jens Dietrich 
 * Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.gnu.org/licenses/agpl.html 
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package test.org.mandarax.dsl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mandarax.dsl.Context;
import org.mandarax.dsl.Expression;
import org.mandarax.dsl.ImportDeclaration;
import org.mandarax.dsl.PackageDeclaration;
import org.mandarax.dsl.Variable;
import org.mandarax.dsl.util.AbstractTypeReasoner;
import org.mandarax.dsl.util.DefaultResolver;
import org.mandarax.dsl.util.Resolver;
import org.mandarax.dsl.util.ResolverException;
import org.mandarax.dsl.util.TypeReasoner;
import org.mandarax.dsl.util.TypeReasoningException;
import static org.junit.Assert.*;
import static test.org.mandarax.dsl.TestUtils.*;

public class ResolverTests extends AbstractTests {
	
	@Test
	public void testResolvingClasses1() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import java.util.Date;");
		ImportDeclaration imp2 = readImportDeclaration("import java.io.File;");
		Context context = new Context();
		context.add(imp1);
		context.add(imp2);
		
		Class clazz = resolver.getType(context,"Date");
		assertEquals("java.util.Date",clazz.getName());
	}
	
	@Test
	public void testResolvingClasses2() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import java.util.Date;");
		ImportDeclaration imp2 = readImportDeclaration("import java.io.File;");
		Context context = new Context();
		context.add(imp1);
		context.add(imp2);
		
		Class clazz = resolver.getType(context,"File");
		assertEquals("java.io.File",clazz.getName());
	}
	
	@Test
	public void testResolvingClasses3() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import java.util.Date;");
		ImportDeclaration imp2 = readImportDeclaration("import java.io.File;");
		Context context = new Context();
		context.add(imp1);
		context.add(imp2);
		
		Class clazz = resolver.getType(context,"String");
		assertEquals("java.lang.String",clazz.getName());
	}
	
	@Test
	public void testResolvingClasses4() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import java.util.Date;");
		PackageDeclaration p = readPackageDeclaration("package java.io;");
		Context context = new Context();
		context.add(imp1);
		context.setPackageDeclaration(p);
		
		Class clazz = resolver.getType(context,"File");
		assertEquals("java.io.File",clazz.getName());
	}
	
	
	@Test
	public void testResolvingFunctions1() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import static java.lang.System.currentTimeMillis;");
		Context context = new Context();
		context.add(imp1);
		
		Method function = resolver.getFunction(context,"currentTimeMillis", new String[]{});
		assertEquals("currentTimeMillis",function.getName());
	}
	
	@Test
	public void testResolvingFunctions2() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import static java.lang.System.*;");
		Context context = new Context();
		context.add(imp1);
		
		Method function = resolver.getFunction(context,"currentTimeMillis", new String[]{});
		assertEquals("currentTimeMillis",function.getName());
	}
	
	@Test
	public void testResolvingFunctions3() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import static java.lang.System.setProperty;");
		Context context = new Context();
		context.add(imp1);
		
		Method function = resolver.getFunction(context,"setProperty", new String[]{String.class.getName(),String.class.getName()});
		assertEquals("setProperty",function.getName());
	}
	
	@Test
	public void testResolvingFunctions4() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import static java.lang.System.*;");
		Context context = new Context();
		context.add(imp1);
		
		Method function = resolver.getFunction(context,"setProperty", new String[]{String.class.getName(),String.class.getName()});
		assertEquals("setProperty",function.getName());
	}
	
	@Test(expected=ResolverException.class)
	public void testResolvingFunctions5() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import static java.lang.System.*;");
		Context context = new Context();
		context.add(imp1);
		
		resolver.getFunction(context,"noSuchMethod", new String[]{});
	}
	
	@Test(expected=ResolverException.class) // not a static method
	public void testResolvingFunctions6() throws Exception {
		Resolver resolver = new DefaultResolver();
		ImportDeclaration imp1 = readImportDeclaration("import static java.lang.String.*;");
		Context context = new Context();
		context.add(imp1);
		
		resolver.getFunction(context,"length", new String[]{});
	}
	
	
}
