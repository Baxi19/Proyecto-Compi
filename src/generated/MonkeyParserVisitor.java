// Generated from C:/Users/Baxi/Desktop/Proyecto Compi\MonkeyParser.g4 by ANTLR 4.8
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MonkeyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MonkeyParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code programAST}
	 * labeled alternative in {@link MonkeyParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramAST(MonkeyParser.ProgramASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statement_LetAST}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_LetAST(MonkeyParser.Statement_LetASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statement_returnAST}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_returnAST(MonkeyParser.Statement_returnASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpressionStatementAST}
	 * labeled alternative in {@link MonkeyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpressionStatementAST(MonkeyParser.CallExpressionStatementASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAST}
	 * labeled alternative in {@link MonkeyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAST(MonkeyParser.ExpressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonAST}
	 * labeled alternative in {@link MonkeyParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonAST(MonkeyParser.ComparisonASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additionExpressionAST}
	 * labeled alternative in {@link MonkeyParser#additionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExpressionAST(MonkeyParser.AdditionExpressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationExpressionAST}
	 * labeled alternative in {@link MonkeyParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpressionAST(MonkeyParser.MultiplicationExpressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elementExpressionAST}
	 * labeled alternative in {@link MonkeyParser#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementExpressionAST(MonkeyParser.ElementExpressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_numberAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_numberAST(MonkeyParser.PrimitiveExpression_numberASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_stringAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_stringAST(MonkeyParser.PrimitiveExpression_stringASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_identAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_identAST(MonkeyParser.PrimitiveExpression_identASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_trueAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_trueAST(MonkeyParser.PrimitiveExpression_trueASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_falseAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_falseAST(MonkeyParser.PrimitiveExpression_falseASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_expressionAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_expressionAST(MonkeyParser.PrimitiveExpression_expressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_literalAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_literalAST(MonkeyParser.PrimitiveExpression_literalASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_ArrayFunctionsAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_ArrayFunctionsAST(MonkeyParser.PrimitiveExpression_ArrayFunctionsASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_FunctionLiteral_AST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_FunctionLiteral_AST(MonkeyParser.PrimitiveExpression_FunctionLiteral_ASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_HashLiteral_AST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_HashLiteral_AST(MonkeyParser.PrimitiveExpression_HashLiteral_ASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_PrintExpressionAST}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_PrintExpressionAST(MonkeyParser.PrimitiveExpression_PrintExpressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpression_IfExpression}
	 * labeled alternative in {@link MonkeyParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpression_IfExpression(MonkeyParser.PrimitiveExpression_IfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFunctions_lenAST}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFunctions_lenAST(MonkeyParser.ArrayFunctions_lenASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFunctions_firstAST}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFunctions_firstAST(MonkeyParser.ArrayFunctions_firstASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFunctions_lastAST}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFunctions_lastAST(MonkeyParser.ArrayFunctions_lastASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFunctions_restAST}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFunctions_restAST(MonkeyParser.ArrayFunctions_restASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayFunctions_pushAST}
	 * labeled alternative in {@link MonkeyParser#arrayFunctions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayFunctions_pushAST(MonkeyParser.ArrayFunctions_pushASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionList_expressionAST}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList_expressionAST(MonkeyParser.ExpressionList_expressionASTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionList_nullAST}
	 * labeled alternative in {@link MonkeyParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList_nullAST(MonkeyParser.ExpressionList_nullASTContext ctx);
}