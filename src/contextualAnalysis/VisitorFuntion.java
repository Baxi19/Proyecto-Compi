package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.TYPE;

import java.util.ArrayList;


//Visitor, Used by Save the simbols table
public class VisitorFuntion extends MonkeyParserBaseVisitor<Object> {
    public int level =1;

    @Override
    public Object visitProgramAST(MonkeyParser.ProgramASTContext ctx) {
        //TODO: Clean the values used by visitors
        IDLE.getInstance().tableId = 0;
        IDLE.getInstance().tablaSimbolos = new SymbolTable();
        IDLE.getInstance().parameters = new ArrayList<>();
        IDLE.getInstance().paramenter = 0;

        for(int i = 0; i < ctx.statement().size();i++){
            visit(ctx.statement(i));
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
        IDLE.getInstance().existReturn = true;
        visit(ctx.returnStatement());
        return null;
    }

    @Override
    public Object visitCallExpressionStatementAST(MonkeyParser.CallExpressionStatementASTContext ctx) {
        visit(ctx.expressionStatement());
        return null;
    }

    @Override
    public Object visitLetStatementAST(MonkeyParser.LetStatementASTContext ctx) {
        IDLE.getInstance().existReturn = false;
        level++;
        visit(ctx.expression());

        IDLE.getInstance().tablaSimbolos.rewrite(ctx.IDENT(), level); // TODO: Metodo para sobre escribir idents si se vuelven a asignar con let
        if(ctx.getText().split("\\=")[1].startsWith("fn(")){
            IDLE.getInstance().tablaSimbolos.insertMet(ctx.IDENT(), TYPE.FUNCTION, level, ctx);
        }
        level--;
        return null;
    }

    @Override
    public Object visitReturnStatementAST(MonkeyParser.ReturnStatementASTContext ctx) {
        //return
        visit(ctx.expression());
        IDLE.getInstance().returnStatement = ctx.expression().getText();
        return ctx.expression();
    }

    @Override
    public Object visitExpressionStatementAST(MonkeyParser.ExpressionStatementASTContext ctx) {
        visit(ctx.expression());
        //TODO: Call expresion
        //System.out.println("Expresion: " + ctx.expression().getText());
        return null;
    }

    @Override
    public Object visitExpressionAST(MonkeyParser.ExpressionASTContext ctx) {
        visit(ctx.additionExpression());
        visit(ctx.comparison());
        return null;
    }

    @Override
    public Object visitComparisonAST(MonkeyParser.ComparisonASTContext ctx) {
        for(int i = 0; i < ctx.additionExpression().size();i++){
            visit(ctx.additionExpression(i));
        }
        return null;
    }

    @Override
    public Object visitAdditionExpressionAST(MonkeyParser.AdditionExpressionASTContext ctx) {
        visit(ctx.additionFactor());
        visit(ctx.multiplicationExpression());
        return null;
    }

    @Override
    public Object visitAdditionFactorAST(MonkeyParser.AdditionFactorASTContext ctx) {
        for(int i = 0; i < ctx.multiplicationExpression().size();i++){
            visit(ctx.multiplicationExpression(i));
        }
        return null;
    }

    @Override
    public Object visitMultiplicationExpressionAST(MonkeyParser.MultiplicationExpressionASTContext ctx) {
        if(ctx.elementExpression()!=null){
            visit(ctx.elementExpression());
        }
        if(ctx.multiplicationFactor()!=null){
            visit(ctx.multiplicationFactor());
        }
        return null;
    }

    @Override
    public Object visitMultiplicationFactorAST(MonkeyParser.MultiplicationFactorASTContext ctx) {
        for(int i = 0; i < ctx.elementExpression().size();i++){
            visit(ctx.elementExpression(i));
        }
        return null;
    }

    @Override
    public Object visitElementExpressionAST(MonkeyParser.ElementExpressionASTContext ctx) {
        if(ctx.primitiveExpression()!=null){
            visit(ctx.primitiveExpression());
        }
        if(ctx.elementAccess()!=null){
            visit(ctx.elementAccess());
        }
        if(ctx.callExpression()!=null){
            visit(ctx.callExpression());
            //TODO: check funtion parameter if are equal at declared
            //System.out.println("-->" + ctx.callExpression().getText());

        }
        return null;
    }

    @Override
    public Object visitElementAccessAST(MonkeyParser.ElementAccessASTContext ctx) {
        if(ctx.expression()!=null){
            visit(ctx.expression());
        }
        return null;
    }

    @Override
    public Object visitCallExpressionAST(MonkeyParser.CallExpressionASTContext ctx) {
        //Get the parameter in every call
        visit(ctx.expressionList());
        IDLE.getInstance().callWith = ctx.expressionList().getText();
        //System.out.println("Save: " + IDLE.getInstance().callWith);
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_numberAST(MonkeyParser.PrimitiveExpression_numberASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_stringAST(MonkeyParser.PrimitiveExpression_stringASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_identAST(MonkeyParser.PrimitiveExpression_identASTContext ctx) {
        //TODO: Meter checkeo si existe funcion aqui desde visitor Variable por el closeScope
        //System.out.println(ctx.IDENT().getText());
        /*if(IDLE.getInstance().tablaSimbolos.search(ctx.IDENT(), TYPE.FUNCTION, level) == null){
            IDLE.getInstance().errorsContextual.add(
                    new Error(ctx.IDENT().getSymbol().getLine(),
                            ctx.IDENT().getSymbol().getCharPositionInLine(),"Undefined  " +ctx.IDENT().getText() + " ", "SINTAX ERROR  "));
        }*/
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
        if(ctx.expression()!=null){
            visit(ctx.expression());
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_literalAST(MonkeyParser.PrimitiveExpression_literalASTContext ctx) {
        visit(ctx.arrayLiteral());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_ArrayFunctionsAST(MonkeyParser.PrimitiveExpression_ArrayFunctionsASTContext ctx) {
        if(ctx.arrayFunctions()!=null){
            visit(ctx.arrayFunctions());
        }
        if(ctx.expressionList()!=null){
            visit(ctx.expressionList());
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_FunctionLiteralAST(MonkeyParser.PrimitiveExpression_FunctionLiteralASTContext ctx) {
        visit(ctx.functionLiteral());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_HashLiteralAST(MonkeyParser.PrimitiveExpression_HashLiteralASTContext ctx) {
        visit(ctx.hashLiteral());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_PrintExpressionAST(MonkeyParser.PrimitiveExpression_PrintExpressionASTContext ctx) {
        visit(ctx.printExpression());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_IfExpressionAST(MonkeyParser.PrimitiveExpression_IfExpressionASTContext ctx) {
        visit(ctx.ifExpression());
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
        if(ctx.expressionList()!=null){
            visit(ctx.expressionList());
        }
        return null;
    }

    @Override
    public Object visitFunctionLiteralAST(MonkeyParser.FunctionLiteralASTContext ctx) {

        if(ctx.functionParameters()!=null){
            visit(ctx.functionParameters());
           // System.out.println(">>"+ctx.functionParameters().getText());

        }
        if(ctx.blockStatement()!=null){
            visit(ctx.blockStatement());
        }
        //TODO: Llamar closeScope personalizado y level ?
        return null;
    }

    @Override
    public Object visitFunctionParametersAST(MonkeyParser.FunctionParametersASTContext ctx) {
        //Save the parameters
        ctx.IDENT();
        IDLE.getInstance().parameters = ( ArrayList<TerminalNode> ) ctx.IDENT();
        return null;
    }

    @Override
    public Object visitHashLiteralAST(MonkeyParser.HashLiteralASTContext ctx) {
        for(int i = 0; i < ctx.hashContent().size();i++){
            visit(ctx.hashContent(i));
        }
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
        if(ctx.expression()!=null){
            visit(ctx.expression());
        }
        return null;
    }

    @Override
    public Object visitIfExpressionAST(MonkeyParser.IfExpressionASTContext ctx) {

        if(ctx.IF()!=null){

        }
        if(ctx.ELSE()!=null){

        }
        if(ctx.expression()!=null){
            visit(ctx.expression());
        }
        for(int i = 0; i < ctx.blockStatement().size();i++){
            visit(ctx.blockStatement(i));
        }
        return null;
    }

    @Override
    public Object visitBlockStatementAST(MonkeyParser.BlockStatementASTContext ctx) {
        for(int i = 0; i < ctx.statement().size();i++){
            visit(ctx.statement(i));
            //TODO: Llamar closeScope personalizado
        }
        return null;
    }

}
