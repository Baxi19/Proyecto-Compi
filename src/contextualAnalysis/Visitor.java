package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;


//Visitor, Used by Save the simbols table
public class Visitor extends MonkeyParserBaseVisitor<Object> {
    public int level =1;

    @Override
    public Object visitProgramAST(MonkeyParser.ProgramASTContext ctx) {
        IDLE.getInstance().tablaSimbolos = new SymbolTable();
        for(int i = 0; i < ctx.statement().size();i++){
            //Tabs++;
            visit(ctx.statement(i));
            //Tabs--;
        }
        System.out.println(IDLE.getInstance().tablaSimbolos.imprimir());
        return null;
    }

    @Override
    public Object visitStatement_LetAST(MonkeyParser.Statement_LetASTContext ctx) {
        //Tabs++;
        visit(ctx.letStatement());
        //Tabs--;
        return null;
    }

    @Override
    public Object visitStatement_returnAST(MonkeyParser.Statement_returnASTContext ctx) {
        //Tabs++;
        visit(ctx.returnStatement());
        //Tabs--;
        return null;
    }

    @Override
    public Object visitCallExpressionStatementAST(MonkeyParser.CallExpressionStatementASTContext ctx) {
        //Tabs++;
        visit(ctx.expressionStatement());
        //Tabs--;
        return null;
    }

    @Override
    public Object visitLetStatementAST(MonkeyParser.LetStatementASTContext ctx) {

        //Tabs++;
        visit(ctx.expression());
        //Tabs--;

        IDLE.getInstance().tablaSimbolos.insertar(ctx.IDENT().getSymbol(), level, ctx);
        //IDLE.getInstance().table.add(new Variable(ctx.IDENT().getSymbol(),1,ctx ));
        return null;
    }

    @Override
    public Object visitReturnStatementAST(MonkeyParser.ReturnStatementASTContext ctx) {
        //Tabs++;
        visit(ctx.expression());
        //Tabs--;

        return null;
    }

    @Override
    public Object visitExpressionStatementAST(MonkeyParser.ExpressionStatementASTContext ctx) {

        if(ctx.expression()!=null){
            //Tabs++;
            visit(ctx.expression());
            //Tabs--;
        }
        if(ctx.PYCOMMA()!=null){

        }
        return null;
    }

    @Override
    public Object visitExpressionAST(MonkeyParser.ExpressionASTContext ctx) {

        if(ctx.additionExpression()!=null){
            //Tabs++;
            visit(ctx.additionExpression());
            //Tabs--;
        }
        if(ctx.comparison()!=null){
           // Tabs++;
            visit(ctx.comparison());
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitComparisonAST(MonkeyParser.ComparisonASTContext ctx) {
        //VisitorTree.add("\n" + Tabs(Tabs)+" Comparison ");
        for(int i = 0; i < ctx.additionExpression().size();i++){
            //Tabs++;
            visit(ctx.additionExpression(i));
            //Tabs--;
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
        if(ctx.additionFactor()!=null){
            //Tabs++;
            visit(ctx.additionFactor());
            //Tabs--;
        }
        if(ctx.multiplicationExpression()!=null){
            //Tabs++;
            visit(ctx.multiplicationExpression());
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitAdditionFactorAST(MonkeyParser.AdditionFactorASTContext ctx) {
        for(int i = 0; i < ctx.multiplicationExpression().size();i++){
            //Tabs++;
            visit(ctx.multiplicationExpression(i));
            //Tabs--;
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
            //Tabs++;
            visit(ctx.elementExpression());
           // Tabs--;
        }
        if(ctx.multiplicationFactor()!=null){
            //Tabs++;
            visit(ctx.multiplicationFactor());
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitMultiplicationFactorAST(MonkeyParser.MultiplicationFactorASTContext ctx) {

        for(int i = 0; i < ctx.elementExpression().size();i++){
            //Tabs++;
            visit(ctx.elementExpression(i));
            //Tabs--;
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
            //Tabs++;
            visit(ctx.primitiveExpression());
            //Tabs--;
        }
        if(ctx.elementAccess()!=null){
            //Tabs++;
            visit(ctx.elementAccess());
            //Tabs--;
        }
        if(ctx.callExpression()!=null){
            //Tabs++;
            visit(ctx.callExpression());
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitElementAccessAST(MonkeyParser.ElementAccessASTContext ctx) {

        if(ctx.L_BRACK()!=null){

        }
        if(ctx.R_BRACK()!=null){

        }
        if(ctx.expression()!=null){
            //Tabs++;
            visit(ctx.expression());
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitCallExpressionAST(MonkeyParser.CallExpressionASTContext ctx) {

        if(ctx.L_PAREN()!=null){

        }
        if(ctx.R_PAREN()!=null){

        }
        if(ctx.expressionList()!=null){
            //Tabs++;
            visit(ctx.expressionList());
            //Tabs--;
        }
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
        if(IDLE.getInstance().tablaSimbolos.search(ctx.IDENT().getText()) == null){
            IDLE.getInstance().errorsContextual.add(
                    new Error(ctx.IDENT().getSymbol().getLine(),
                            ctx.IDENT().getSymbol().getCharPositionInLine(),"Undefined Variable '" +ctx.IDENT().getText() + "' ", "CONTEXT"));

        }
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
        if(ctx.L_PAREN()!=null){

        }
        if(ctx.R_PAREN()!=null){

        }
        if(ctx.expression()!=null){
            //Tabs++;
            visit(ctx.expression());
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_literalAST(MonkeyParser.PrimitiveExpression_literalASTContext ctx) {

        //Tabs++;
        visit(ctx.arrayLiteral());
        //Tabs--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_ArrayFunctionsAST(MonkeyParser.PrimitiveExpression_ArrayFunctionsASTContext ctx) {
        if(ctx.L_PAREN()!=null){

        }
        if(ctx.R_PAREN()!=null){

        }
        if(ctx.arrayFunctions()!=null){
            //Tabs++;
            visit(ctx.arrayFunctions());
            //Tabs--;
        }
        if(ctx.expressionList()!=null){
            //Tabs++;
            visit(ctx.expressionList());
           // Tabs--;
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_FunctionLiteralAST(MonkeyParser.PrimitiveExpression_FunctionLiteralASTContext ctx) {
        //Tabs++;
        visit(ctx.functionLiteral());
        //Tabs--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_HashLiteralAST(MonkeyParser.PrimitiveExpression_HashLiteralASTContext ctx) {
        //Tabs++;
        visit(ctx.hashLiteral());
        //Tabs--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_PrintExpressionAST(MonkeyParser.PrimitiveExpression_PrintExpressionASTContext ctx) {
       // Tabs++;
        visit(ctx.printExpression());
       // Tabs--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_IfExpressionAST(MonkeyParser.PrimitiveExpression_IfExpressionASTContext ctx) {
        //Tabs++;
        visit(ctx.ifExpression());
        //Tabs--;
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
        if(ctx.L_BRACK()!=null){

        }
        if(ctx.R_BRACK()!=null){

        }
        if(ctx.expressionList()!=null){
            //Tabs++;
            visit(ctx.expressionList());
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitFunctionLiteralAST(MonkeyParser.FunctionLiteralASTContext ctx) {

        if(ctx.FN()!=null){

        }
        if(ctx.L_PAREN()!=null){

        }
        if(ctx.R_PAREN()!=null){

        }
        if(ctx.functionParameters()!=null){
            //Tabs++;
            visit(ctx.functionParameters());
            //Tabs--;
        }
        if(ctx.blockStatement()!=null){
            //Tabs++;
            visit(ctx.blockStatement());
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitFunctionParametersAST(MonkeyParser.FunctionParametersASTContext ctx) {

        for(int i = 0; i < ctx.IDENT().size();i++){

        }
        for(int i = 0; i < ctx.COMMA().size();i++){

        }
        return null;
    }

    @Override
    public Object visitHashLiteralAST(MonkeyParser.HashLiteralASTContext ctx) {

        for(int i = 0; i < ctx.hashContent().size();i++){
            //Tabs++;
            visit(ctx.hashContent(i));
            //Tabs--;
        }
        for(int i = 0; i < ctx.COMMA().size();i++){

        }
        if(ctx.L_BRACE()!=null){

        }
        return null;
    }

    @Override
    public Object visitHashContentAST(MonkeyParser.HashContentASTContext ctx) {

        if(ctx.COLON()!=null){

        }
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

        if(ctx.PUTS()!=null){

        }
        if(ctx.L_PAREN()!=null){

        }
        if(ctx.R_PAREN()!=null){

        }
        if(ctx.expression()!=null){
            //Tabs++;
            visit(ctx.expression());
            //Tabs--;
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
            //Tabs++;
            visit(ctx.expression());
            //Tabs--;
        }
        for(int i = 0; i < ctx.blockStatement().size();i++){
            //Tabs++;
            visit(ctx.blockStatement(i));
            //Tabs--;
        }
        return null;
    }

    @Override
    public Object visitBlockStatementAST(MonkeyParser.BlockStatementASTContext ctx) {
        for(int i = 0; i < ctx.statement().size();i++){
            level++;
            visit(ctx.statement(i));
            level--;
        }
        return null;
    }

}
