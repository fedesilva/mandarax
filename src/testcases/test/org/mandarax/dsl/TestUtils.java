/*
 * Copyright 2010 Jens Dietrich Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package test.org.mandarax.dsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.mandarax.dsl.*;
import org.mandarax.dsl.parser.ScriptReader;

/**
 * Test utilities.
 * @author jens dietrich
 */
public class TestUtils {
	
	public static void testVarOpInt(Expression x,String varName,BinOp op,int intValue) throws Exception {
		assertTrue(x instanceof BinaryExpression);
		BinaryExpression bx = (BinaryExpression)x;
		assertTrue(bx.getLeft() instanceof Variable);
		assertTrue(bx.getRight() instanceof IntLiteral);
		assertEquals(op,bx.getOperator());
		assertEquals(varName,((Variable)bx.getLeft()).getName());
		assertEquals(intValue,(int)((IntLiteral)bx.getRight()).getValue());
	}
	
	public static void assertVariable(Expression x,String varName) throws Exception {
		assertTrue(x instanceof Variable);
		Variable v = (Variable)x;
		assertEquals(varName,v.getName());
	}
	
	public static void assertStringLieral(Expression x,String value) throws Exception {
		assertTrue(x instanceof StringLiteral);
		StringLiteral v = (StringLiteral)x;
		assertEquals(value,v.getValue());
	}
	
	public static void assertIntLiteral(Expression x,int value) throws Exception {
		assertTrue(x instanceof IntLiteral);
		IntLiteral v = (IntLiteral)x;
		assertEquals(new Integer(value),v.getValue());
	}
	
	
	
	public static InputStream getStream(String s) throws Exception {
		return new ByteArrayInputStream(s.getBytes("UTF-8"));
	}

	public static Expression readExpression(String s) throws Exception {
		InputStream in = getStream(s);
		return new ScriptReader().readExpression(in);
	}
	public static ImportDeclaration readImportDeclaration(String s) throws Exception {
		InputStream in = getStream(s);
		return new ScriptReader().readImportDeclaration(in);
	}
	public static RelationshipDefinition readRelationshipDefinition(String s) throws Exception {
		InputStream in = getStream(s);
		return new ScriptReader().readRelationshipDefinition(in);
	}
	
	public static PackageDeclaration readPackageDeclaration(String s) throws Exception {
		InputStream in = getStream(s);
		return new ScriptReader().readPackageDeclaration(in);
	}
	
	public static Rule readRule(String s) throws Exception {
		InputStream in = getStream(s);
		return new ScriptReader().readRule(in);
	}
}
