//-------------------------------------------------------------------------------------------------------------------
//                                    Parser
//-------------------------------------------------------------------------------------------------------------------
parser grammar MonkeyParser;

options {
    tokenVocab = MonkeyScanner;
}

//-------------------------------------------------------------------------------------------------------------------
//                               Grammar for Monkey Language
//-------------------------------------------------------------------------------------------------------------------
program  	                    :statement*                                                                             #programAST;

//-------------------------------------------------------------------------------------------------------------------
statement  	                    :LET letStatement                                                                       #statement_LetAST
                                | RETURN returnStatement                                                                #statement_returnAST
                                | expressionStatement                                                                   #callExpressionStatementAST;

//-------------------------------------------------------------------------------------------------------------------
//Assign
letStatement                    : IDENT ASSIGN expression (PYCOMMA | )                                                  #letStatementAST;

//-------------------------------------------------------------------------------------------------------------------
returnStatement	                : expression (PYCOMMA | )                                                               #returnStatementAST;

//-------------------------------------------------------------------------------------------------------------------
expressionStatement             : expression (PYCOMMA | )                                                               #expressionStatementAST;

//-------------------------------------------------------------------------------------------------------------------
expression                      : additionExpression comparison                                                         #expressionAST;

//-------------------------------------------------------------------------------------------------------------------
comparison                      : ((LT|GT|LE|GE|EQUAL|NOT_EQUAL) additionExpression)*                                             #comparisonAST;

//-------------------------------------------------------------------------------------------------------------------
additionExpression	            : multiplicationExpression additionFactor                                               #additionExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
additionFactor                  : ((ADD|SUB) multiplicationExpression)*                                                 #additionFactorAST;

//-------------------------------------------------------------------------------------------------------------------
multiplicationExpression        : elementExpression multiplicationFactor                                                #multiplicationExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
multiplicationFactor            : ((MUL|DIV) elementExpression)*                                                        #multiplicationFactorAST;

//-------------------------------------------------------------------------------------------------------------------
elementExpression               : primitiveExpression (elementAccess | callExpression | )                               #elementExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
//[...]
elementAccess                   : L_BRACK expression R_BRACK                                                            #elementAccessAST;

//-------------------------------------------------------------------------------------------------------------------
//(...)
callExpression	                : L_PAREN expressionList R_PAREN                                                        #callExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
primitiveExpression	            : INTEGER                                                                               #primitiveExpression_numberAST
                                | STRING                                                                                #primitiveExpression_stringAST
                                | IDENT                                                                                 #primitiveExpression_identAST
                                | TRUE                                                                                  #primitiveExpression_trueAST
                                | FALSE                                                                                 #primitiveExpression_falseAST
                                | L_PAREN expression R_PAREN                                                            #primitiveExpression_expressionAST
                                | arrayLiteral                                                                          #primitiveExpression_literalAST
                                | arrayFunctions L_PAREN expressionList R_PAREN                                         #primitiveExpression_ArrayFunctionsAST
                                | functionLiteral                                                                       #primitiveExpression_FunctionLiteralAST
                                | hashLiteral                                                                           #primitiveExpression_HashLiteralAST
                                | printExpression                                                                       #primitiveExpression_PrintExpressionAST
                                | ifExpression                                                                          #primitiveExpression_IfExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
arrayFunctions	                : LEN                                                                                   #arrayFunctions_lenAST
                                | FIRST                                                                                 #arrayFunctions_firstAST
                                | LAST                                                                                  #arrayFunctions_lastAST
                                | REST                                                                                  #arrayFunctions_restAST
                                | PUSH                                                                                  #arrayFunctions_pushAST;

//-------------------------------------------------------------------------------------------------------------------
//[...]
arrayLiteral                    : L_BRACK expressionList R_BRACK                                                        #arrayLiteralAST;

//-------------------------------------------------------------------------------------------------------------------
//fn (...){...}
functionLiteral	                : FN L_PAREN functionParameters R_PAREN blockStatement                                  #functionLiteralAST;

//-------------------------------------------------------------------------------------------------------------------
//
functionParameters	            : IDENT (COMMA IDENT)* /*moreIdentifiers */                                             #functionParametersAST;

//-------------------------------------------------------------------------------------------------------------------
//moreIdentifiers	                : (COMMA IDENT)*                                                                        #moreIdentifiersAST;

//-------------------------------------------------------------------------------------------------------------------
//{...}
hashLiteral		                : L_BRACE hashContent  (COMMA hashContent)* /*moreHashContent*/ R_BRACE                 #hashLiteralAST;

//-------------------------------------------------------------------------------------------------------------------
//
hashContent	                    : expression COLON expression                                                           #hashContentAST;

//-------------------------------------------------------------------------------------------------------------------

//moreHashContent	                : (COMMA hashContent)*                                                                  #moreHashContentAST;

//-------------------------------------------------------------------------------------------------------------------
expressionList                  : expression (COMMA expression)* /*moreExpressions  */                                  #expressionList_expressionAST
                                |                                                                                       #expressionList_nullAST
                                ;

//-------------------------------------------------------------------------------------------------------------------
//moreExpressions                 : (COMMA expression)*                                                                   #moreExpressionsAST;

//-------------------------------------------------------------------------------------------------------------------
//puts ( ... )
printExpression                 : PUTS L_PAREN expression R_PAREN                                                       #printExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
//if
ifExpression	                : IF expression blockStatement (ELSE blockStatement| )                                  #ifExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
//{ ... }
blockStatement	                : L_BRACE statement* R_BRACE                                                            #blockStatementAST;

//-------------------------------------------------------------------------------------------------------------------