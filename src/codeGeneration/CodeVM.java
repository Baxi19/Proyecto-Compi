package codeGeneration;

import backend.IDLE;
import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;

import java.util.ArrayList;


public class CodeVM extends MonkeyParserBaseVisitor<Object> {

    private boolean letmain;
    private TablaSimbolos tablaIDS;

    private int index;
    private ArrayList<String> code;
    public int level;

    // Aux
    private MonkeyParser.LetStatementASTContext ctxLet;
    private MonkeyParser.CallExpressionStatementASTContext ctxCall;

    private boolean isLet;
    private boolean isReturn;
    private boolean isCall;

    private boolean isVar;
    private boolean isFunct;
    private boolean isParam;
    private boolean isList;
    private boolean isHash;


    //Constructor
    public CodeVM() {
        IDLE.getInstance().instructions = "";
        IDLE.getInstance().functions = new ArrayList<>();
        IDLE.getInstance().parameterQuantity = 0;

        this.letmain=false;
        this.tablaIDS = new TablaSimbolos();
        this.index=0;
        this.code= new ArrayList<>();
        this.level = -1;
        this.isLet = false;
        this.isReturn = false;
        this.isCall = false;
        this.isVar = false;
        this.isFunct = false;
        this.isParam = false;
        this.isList = false;
        this.isHash = false;
    }

    @Override
    public Object visitProgramAST(MonkeyParser.ProgramASTContext ctx) {
        // Program
        level ++;
        tablaIDS.openScope();
        for(int i = 0; i < ctx.statement().size();i++){
            visit(ctx.statement(i));
        }
        level --;
        tablaIDS.closeScope();
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
        return null;
    }

    @Override
    public Object visitCallExpressionStatementAST(MonkeyParser.CallExpressionStatementASTContext ctx) {
        // Call expressions
        makeAllFalse();
        isCall = true;
        ctxCall = ctx;
        visit(ctx.expressionStatement());
        return null;
    }

    @Override
    public Object visitLetStatementAST(MonkeyParser.LetStatementASTContext ctx) {
        // Let Statement, Here will check the type
        forgetType();
        ctxLet = ctx; //save the ctx as aux

        // If is function
        if(ctx.getText().split("\\=")[1].startsWith("fn(")){
            isFunct = true;
            if(ctx.IDENT().getText().toLowerCase().equals("main") && level == 0){
                letmain  = true;
                this.tablaIDS.insertar(ctx.IDENT().getSymbol(),0,ctx);
                IDLE.getInstance().functions.add(new Funct(this.index, "Main", 0));
                this.generate(this.index,"DEF Main", null);
                visit(ctx.expression());
                this.generate(this.index,"END",null);
                letmain = false;
            }else{
                level ++;
                tablaIDS.openScope();
                this.tablaIDS.insertar(ctx.IDENT().getSymbol(),0,ctx);
                IDLE.getInstance().functions.add(new Funct(this.index, ctx.IDENT().getText(), 0));
                this.generate(this.index,"DEF", ctx.IDENT().getText());
                visit(ctx.expression());
                this.generate(this.index,"RETURN",null);
                level--;
                tablaIDS.closeScope();
            }
        }

        // if is List
        else if(ctx.getText().split("\\=")[1].startsWith("[")){
            isList = true;
            this.tablaIDS.insertar(ctx.IDENT().getSymbol(),0,ctx);
            if(level == 0 | letmain){
                this.generate(this.index,"PUSH_GLOBAL",ctx.IDENT().getText());
                visit(ctx.expression());
                this.generate(this.index,"STORE_GLOBAL",ctx.IDENT().getText());
            }else{
                this.generate(this.index,"PUSH_LOCAL",ctx.IDENT().getText());
                visit(ctx.expression());
                this.generate(this.index,"STORE_FAST",ctx.IDENT().getText());
            }
        }

        // if is hash
        else if(ctx.getText().split("\\=")[1].startsWith("{")){
            isHash = true;
            this.tablaIDS.insertar(ctx.IDENT().getSymbol(),0,ctx);
            if(level == 0 | letmain){
                this.generate(this.index,"PUSH_GLOBAL",ctx.IDENT().getText());
                visit(ctx.expression());
                this.generate(this.index,"STORE_GLOBAL",ctx.IDENT().getText());
            }else{
                this.generate(this.index,"PUSH_LOCAL",ctx.IDENT().getText());
                visit(ctx.expression());
                this.generate(this.index,"STORE_FAST",ctx.IDENT().getText());
            }
        }

        // if is variable
        else{
            isVar = true;
            this.tablaIDS.insertar(ctx.IDENT().getSymbol(),0,ctx);
            if(level == 0 | letmain){
                this.generate(this.index,"PUSH_GLOBAL",ctx.IDENT().getText());
                visit(ctx.expression());
                this.generate(this.index,"STORE_GLOBAL",ctx.IDENT().getText());
            }else{
                this.generate(this.index,"PUSH_LOCAL",ctx.IDENT().getText());
                visit(ctx.expression());
                this.generate(this.index,"STORE_FAST",ctx.IDENT().getText());
            }
        }
        return null;
    }

    @Override
    public Object visitReturnStatementAST(MonkeyParser.ReturnStatementASTContext ctx) {
        // Return Statement
        visit(ctx.expression());
        this.generate(this.index,"RETURN_VALUE",null);
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
        visit(ctx.additionExpression());
        visit(ctx.comparison());
        return null;
    }

    @Override
    public Object visitComparisonAST(MonkeyParser.ComparisonASTContext ctx) {
        if(!ctx.additionExpression().isEmpty()){
            for(int i = 0; i < ctx.additionExpression().size();i++){
                visit(ctx.additionExpression(i));
                // <=
                if(ctx.LE(i) != null){
                    this.generate(this.index,"COMPARE_OP", ctx.LE(i));
                }
                // >=
                if(ctx.GE(i) != null){
                    this.generate(this.index,"COMPARE_OP", ctx.GE(i));
                }
                // ==
                if(ctx.EQUAL(i) != null){
                    this.generate(this.index,"COMPARE_OP", ctx.EQUAL(i));
                }
                // !=
                if(ctx.NOT_EQUAL(i) != null){
                    this.generate(this.index,"COMPARE_OP", ctx.NOT_EQUAL(i));
                }
                // >
                if(ctx.GT(i) != null){
                    this.generate(this.index,"COMPARE_OP", ctx.GT(i));
                }
                // <
                if(ctx.LT(i) != null){
                    this.generate(this.index,"COMPARE_OP", ctx.LT(i));
                }
            }
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
        //mult expr
        if(!ctx.multiplicationExpression().isEmpty()){
            for(int i = 0; i < ctx.multiplicationExpression().size();i++){
                visit(ctx.multiplicationExpression(i));
            }
        }

        //Add
        if(!ctx.ADD().isEmpty()){
            for (int i = 0; i < ctx.ADD().size(); i++) {
                visit(ctx.ADD(i));
                this.generate(this.index,"BINARY_ADD", null);
            }
        }

        //Subst
        if(!ctx.SUB().isEmpty()){
            for (int i = 0; i < ctx.SUB().size(); i++) {
                visit(ctx.SUB(i));
                this.generate(this.index,"BINARY_SUBSTRACT", null);
            }
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
        //elementExpression
        if(!ctx.elementExpression().isEmpty()){
            for(int i = 0; i < ctx.elementExpression().size();i++){
                visit(ctx.elementExpression(i));
            }
        }

        //Mul
        if(!ctx.MUL().isEmpty()){
            for (int i = 0; i < ctx.MUL().size(); i++) {
                visit(ctx.MUL(i));
                this.generate(this.index,"BINARY_MULTIPLY", null);
            }
        }

        //Div
        if(!ctx.DIV().isEmpty()){
            for (int i = 0; i < ctx.DIV().size(); i++) {
                visit(ctx.DIV(i));
                this.generate(this.index,"BINARY_DIVIDE", null);
            }
        }
        return null;
    }

    @Override
    public Object visitElementExpressionAST(MonkeyParser.ElementExpressionASTContext ctx) {
        if(ctx.callExpression()!=null){
            isParam = true;
            visit(ctx.callExpression());
        }
        visit(ctx.primitiveExpression());
        if(ctx.elementAccess()!=null){
            visit(ctx.elementAccess());
        }

        if(ctx.callExpression()!=null){
            this.generate(index, "CALL_FUNCTION", IDLE.getInstance().parameterQuantity);
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
        this.generate(this.index,"LOAD_CONST", ctx.INTEGER());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_stringAST(MonkeyParser.PrimitiveExpression_stringASTContext ctx) {
        // String, if is Variable
        this.generate(this.index,"LOAD_CONST", ctx.STRING());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_identAST(MonkeyParser.PrimitiveExpression_identASTContext ctx) {
        // Ident
        boolean isFunction = false;
        if(letmain | level == 0){
            this.generate(this.index,"LOAD_GLOBAL", ctx.IDENT().getText());
        }else{
            for(int i = 0; i < IDLE.getInstance().functions.size(); i++){
                if(IDLE.getInstance().functions.get(i).name.equals(ctx.IDENT().toString())){
                    isFunction = true;
                }
            }
            if(isFunction){
                this.generate(this.index,"LOAD_GLOBAL", ctx.IDENT().getText());
            }else{
                this.generate(this.index,"LOAD_FAST", ctx.IDENT().getText());
            }

        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_trueAST(MonkeyParser.PrimitiveExpression_trueASTContext ctx) {
        // True
        this.generate(this.index,"LOAD_CONST", ctx.TRUE());
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_falseAST(MonkeyParser.PrimitiveExpression_falseASTContext ctx) {
        // False
        this.generate(this.index,"LOAD_CONST", ctx.FALSE());
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
        if(ctx.expressionList()!=null){
            visit(ctx.expressionList());
        }
        if(ctx.arrayFunctions()!=null){
            visit(ctx.arrayFunctions());
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
        if(ctx.hashLiteral() != null){
            visit(ctx.hashLiteral());
        }
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
        // Len();
        this.generate(this.index,"LOAD_GLOBAL", ctx.LEN());
        return null;
    }

    @Override
    public Object visitArrayFunctions_firstAST(MonkeyParser.ArrayFunctions_firstASTContext ctx) {
        // First()
        this.generate(this.index,"LOAD_GLOBAL", ctx.FIRST());
        return null;
    }

    @Override
    public Object visitArrayFunctions_lastAST(MonkeyParser.ArrayFunctions_lastASTContext ctx) {
        // last()
        this.generate(this.index,"LOAD_GLOBAL", ctx.LAST());
        return null;
    }

    @Override
    public Object visitArrayFunctions_restAST(MonkeyParser.ArrayFunctions_restASTContext ctx) {
        // Rest()
        this.generate(this.index,"LOAD_GLOBAL", ctx.REST());
        return null;
    }

    @Override
    public Object visitArrayFunctions_pushAST(MonkeyParser.ArrayFunctions_pushASTContext ctx) {
        // Push()
        this.generate(this.index,"LOAD_GLOBAL", ctx.PUSH());
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
            isParam = true;
            visit(ctx.functionParameters());
        }
        if(ctx.blockStatement()!=null){
            visit(ctx.blockStatement());
        }
        return null;
    }

    @Override
    public Object visitFunctionParametersAST(MonkeyParser.FunctionParametersASTContext ctx) {
        //TODO:ONLY in Main, Parameters was declared with value 0, but have to assign the value when are called
        for (int i = 0; i < ctx.IDENT().size(); i++) {
            if(letmain | level == 0){
                this.tablaIDS.insertar(ctx.IDENT(i).getSymbol(),0,null);
                this.generate(this.index,"LOAD_GLOBAL", ctx.IDENT(i));
                this.generate(this.index,"STORE_GLOBAL", ctx.IDENT(i));
            }else{
                this.tablaIDS.insertar(ctx.IDENT(i).getSymbol(),0,null);
                this.generate(this.index,"PUSH_LOCAL", ctx.IDENT(i));
            }
        }
        if(isParam){
            IDLE.getInstance().parameterQuantity = ctx.IDENT().size();
            System.err.println(IDLE.getInstance().parameterQuantity);
        }
        isParam = false;
        return null;
    }

    @Override
    public Object visitHashLiteralAST(MonkeyParser.HashLiteralASTContext ctx) {
        // HASH
        if(!ctx.hashContent().isEmpty()){
            for(int i = 0; i < ctx.hashContent().size();i++){
                visit(ctx.hashContent(i));
            }
            this.generate(this.index, "BUILD_CONST_KEY_MAP", ctx.hashContent().size());
        }
        return null;
    }

    @Override
    public Object visitHashContentAST(MonkeyParser.HashContentASTContext ctx) {
        if(!ctx.expression().isEmpty()){
            for (int i = 0; i < ctx.expression().size(); i++) {
                visit(ctx.expression(i));
            }
        }
        return ctx.getText();
    }

    @Override
    public Object visitExpressionList_expressionAST(MonkeyParser.ExpressionList_expressionASTContext ctx) {
        // BUILD_LIST
        if(!ctx.expression().isEmpty()){
            for (int i = 0; i < ctx.expression().size(); i++) {
                visit(ctx.expression(i));
            }
        }

        // if is let & list
        if(isList && isLet){
            this.generate(this.index, "BUILD_LIST", ctx.expression().size());
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
        if(ctx.expression() != null){
            visit(ctx.expression());
        }
        this.generate(this.index,"LOAD_GLOBAL", "write");
        this.generate(this.index,"CALL_FUNCTION", "1");
        return null;
    }

    @Override
    public Object visitIfExpressionAST(MonkeyParser.IfExpressionASTContext ctx) {
        //TODO:  Make Type IF, VM's Instructions

        // if
        visit(ctx.expression());
        int tag1Index = this.index;
        this.generate(this.index,"JUMP_IF_FALSE", -1);

        // if true
        visit(ctx.blockStatement(0));
        int tag2Index = this.index;
        this.generate(this.index,"JUMP_ABSOLUTE",-1);
        this.code.set(tag1Index, tag1Index+" "+"JUMP_IF_FALSE"+ " "+this.index);

        // else
        if(ctx.ELSE() != null){
            visit(ctx.blockStatement(1));
            this.code.set(tag2Index, tag2Index+" "+"JUMP_ABSOLUTE"+ " "+this.index);
        }
        this.code.set(tag2Index, tag2Index+" "+"JUMP_ABSOLUTE"+ " "+this.index);
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
