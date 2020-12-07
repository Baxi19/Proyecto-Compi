package codeGeneration;

import backend.IDLE;
import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;

import java.util.ArrayList;

public class CodeVM extends MonkeyParserBaseVisitor<Object> {

    private int letmain;
    private TablaSimbolos tablaIDS;

    private int index;
    private ArrayList<String> code;
    public int level;

    // Aux
    private MonkeyParser.LetStatementASTContext ctxLet;
    private boolean isLet;
    private boolean isReturn;
    private boolean isCall;

    private boolean isVar;
    private boolean isFunct;
    private boolean isList;
    private boolean isHash;

    //Constructor
    public CodeVM() {
        IDLE.getInstance().instructions = "";
        this.letmain=-1;
        this.tablaIDS = new TablaSimbolos();

        this.index=0;
        this.code= new ArrayList<>();
        this.level = -1;

        this.isLet = false;
        this.isReturn = false;
        this.isCall = false;

        this.isVar = false;
        this.isFunct = false;
        this.isList = false;
        this.isHash = false;
    }

    @Override
    public Object visitProgramAST(MonkeyParser.ProgramASTContext ctx) {
        // Program
        for(int i = 0; i < ctx.statement().size();i++){
            visit(ctx.statement(i));
        }


        IDLE.getInstance().instructions = toString();
        return null;
    }

    @Override
    public Object visitStatement_LetAST(MonkeyParser.Statement_LetASTContext ctx) {
        // Let
        makeAllFalse();
        isLet = true;

        visit(ctx.letStatement());
        return null;
    }

    @Override
    public Object visitStatement_returnAST(MonkeyParser.Statement_returnASTContext ctx) {
        // Return
        makeAllFalse();
        isReturn = true;

        visit(ctx.returnStatement());
        //this.generate(this.index,"RETURN_VALUE",null);
        return null;
    }

    @Override
    public Object visitCallExpressionStatementAST(MonkeyParser.CallExpressionStatementASTContext ctx) {
        // Call expressions
        makeAllFalse();
        isCall = true;

        visit(ctx.expressionStatement());
        return null;
    }

    @Override
    public Object visitLetStatementAST(MonkeyParser.LetStatementASTContext ctx) {
        // Let Statement, Here will check the type
        level ++;
        forgetType();
        ctxLet = ctx; //save the ctx as aux



        // If is function
        if(ctx.getText().split("\\=")[1].startsWith("fn(")){
            isFunct = true;
            if(ctx.IDENT().getText().toLowerCase().equals("main") && level == 0){
                this.generate(this.index,"DEF Main", null);
                //System.out.println("**>MAIN<** IDENT: " +ctx.IDENT() +" Level: " + level +" => FN()");
            }else{
                this.generate(this.index,"DEF", ctx.IDENT());
                //TODO: DEF ...</> ... END
                //System.out.println("IDENT: " +ctx.IDENT() +" Level: " + level +" => FN()");
            }

        }
        // if is List
        else if(ctx.getText().split("\\=")[1].startsWith("[")){
            isList = true;
            //System.out.println("IDENT: " +ctx.IDENT() +" Level: " + level +" => []");
        }
        // if is hash
        else if(ctx.getText().split("\\=")[1].startsWith("{")){
            isHash = true;
            //System.out.println("IDENT: " +ctx.IDENT() +" Level: " + level +" => {}");
        }
        // if is variable
        else{
            isVar = true;
            //System.out.println("IDENT: " +ctx.IDENT() +" Level: " + level +" => var");
            if(level == 0){
            //    this.generate(this.index,"PUSH_GLOBAL_I",ctx.IDENT().getText());
            }else{
            //    this.generate(this.index,"PUSH_LOCAL_I",ctx.IDENT().getText());
            }

        }

        visit(ctx.expression());
        if(isFunct){
            this.generate(this.index,"END",null);
        }
        level --;
        return null;
    }

    @Override
    public Object visitReturnStatementAST(MonkeyParser.ReturnStatementASTContext ctx) {
        // Return Statement
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitExpressionStatementAST(MonkeyParser.ExpressionStatementASTContext ctx) {
        // Expression Statement
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitExpressionAST(MonkeyParser.ExpressionASTContext ctx) {
        // Here, can evaluate the expression
        //if(isLet){
            //System.err.println(IDLE.getInstance().getValue(ctx.getText()));
       // }

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
        if(ctx.multiplicationExpression() != null){
            visit(ctx.multiplicationExpression());
        }
        if(ctx.additionFactor() != null){
            visit(ctx.additionFactor());
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

        visit(ctx.primitiveExpression());
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
        // Number

        return null;
    }

    @Override
    public Object visitPrimitiveExpression_stringAST(MonkeyParser.PrimitiveExpression_stringASTContext ctx) {
        // String

        return null;
    }

    @Override
    public Object visitPrimitiveExpression_identAST(MonkeyParser.PrimitiveExpression_identASTContext ctx) {
        // Ident

        return null;
    }

    @Override
    public Object visitPrimitiveExpression_trueAST(MonkeyParser.PrimitiveExpression_trueASTContext ctx) {
        // True

        return null;
    }

    @Override
    public Object visitPrimitiveExpression_falseAST(MonkeyParser.PrimitiveExpression_falseASTContext ctx) {
        // False

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
        //System.out.println("Should call function with " +ctx.IDENT().size() + " parameters");
        for (int i = 0; i < ctx.IDENT().size(); i++) {
            //System.out.println("Parameter: " +ctx.IDENT(i));
        }
        return null;
    }

    @Override
    public Object visitHashLiteralAST(MonkeyParser.HashLiteralASTContext ctx) {
        //System.out.println("Hash size: " + ctx.hashContent().size());
        for(int i = 0; i < ctx.hashContent().size();i++){
            //System.out.println("Data hash, index " + i + " : " + visit(ctx.hashContent(i)));
        }
        return null;
    }

    @Override
    public Object visitHashContentAST(MonkeyParser.HashContentASTContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitExpressionList_expressionAST(MonkeyParser.ExpressionList_expressionASTContext ctx) {
        //System.out.println("Array size: " + ctx.expression().size());
        for (int i = 0; i < ctx.expression().size(); i++) {
            //System.out.println("Data array, index " + i + " : " + visit(ctx.expression(i)));
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
        //this.generate(this.index,"LOAD_GLOBAL", ctx.expression().getText());
        //this.generate(this.index,"LOAD_GLOBAL", "write");
        //this.generate(this.index,"CALL_FUNCTION", "1");

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

    @Override
    public String toString() {
        String result="";
        for (String s : code){
            result+= s + '\n';
        }
        return result;
    }
    //-------------------------------------------------------------------------------
    //Method to generate Monkey Virtual Machine code
    private void generate(int index, String instruction, Object param){
        if(param != null){
            this.code.add(index +" "+ instruction + " " +param);
        }else{
            this.code.add(index +" "+ instruction);
        }
        this.index++;
    }

    // Statements
    public void makeAllFalse(){
        isLet = false;
        isCall = false;
        isReturn = false;
    }

    // Type
    public void forgetType(){
        isVar = false;
        isFunct = false;
        isList = false;
        isHash = false;
    }

}
