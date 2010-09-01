
grammar Mandarax;
options {backtrack=true; memoize=true;ASTLabelType=CommonTree;output=AST;}

@parser::header{ 
	package org.mandarax.dsl.parser;
	import org.mandarax.dsl.*;
	import static org.mandarax.dsl.Utils.*;
}
@lexer::header{ 
	package org.mandarax.dsl.parser;
}

@lexer::members {
  protected boolean enumIsKeyword = true;
  protected boolean assertIsKeyword = true;
}
@parser::members {
  private Position pos(Token token) {
  	return new Position(token.getLine(),token.getCharPositionInLine());
  }
  private Position pos(Expression expression) {
  	return expression.getPosition().clone();
  }
}

// starting point for parsing java style expressions
// use the expression() method in parser to access it

expression returns [Expression value]
    :   r=conditionalExpression {$value=r.value;}
    ;

constantDeclarator
    :   Identifier 
        ;

typeName
    :   qualifiedName
    ;
// TODO arrays
type returns [String value]
	:	i = classOrInterfaceType ('[' ']')* {$value = i.value;}
	|	j = primitiveType ('[' ']')* {$value = j.value;}
	;
	
classOrInterfaceType returns [String value]
	:	i = Identifier {$value=i.getText();} ('.' i = Identifier {$value=$value+'.'+i.getText();})*
	;
	
	
primitiveType  returns [String value]
    :   'boolean' {$value="boolean";}
    |   'char'	{$value="char";}
    |   'byte' {$value="byte";}
    |   'short' {$value="short";}
    |   'int' {$value="int";}
    |   'long' {$value="boolean";}
    |   'float' {$value="long";}
    |   'double' {$value="double";}
    ;
     
qualifiedNameList
    :   qualifiedName (',' qualifiedName)*
    ;

qualifiedName returns [Expression value]
	:	i = Identifier {$value=new Variable(pos(i),i.getText());} ('.' j = Identifier {$value=new MemberAccess(pos(i),$value,j.getText());})*
	;
    
literal returns [Expression value]
    :   l1 = integerLiteral {$value = l1.value;}
//    |   l2 = FloatingPointLiteral {$value = l2.value;}
//    |   l3 = CharacterLiteral {$value = l3.value;}
    |   l4 = StringLiteral {$value = new StringLiteral(pos(l4),l4.getText().substring(1,l4.getText().length()-1));}
    |   l5 = booleanLiteral {$value = l5.value;}
//    |   l6 = 'null' {$value = l6.value;}
    ;

integerLiteral returns [Expression value]
    :   hex = HexLiteral {$value = new IntLiteral(pos(hex),Integer.parseInt(hex.getText().substring(2),16));}
    |   oct = OctalLiteral {$value = new IntLiteral(pos(oct),Integer.parseInt(oct.getText(),8));}
    |   dec = DecimalLiteral {$value = new IntLiteral(pos(dec),Integer.parseInt(dec.getText()));}
    ;

booleanLiteral returns [Expression value]
    :   t = 'true' {$value = new BooleanLiteral(pos(t),true);}
    |   f = 'false' {$value = new BooleanLiteral(pos(f),false);}
    ;


elementValuePairs
    :   elementValuePair (',' elementValuePair)*
    ;

elementValuePair
    :   Identifier '=' elementValue
    ;
    
elementValue
    :   conditionalExpression
    |   elementValueArrayInitializer
    ;
    
elementValueArrayInitializer
    :   '{' (elementValue (',' elementValue)*)? (',')? '}'
    ;
    
// EXPRESSIONS

parExpression returns [Expression value]
    :   '(' expr = expression ')' {$value = expr.value;}
    ;
    
expressionList returns [List<Expression> values]
@init {$values = new ArrayList<Expression>();}
    :   e1 = expression {$values.add(e1.value);} (',' e2 = expression {$values.add(e2.value);} )*
    ;

//statementExpression
//    :   expression
//    ;
    
//constantExpression
//    :   expression
//    ;

conditionalExpression returns [Expression value]
    :   r1 = disjunction ( '?' r2 = expression ':' r3 = expression )? {$value=(r2==null)?r1.value:new ConditionalExpression(pos(r1.value),r1.value,r2.value,r3.value);}
    ;
 
disjunction returns [Expression value]
    :   part1 = conjunction {$value = part1.value;} ( '|' part = conjunction {$value = new BinaryExpression(pos(part1.value),BinOp.OR,$value,part.value);})* 
    ;

conjunction returns [Expression value]
    :   part1 = equalityExpression {$value = part1.value;} ( '&' part =  equalityExpression {$value = new BinaryExpression(pos(part1.value),BinOp.AND,$value,part.value);})* 
    ;

equalityExpression returns [Expression value]
    :   part1 = instanceOfExpression {$value=part1.value;} ( op = ('==' | '!=') part2 = instanceOfExpression {$value=new BinaryExpression(pos(part1.value),binOpForName(op.getText()),$value,part2.value);})* 
    ;

instanceOfExpression returns [Expression value]
    :   part = relationalExpression ('instanceof' t = type)? {$value = (t==null)?part.value:new InstanceOfExpression(pos(part.value),part.value,t.value);}
    ;

relationalExpression  returns [Expression value]
    :   part1 = shiftExpression {$value=part1.value;} ( op = relationalOp part2 = shiftExpression {$value = new BinaryExpression(pos(part1.value),op.value,$value,part2.value);})* 
    ;
    

relationalOp  returns [BinOp value]
    :   '<=' {$value = BinOp.LTE;}
    |   '>=' {$value = BinOp.GTE;}
    |	'<' {$value = BinOp.LT;}
    |   '>' {$value = BinOp.GT;}
    ;

// note: only supports binary shift expressions
shiftExpression returns [Expression value]
    :   part1 = additiveExpression {$value = part1.value;} ( op = shiftOp part = additiveExpression {$value = new BinaryExpression(pos(part1.value),op.value,$value,part.value);})? 
    ;

shiftOp returns [BinOp value]
    :   ('<' '<')=> t1='<' t2='<' 
        { $t1.getLine() == $t2.getLine() && 
          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() }? {$value = BinOp.SHIFT_LL;}
    |   ('>' '>' '>')=> t1='>' t2='>' t3='>' 
        { $t1.getLine() == $t2.getLine() && 
          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() &&
          $t2.getLine() == $t3.getLine() && 
          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() }? {$value = BinOp.SHIFT_RRR;}
    |   ('>' '>')=> t1='>' t2='>'
        { $t1.getLine() == $t2.getLine() && 
          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() }? {$value = BinOp.SHIFT_RR;}
    ;


additiveExpression returns [Expression value]
    :  part1 = multiplicativeExpression {$value = part1.value;}( op = ('+' | '-') part = multiplicativeExpression {$value = new BinaryExpression(pos(part1.value),binOpForName(op.getText()),$value,part.value);})* 
    ;

multiplicativeExpression returns [Expression value]
    :  part1 = unaryExpression {$value = part1.value;} ( op =( '*' | '/' | '%' ) part = unaryExpression {$value = new BinaryExpression(pos(part1.value),binOpForName(op.getText()),$value,part.value);})* 
    ;
    
unaryExpression returns [Expression value]
    :   '-' part1 = unaryExpression {$value = new UnaryExpression(pos(part1.value),UnOp.MINUS,part1.value);}
    |   part2 = unaryExpressionNotPlusMinus {$value = part2.value;}
    ;

unaryExpressionNotPlusMinus returns [Expression value]
    :   '~' r1 = unaryExpression {$value = new UnaryExpression(pos(r1.value),UnOp.COMPL,r1.value);}
    |   '!' r2 = unaryExpression {$value = new UnaryExpression(pos(r2.value),UnOp.NOT,r2.value);}
    |   r3 = castExpression {$value = r3.value;}
    |   r8 = methodInvocation {$value = r8.value;}
    |   r7 = propertyAccess {$value = r7.value;}
    |   r4 = objectref {$value = r4.value;}
    |   r5 = qualifiedName {$value = r5.value;}
    |   r6 = parExpression {$value = r6.value;}
    ;
    
propertyAccess returns [Expression value]
    :   o = objectref {$value = o.value;} ('.' i= Identifier  {$value = new MemberAccess(pos(o.value),$value,i.getText());})* 
    ;

methodInvocation returns [Expression value]
    :   o = objectref {$value = o.value;} ('.' i = Identifier '('(p = expressionList)? ')' {$value = new MemberAccess(pos(o.value),$value,i.getText(),p==null?new ArrayList<Expression>():p.values);})* 
    ;
    
objectref returns [Expression value]
    	: r1 = Identifier {$value = new Variable(pos(r1),r1.getText());}
    	| r2 = literal {$value = r2.value;}
    	| r3 = parExpression {$value = r3.value;}
    	;	       

castExpression returns [Expression value]
    :  '(' t1 = primitiveType ')' exp1 = unaryExpression {$value = new CastExpression(pos(t1.start),exp1.value,t1.value);}
    |  '(' t2 = type ')' exp2 = unaryExpressionNotPlusMinus  {$value = new CastExpression(pos(t2.start),exp2.value,t2.value);}
    ;

arguments
    :   '(' expressionList? ')'
    ;

// LEXER

HexLiteral : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OctalLiteral : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix : ('l'|'L') ;

FloatingPointLiteral
    :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    |   ('0'..'9')+ Exponent FloatTypeSuffix?
    |   ('0'..'9')+ FloatTypeSuffix
    ;

fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

CharacterLiteral
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;

StringLiteral 
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UnicodeEscape
    |   OctalEscape
    ;

fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

    
Identifier 
    :   Letter (Letter|JavaIDDigit)*
    ;

/**I found this char range in JavaCC's grammar, but Letter and Digit overlap.
   Still works, but...
 */
fragment
Letter
    :  '\u0024' |
       '\u0041'..'\u005a' |
       '\u005f' |
       '\u0061'..'\u007a' |
       '\u00c0'..'\u00d6' |
       '\u00d8'..'\u00f6' |
       '\u00f8'..'\u00ff' |
       '\u0100'..'\u1fff' |
       '\u3040'..'\u318f' |
       '\u3300'..'\u337f' |
       '\u3400'..'\u3d2d' |
       '\u4e00'..'\u9fff' |
       '\uf900'..'\ufaff'
    ;

fragment
JavaIDDigit
    :  '\u0030'..'\u0039' |
       '\u0660'..'\u0669' |
       '\u06f0'..'\u06f9' |
       '\u0966'..'\u096f' |
       '\u09e6'..'\u09ef' |
       '\u0a66'..'\u0a6f' |
       '\u0ae6'..'\u0aef' |
       '\u0b66'..'\u0b6f' |
       '\u0be7'..'\u0bef' |
       '\u0c66'..'\u0c6f' |
       '\u0ce6'..'\u0cef' |
       '\u0d66'..'\u0d6f' |
       '\u0e50'..'\u0e59' |
       '\u0ed0'..'\u0ed9' |
       '\u1040'..'\u1049'
   ;

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;

COMMENT
    :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;
    