package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;
import utils.TYPE;


//Visitor, Used by Save the simbols table
public class VisitorVariable extends MonkeyParserBaseVisitor<Object> {
    public int level =1;

    @Override
    public Object visitProgramAST(MonkeyParser.ProgramASTContext ctx) {
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
        visit(ctx.expression());
        //if is  NOT a  Funtion
        String[] parts = ctx.getText().split("\\=");
        if(!parts[1].startsWith("fn(")){
            //TODO: call  function declared ejp: let x = fib(5); also check parameters number

            //List
            if(parts[1].startsWith("[")){
                if(IDLE.getInstance().checkFirstParameter(parts[1])){
                    IDLE.getInstance().tablaSimbolos.insertHashContent(ctx.IDENT(), TYPE.HASHCONTENT, level, ctx);
                }else{
                    IDLE.getInstance().errorsContextual.add(
                            new Error(ctx.IDENT().getSymbol().getLine(),
                                    ctx.IDENT().getSymbol().getCharPositionInLine(),"Hash Content only supports (INT or STRING) in first position, in : '" +ctx.IDENT().getText() + " ", "SINTAX ERROR  "));
                }
            }

            //Hash
            else if(parts[1].startsWith("{")){
                IDLE.getInstance().tablaSimbolos.insertHashLiteral(ctx.IDENT(), TYPE.HASHLITERAL, level, ctx);
            }
            //Variable
            else {
                IDLE.getInstance().tablaSimbolos.insertVar(ctx.IDENT(), TYPE.VARIABLE, level, ctx);
            }
        }
        return null;
    }

    @Override
    public Object visitReturnStatementAST(MonkeyParser.ReturnStatementASTContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitExpressionStatementAST(MonkeyParser.ExpressionStatementASTContext ctx) {
        visit(ctx.expression());

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
        for(int i = 0; i < ctx.LT().size();i++){

        }
        for(int i = 0; i < ctx.GT().size();i++){

        }
        for(int i = 0; i < ctx.LE().size();i++){

        }
        for(int i = 0; i < ctx.GE().size();i++){

        }
        for(int i = 0; i < ctx.EQUAL().size();i++){

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
        for(int i = 0; i < ctx.ADD().size();i++){

        }
        for(int i = 0; i < ctx.SUB().size();i++){

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
        for(int i = 0; i < ctx.MUL().size();i++){

        }
        for(int i = 0; i < ctx.DIV().size();i++){

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
        if(ctx.expressionList()!=null){
            visit(ctx.expressionList());
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_numberAST(MonkeyParser.PrimitiveExpression_numberASTContext ctx) {
        return ctx.INTEGER();
    }

    @Override
    public Object visitPrimitiveExpression_stringAST(MonkeyParser.PrimitiveExpression_stringASTContext ctx) {
        return ctx.STRING();
    }

    @Override
    public Object visitPrimitiveExpression_identAST(MonkeyParser.PrimitiveExpression_identASTContext ctx) {
        //TODO: DEBUG: To Check Method, to verify if exist to **rewrite**
        boolean function  = false;
        boolean  parameter = false;
        boolean  variable = false;
        boolean  list  = false;
        boolean  hash = false;
        /*if(IDLE.getInstance().tablaSimbolos.search(ctx.IDENT(), TYPE.FUNCTION, level) == null){
            IDLE.getInstance().errorsContextual.add(
                    new Error(ctx.IDENT().getSymbol().getLine(),
                            ctx.IDENT().getSymbol().getCharPositionInLine(),"Undefined  " +ctx.IDENT().getText() + " ", "SINTAX ERROR  "));
        }*/

        if(IDLE.getInstance().tablaSimbolos.search(ctx.IDENT(), TYPE.FUNCTION, level) != null){
            function = true;
        }
        if(IDLE.getInstance().tablaSimbolos.search(ctx.IDENT(), TYPE.PARAMETER, level) != null){
            parameter = true;
        }
        if(IDLE.getInstance().tablaSimbolos.search(ctx.IDENT(), TYPE.VARIABLE, level) != null){
            variable = true;
        }
        if(IDLE.getInstance().tablaSimbolos.search(ctx.IDENT(), TYPE.HASHCONTENT, level) != null){
            list = true;
        }
        if(IDLE.getInstance().tablaSimbolos.search(ctx.IDENT(), TYPE.HASHLITERAL, level) != null){
            hash = true;
        }
        if(!(function || parameter || variable || list || hash)){
            IDLE.getInstance().errorsContextual.add(
                    new Error(ctx.IDENT().getSymbol().getLine(),
                            ctx.IDENT().getSymbol().getCharPositionInLine(),"Undefined  " +ctx.IDENT().getText() + " ", "SINTAX ERROR  "));
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_trueAST(MonkeyParser.PrimitiveExpression_trueASTContext ctx) {
        return ctx.TRUE();
    }

    @Override
    public Object visitPrimitiveExpression_falseAST(MonkeyParser.PrimitiveExpression_falseASTContext ctx) {
        return ctx.FALSE();
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
        level++;
        if(ctx.functionParameters()!=null){
            visit(ctx.functionParameters());
        }
        if(ctx.blockStatement()!=null){
            visit(ctx.blockStatement());
        }
        level--;
        return null;
    }

    @Override
    public Object visitFunctionParametersAST(MonkeyParser.FunctionParametersASTContext ctx) {
        return ctx.IDENT().size();
    }

    @Override
    public Object visitHashLiteralAST(MonkeyParser.HashLiteralASTContext ctx) {
        for(int i = 0; i < ctx.hashContent().size();i++){
            visit(ctx.hashContent(i));
        }
        for(int i = 0; i < ctx.COMMA().size();i++){

        }
        return null;
    }

    @Override
    public Object visitHashContentAST(MonkeyParser.HashContentASTContext ctx) {
        for(int i = 0; i < ctx.expression().size();i++){

        }
        return null;
    }

    @Override
    public Object visitExpressionList_expressionAST(MonkeyParser.ExpressionList_expressionASTContext ctx) {

        for(int i = 0; i < ctx.expression().size();i++){

        }
        for(int i = 0; i < ctx.COMMA().size();i++){

        }
        return null;
    }

    @Override
    public Object visitExpressionList_nullAST(MonkeyParser.ExpressionList_nullASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrintExpressionAST(MonkeyParser.PrintExpressionASTContext ctx) {

        visit(ctx.expression());

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
            //level++;
            visit(ctx.statement(i));
            //level--;
        }
        return null;
    }

}
