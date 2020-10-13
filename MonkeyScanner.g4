//-------------------------------------------------------------------------------------------------------------------
//                                      Lexer Rules
//-------------------------------------------------------------------------------------------------------------------
lexer grammar MonkeyScanner;
//-------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------
// Separators
//-------------------------------------------------------------------------------------------------------------------
L_PAREN : '(';
R_PAREN : ')';
L_BRACE : '{';
R_BRACE : '}';
L_BRACK : '[';
R_BRACK : ']';
PYCOMMA : ';';
COMMA   : ',';
COLON   : ':';

//-------------------------------------------------------------------------------------------------------------------
//  Operators
//-------------------------------------------------------------------------------------------------------------------
LE      : '<=';
GE      : '>=';
EQUAL   : '==';
GT      : '>';
LT      : '<';
ASSIGN  : '=';
// basics
ADD     : '+';
SUB     : '-';
MUL     : '*';
DIV     : '/';

//-------------------------------------------------------------------------------------------------------------------
//Reserved words
//-------------------------------------------------------------------------------------------------------------------
LET     : 'let';
RETURN  : 'return';
FN      : 'fn';
IF      : 'if';
ELSE    : 'else';
PUTS    : 'puts';

//-------------------------------------------------------------------------------------------------------------------
//Arrays Funtions
//-------------------------------------------------------------------------------------------------------------------
LEN     : 'len';
FIRST   : 'first';
LAST    : 'last';
REST    : 'rest';
PUSH    : 'push';

//-------------------------------------------------------------------------------------------------------------------
// Types
//-------------------------------------------------------------------------------------------------------------------
TRUE    : 'true';
FALSE   : 'false';
INTEGER : [0-9]([0-9])*;
STRING  : '"' (~["])* '"';
IDENT   : [a-zA-Z]([a-zA-Z]|[0-9]|'_')*;

//-------------------------------------------------------------------------------------------------------------------
// Skiped
//-------------------------------------------------------------------------------------------------------------------
WS              : [ \r\t\n]+                        -> skip ;
BLOCK_COMMENT   : '/*' (.|BLOCK_COMMENT)* '*/'      -> skip;
SINGLE_COMMENT  : '//' ~[\r\n]* '\r'? '\n'          -> skip ;