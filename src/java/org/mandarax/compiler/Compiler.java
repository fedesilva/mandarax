package org.mandarax.compiler;

import java.util.Collection;

import org.mandarax.dsl.CompilationUnit;
import org.mandarax.dsl.parser.ScriptException;

public interface Compiler {
	public void compile(Collection<Source> sources,Location target,CompilationMode mode) throws CompilerException,ScriptException  ; 
}
