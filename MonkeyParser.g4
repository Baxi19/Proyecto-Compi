//-------------------------------------------------------------------------------------------------------------------
//                                    Parser
parser grammar MonkeyParser;

options {
    tokenVocab = MonkeyScanner;
}

//-------------------------------------------------------------------------------------------------------------------
//                               Grammar for Monkey Language
//-------------------------------------------------------------------------------------------------------------------
program  	                    : statement*                                                                            #programAST;

//-------------------------------------------------------------------------------------------------------------------
statement  	                    : LET IDENT ASSIGN expression (PYCOMMA | )/*letStatement*/                              #statement_LetAST
                                | RETURN expression (PYCOMMA | ) /*returnStatement*/                                    #statement_returnAST
                                | expression (PYCOMMA | ) /*expressionStatement */                                      #callExpressionStatementAST;

//-------------------------------------------------------------------------------------------------------------------
expression                      : additionExpression  comparison                                                        #expressionAST;

//-------------------------------------------------------------------------------------------------------------------
comparison                      : ((LT|GT|LE|GE|EQUAL) additionExpression)*                                             #comparisonAST;

//-------------------------------------------------------------------------------------------------------------------
additionExpression	            : multiplicationExpression ((ADD|SUB) multiplicationExpression)*/*additionFactor*/      #additionExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
multiplicationExpression        : elementExpression ((MUL|DIV) elementExpression)*/*multiplicationFactor*/              #multiplicationExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
elementExpression               : primitiveExpression (L_BRACK  expression  R_BRACK|L_PAREN expressionList R_PAREN | )  #elementExpressionAST;

//-------------------------------------------------------------------------------------------------------------------
primitiveExpression	            : INTEGER                                                                               #primitiveExpression_numberAST
                                | STRING                                                                                #primitiveExpression_stringAST
                                | IDENT                                                                                 #primitiveExpression_identAST
                                | TRUE                                                                                  #primitiveExpression_trueAST
                                | FALSE                                                                                 #primitiveExpression_falseAST
                                | L_PAREN expression R_PAREN                                                            #primitiveExpression_expressionAST
                                | L_BRACK expressionList R_BRACK/*arrayLiteral*/                                        #primitiveExpression_literalAST
                                | arrayFunctions L_PAREN expressionList R_PAREN                                         #primitiveExpression_ArrayFunctionsAST
                                | FN L_PAREN IDENT (COMMA IDENT)* R_PAREN L_BRACE statement* R_BRACE                    #primitiveExpression_FunctionLiteral_AST
                                | L_BRACE expression COLON expression  (COMMA expression COLON expression)*             #primitiveExpression_HashLiteral_AST
                                | PUTS L_PAREN expression R_PAREN/*printExpression*/                                    #primitiveExpression_PrintExpressionAST
                                | IF expression L_BRACE statement* R_BRACE (ELSE L_BRACE statement* R_BRACE| )          #primitiveExpression_IfExpression;

//-------------------------------------------------------------------------------------------------------------------
arrayFunctions	                : LEN                                                                                   #arrayFunctions_lenAST
                                | FIRST                                                                                 #arrayFunctions_firstAST
                                | LAST                                                                                  #arrayFunctions_lastAST
                                | REST                                                                                  #arrayFunctions_restAST
                                | PUSH                                                                                  #arrayFunctions_pushAST;

//-------------------------------------------------------------------------------------------------------------------
expressionList                  : expression (COMMA expression)* /*moreExpressions  */                                  #expressionList_expressionAST
                                |                                                                                       #expressionList_nullAST
                                ;