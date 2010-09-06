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

package org.mandarax.dsl.parser;

import java.io.IOException;
import java.io.InputStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.mandarax.dsl.*;

/**
 * Utility to read artefacts from scripts.
 * @author jens dietrich
 */
public class ScriptReader {
	private MandaraxParser getParser(InputStream in) throws ScriptException {
		MandaraxLexer lexer = null;
		try {
			lexer = new MandaraxLexer(new ANTLRInputStream(in));
		} catch (IOException e) {
			throw new ScriptException(e);
		}
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		return new MandaraxParser(tokens);		
	}
	public Expression readExpression(InputStream in) throws ScriptException {
		try {
			return getParser(in).expression().value;
		} catch (Exception e) {
			throw new ScriptException(e);
		}
	}
	public ImportDeclaration readImportDeclaration(InputStream in) throws ScriptException {
		try {
			return getParser(in).importDeclaration().value;
		} catch (Exception e) {
			throw new ScriptException(e);
		}
	}
	public RelationshipDefinition readRelationshipDefinition(InputStream in) throws ScriptException {
		try {
			return getParser(in).relationshipDefinition().value;
		} catch (Exception e) {
			throw new ScriptException(e);
		}
	}
	
	public PackageDeclaration readPackageDeclaration(InputStream in) throws ScriptException {
		try {
			return getParser(in).packageDeclaration().value;
		} catch (Exception e) {
			throw new ScriptException(e);
		}
	}
	
	public Rule readRule(InputStream in) throws ScriptException {
		try {
			return getParser(in).rule().value;
		} catch (Exception e) {
			throw new ScriptException(e);
		}
	}
	
	
}
