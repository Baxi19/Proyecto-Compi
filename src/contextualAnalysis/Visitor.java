package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;

//tree.Visitor, Used by Save the simbols table
public class Visitor extends MonkeyParserBaseVisitor<Object> {
    private int level=-1;
    private SymbolTable symbolTable = new SymbolTable();

    @Override
    public Object visitProgramAST(MonkeyParser.ProgramASTContext ctx) {
        for(int i = 0; i < ctx.statement().size();i++){
            level++;
            visit(ctx.statement(i));
            level--;
        }
        return null;
    }

    @Override
    public Object visitStatement_LetAST(MonkeyParser.Statement_LetASTContext ctx) {
        visit(ctx.letStatement());
        return null;
    }

    @Override
    public Object visitStatement_returnAST(MonkeyParser.Statement_returnASTContext ctx) {
        return null;
    }

    @Override
    public Object visitCallExpressionStatementAST(MonkeyParser.CallExpressionStatementASTContext ctx) {
        return null;
    }

    @Override
    public Object visitLetStatementAST(MonkeyParser.LetStatementASTContext ctx) {
        String name = ctx.IDENT.getText();
        //type will be used to save in table
        int type = ( int ) visit(ctx.expression());
        SymbolTable.Ident ident = symbolTable.search(name);
        //TODO: Finish If
        if(ident != null){
            System.out.println("Variable has been already declared");
        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(ctx.IDENT.getLine(), ctx.IDENT.getCharPositionInLine(),
                            "Variable '" + name + "' has not been declared in current context", "CONTEXT ERROR"));
        }
        return null;
    }

    @Override
    public Object visitReturnStatementAST(MonkeyParser.ReturnStatementASTContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionStatementAST(MonkeyParser.ExpressionStatementASTContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionAST(MonkeyParser.ExpressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitComparisonAST(MonkeyParser.ComparisonASTContext ctx) {
        return null;
    }

    @Override
    public Object visitAdditionExpressionAST(MonkeyParser.AdditionExpressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitAdditionFactorAST(MonkeyParser.AdditionFactorASTContext ctx) {
        return null;
    }

    @Override
    public Object visitMultiplicationExpressionAST(MonkeyParser.MultiplicationExpressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitMultiplicationFactorAST(MonkeyParser.MultiplicationFactorASTContext ctx) {
        return null;
    }

    @Override
    public Object visitElementExpressionAST(MonkeyParser.ElementExpressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitElementAccessAST(MonkeyParser.ElementAccessASTContext ctx) {
        return null;
    }

    @Override
    public Object visitCallExpressionAST(MonkeyParser.CallExpressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_numberAST(MonkeyParser.PrimitiveExpression_numberASTContext ctx) {
        return 0;
    }

    @Override
    public Object visitPrimitiveExpression_stringAST(MonkeyParser.PrimitiveExpression_stringASTContext ctx) {
        return 1;
    }

    @Override
    public Object visitPrimitiveExpression_identAST(MonkeyParser.PrimitiveExpression_identASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_trueAST(MonkeyParser.PrimitiveExpression_trueASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_falseAST(MonkeyParser.PrimitiveExpression_falseASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_expressionAST(MonkeyParser.PrimitiveExpression_expressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_literalAST(MonkeyParser.PrimitiveExpression_literalASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_ArrayFunctionsAST(MonkeyParser.PrimitiveExpression_ArrayFunctionsASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_FunctionLiteralAST(MonkeyParser.PrimitiveExpression_FunctionLiteralASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_HashLiteralAST(MonkeyParser.PrimitiveExpression_HashLiteralASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_PrintExpressionAST(MonkeyParser.PrimitiveExpression_PrintExpressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_IfExpressionAST(MonkeyParser.PrimitiveExpression_IfExpressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitArrayFunctions_lenAST(MonkeyParser.ArrayFunctions_lenASTContext ctx) {
        return null;
    }

    @Override
    public Object visitArrayFunctions_firstAST(MonkeyParser.ArrayFunctions_firstASTContext ctx) {
        return null;
    }

    @Override
    public Object visitArrayFunctions_lastAST(MonkeyParser.ArrayFunctions_lastASTContext ctx) {
        return null;
    }

    @Override
    public Object visitArrayFunctions_restAST(MonkeyParser.ArrayFunctions_restASTContext ctx) {
        return null;
    }

    @Override
    public Object visitArrayFunctions_pushAST(MonkeyParser.ArrayFunctions_pushASTContext ctx) {
        return null;
    }

    @Override
    public Object visitArrayLiteralAST(MonkeyParser.ArrayLiteralASTContext ctx) {
        return null;
    }

    @Override
    public Object visitFunctionLiteralAST(MonkeyParser.FunctionLiteralASTContext ctx){
        return null;
    }

    @Override
    public Object visitFunctionParametersAST(MonkeyParser.FunctionParametersASTContext ctx) {
        return null;
    }

    @Override
    public Object visitHashLiteralAST(MonkeyParser.HashLiteralASTContext ctx) {
        return null;
    }

    @Override
    public Object visitHashContentAST(MonkeyParser.HashContentASTContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionList_expressionAST(MonkeyParser.ExpressionList_expressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitExpressionList_nullAST(MonkeyParser.ExpressionList_nullASTContext ctx) {
        return null;
    }


    @Override
    public Object visitPrintExpressionAST(MonkeyParser.PrintExpressionASTContext ctx) {
        return null;
    }


    @Override
    public Object visitIfExpressionAST(MonkeyParser.IfExpressionASTContext ctx) {
        return null;
    }

    @Override
    public Object visitBlockStatementAST(MonkeyParser.BlockStatementASTContext ctx) {
        return null;
    }
}
