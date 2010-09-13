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

package org.mandarax.dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to keep track of contextual information such as namespace definitions and defined objects.
 * @author jens dietrich
 */

public class Context {
	private PackageDeclaration packageDeclaration = null;
	private List<ImportDeclaration> imports = new ArrayList<ImportDeclaration>();
	private List<ImportDeclaration> staticImports = new ArrayList<ImportDeclaration>();
	private List<ObjectDeclaration> objectDeclarations = new ArrayList<ObjectDeclaration>();
	
	public List<ImportDeclaration> getImportDeclarations() {
		return imports;
	}
	public PackageDeclaration getPackageDeclaration() {
		return packageDeclaration;
	}
	public List<ImportDeclaration> getStaticImportDeclarations() {
		return staticImports;
	}
	public List<ObjectDeclaration> getObjectDeclarations() {
		return objectDeclarations;
	}
	public void add(ImportDeclaration imp) {
		if (imp.isStaticImport()) this.staticImports.add(imp);
		else this.imports.add(imp);
	}
	public void add(ObjectDeclaration decl) {
		objectDeclarations.add(decl);
	}
	public void setPackageDeclaration(PackageDeclaration packageDeclaration) {
		this.packageDeclaration = packageDeclaration;
	}
}
