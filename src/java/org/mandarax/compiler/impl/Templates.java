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

package org.mandarax.compiler.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.mandarax.MandaraxException;
import org.mandarax.compiler.CompilerException;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;

/**
 * Utility to manage templates.
 * @author jens dietrich
 */
public class Templates {
	
	private static Map<String,CompiledTemplate> templateCache = new HashMap<String,CompiledTemplate>();
	
	public static CompiledTemplate getTemplate(String name) throws MandaraxException {
		synchronized (Templates.class) {
			CompiledTemplate template = templateCache.get(name);
			if (template!=null) return template;
			
			try {
				InputStream in = Templates.class.getResourceAsStream("/org/mandarax/compiler/impl/templates/"+name+".mv");
				BufferedReader breader = new BufferedReader(new InputStreamReader(in));
				String line = null;
				StringBuffer buffer = new StringBuffer();
				while ((line=breader.readLine())!=null) {
					buffer.append(line);
					buffer.append('\n');
				}
				breader.close();
				
				template = TemplateCompiler.compileTemplate(buffer.toString());
				templateCache.put(name,template);
				return template;
			}
			catch (Exception x) {
				throw new CompilerException("Cannot load template " + name,x);
			}
		}
	}
	
	public static void resetCache() throws MandaraxException {
		synchronized (Templates.class) {
			templateCache.clear();
		}
	}
}
