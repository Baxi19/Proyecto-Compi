package codeGeneration;

import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;


import java.util.ArrayList;

public class CodeGenerator extends MonkeyParserBaseVisitor<Object> {

    private int index;
    private ArrayList<String> code;
    public int level;

    // Aux
    private MonkeyParser.LetStatementASTContext ctxLet;
    private boolean isLet;
    private boolean isReturn;
    private boolean isCall;


    //Constructor
    public CodeGenerator() {
        this.index=0;
        this.code= new ArrayList<>();
        this.level = -1;
        this.isLet = false;
        this.isReturn = false;
        this.isCall = false;
    }

    //Method to generate Monkey Virtual Machine code
    private void generate(int index, String instruction, Object param){
        if(param != null){
            this.code.add(index +" "+ instruction + " " +param);
        }else{
            this.code.add(index +" "+ instruction);
        }
        this.index++;
    }

    // Override Methods
    @Override
    public Object visitProgramAST(MonkeyParser.ProgramASTContext ctx) {
        level ++;
        for(int i = 0; i < ctx.statement().size();i++){
            visit(ctx.statement(i));
        }
        level --;
        System.out.println(toString());
        return null;
    }

    @Override
    public Object visitStatement_LetAST(MonkeyParser.Statement_LetASTContext ctx) {
        System.out.println("***********************************************************************************");
        System.out.println("LET : " + ctx.getText());
        makeAllFalse();
        isLet = true;
        visit(ctx.letStatement());
        return null;
    }

    @Override
    public Object visitStatement_returnAST(MonkeyParser.Statement_returnASTContext ctx) {
        System.out.println("***********************************************************************************");
        System.out.println("RETURN : ");
        makeAllFalse();
        isReturn = true;
        visit(ctx.returnStatement());
        return null;
    }

    @Override
    public Object visitCallExpressionStatementAST(MonkeyParser.CallExpressionStatementASTContext ctx) {
        System.out.println("***********************************************************************************");
        System.out.println("CALL : " + ctx.getText());
        makeAllFalse();
        isCall = true;
        visit(ctx.expressionStatement());
        return null;
    }

    @Override
    public Object visitLetStatementAST(MonkeyParser.LetStatementASTContext ctx) {
        level ++;

        //save the ctx as aux
        ctxLet = ctx;
        //TODO: get best order if this visit at the end, but the problem is Virtual Machine
        //visit(ctx.expression());

        // If is function
        if(ctx.getText().split("\\=")[1].startsWith("fn(")){
            if(ctx.IDENT().getText().toLowerCase().equals("main") && level == 1){
                System.out.println("**>MAIN<** IDENT: " +ctx.IDENT() +" Level: " + level +" => FN()");
            }else{
                //TODO: DEF ...</> ... END
                System.out.println("IDENT: " +ctx.IDENT() +" Level: " + level +" => FN()");
            }

        }
        // if is List
        else if(ctx.getText().split("\\=")[1].startsWith("[")){
            System.out.println("IDENT: " +ctx.IDENT() +" Level: " + level +" => []");
        }
        // if is hash
        else if(ctx.getText().split("\\=")[1].startsWith("{")){
            System.out.println("IDENT: " +ctx.IDENT() +" Level: " + level +" => {}");
        }
        // if is variable
        else{
            System.out.println("IDENT: " +ctx.IDENT() +" Level: " + level +" => var");
            if(level == 1){
                this.generate(this.index,"PUSH_GLOBAL_I",ctx.IDENT().getText());
            }else{
                this.generate(this.index,"PUSH_LOCAL_I",ctx.IDENT().getText());
            }

        }

        visit(ctx.expression());
        level --;
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
        return ctx.getText();
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
        if(ctx.additionFactor() != null){
            visit(ctx.additionFactor());
        }
        if(ctx.multiplicationExpression() != null){
            visit(ctx.multiplicationExpression());
        }
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
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_numberAST(MonkeyParser.PrimitiveExpression_numberASTContext ctx) {

        if(isLet){
            this.generate(this.index,"LOAD_CONST", ctx.INTEGER());
            this.generate(this.index,"STORE_FAST", ctxLet.IDENT().getText());
        }

        System.out.println("INT: " + ctx.INTEGER());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_stringAST(MonkeyParser.PrimitiveExpression_stringASTContext ctx) {
        System.out.println("STRING: " + ctx.STRING());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_identAST(MonkeyParser.PrimitiveExpression_identASTContext ctx) {
        System.out.println("ID: " + ctx.IDENT());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_trueAST(MonkeyParser.PrimitiveExpression_trueASTContext ctx) {
        System.out.println("TRUE: " + ctx.TRUE());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_falseAST(MonkeyParser.PrimitiveExpression_falseASTContext ctx) {
        System.out.println("FALSE: " + ctx.FALSE());
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
        System.out.println("Array: " + ctx.getText());
        if(ctx.expressionList()!=null){
            visit(ctx.expressionList());
        }
        return null;
    }

    @Override
    public Object visitFunctionLiteralAST(MonkeyParser.FunctionLiteralASTContext ctx) {
        //  Make Type FUNCTION, VM's Instructions
        if(ctx.functionParameters()!=null){
            visit(ctx.functionParameters());
        }
        if(ctx.blockStatement()!=null){
            visit(ctx.blockStatement());
        }
        return null;
    }

    @Override
    public Object visitFunctionParametersAST(MonkeyParser.FunctionParametersASTContext ctx) {
        System.out.println("Should call function with " +ctx.IDENT().size() + " parameters");
        for (int i = 0; i < ctx.IDENT().size(); i++) {
            System.out.println("Parameter: " +ctx.IDENT(i));
        }
        return null;
    }

    @Override
    public Object visitHashLiteralAST(MonkeyParser.HashLiteralASTContext ctx) {
        System.out.println("Hash size: " + ctx.hashContent().size());
        for(int i = 0; i < ctx.hashContent().size();i++){
            System.out.println("Data hash, index " + i + " : " + visit(ctx.hashContent(i)));
        }
        return null;
    }

    @Override
    public Object visitHashContentAST(MonkeyParser.HashContentASTContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitExpressionList_expressionAST(MonkeyParser.ExpressionList_expressionASTContext ctx) {
        System.out.println("Array size: " + ctx.expression().size());
        for (int i = 0; i < ctx.expression().size(); i++) {
            System.out.println("Data array, index " + i + " : " + visit(ctx.expression(i)));
        }

        return null;
    }

    @Override
    public Object visitExpressionList_nullAST(MonkeyParser.ExpressionList_nullASTContext ctx) {
        return null;
    }

    @Override
    public Object visitPrintExpressionAST(MonkeyParser.PrintExpressionASTContext ctx) {
        // Puts
        if(ctx.expression()!=null){
            visit(ctx.expression());
        }
        return null;
    }

    @Override
    public Object visitIfExpressionAST(MonkeyParser.IfExpressionASTContext ctx) {
        //  Make Type IF, VM's Instructions
        if(ctx.IF()!=null){

        }
        if(ctx.expression()!=null){
            visit(ctx.expression());
        }
        if(ctx.ELSE()!=null){

        }

        for(int i = 0; i < ctx.blockStatement().size();i++){
            visit(ctx.blockStatement(i));
        }
        return null;
    }

    @Override
    public Object visitBlockStatementAST(MonkeyParser.BlockStatementASTContext ctx) {
        // {... </> ...}
        for(int i = 0; i < ctx.statement().size();i++){
            visit(ctx.statement(i));
        }
        return null;
    }

    //Method To String
    @Override
    public String toString() {
        String data = "";
        for (int i = 0; i < code.size() ; i++) {
            data += code.get(i) + "\n";
        }
        return data;
    }

    public void makeAllFalse(){
        isLet = false;
        isCall = false;
        isReturn = false;
    }

}
