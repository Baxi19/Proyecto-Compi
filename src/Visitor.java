import generated.MonkeyParser;
import generated.MonkeyParserBaseVisitor;

import java.util.ArrayList;

//Class visitor, used to show the tree with tabs in terminal
public class Visitor extends MonkeyParserBaseVisitor<Object> {

    private int Tabs=0;
    ArrayList<String> VisitorTree = new ArrayList<String>();

    private String Tabs(int cantTabs){
        String res="";
        for(int i=0; i< cantTabs;i++){
            res+="\t";
        }
        return res;
    }

    @Override
    public Object visitProgramAST(MonkeyParser.ProgramASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs) + " Program Context ");
        for(int i = 0; i < ctx.statement().size();i++){
            Tabs++;
            visit(ctx.statement(i));
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitStatement_LetAST(MonkeyParser.Statement_LetASTContext ctx) {
        if(ctx.LET()!= null){
            VisitorTree.add("\n" + " "+ ctx.getText());
        }if(ctx.letStatement()!= null){
            Tabs++;
            visit(ctx.letStatement());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitStatement_returnAST(MonkeyParser.Statement_returnASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs) + " Statement Return ");
        Tabs++;
        visit(ctx.returnStatement());
        Tabs--;
        return null;
    }

    @Override
    public Object visitCallExpressionStatementAST(MonkeyParser.CallExpressionStatementASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs) + " Call Expression Statement ");
        Tabs++;
        visit(ctx.expressionStatement());
        Tabs--;
        return null;
    }

    @Override
    public Object visitLetStatementAST(MonkeyParser.LetStatementASTContext ctx) {
        String X = null;
        if(ctx.ASSIGN()!= null){
            X = ctx.getText();
        }
        if(ctx.IDENT()!=null){
            X = ctx.getText();
        }
        if(ctx.PYCOMMA()!=null){
            X = ctx.getText();
        }
        if(ctx.expression()!=null){
            Tabs++;
            visit(ctx.expression());
            Tabs--;
        }
        if(X != null){
            VisitorTree.add("\n" + Tabs(Tabs)+ " Let Statement " + " "+ X);
        }
        return null;
    }

    @Override
    public Object visitReturnStatementAST(MonkeyParser.ReturnStatementASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Return Statement ");
        if(ctx.expression()!=null){
            Tabs++;
            visit(ctx.expression());
            Tabs--;
        }
        if(ctx.PYCOMMA()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        return null;
    }

    @Override
    public Object visitExpressionStatementAST(MonkeyParser.ExpressionStatementASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Expression Statement ");
        if(ctx.expression()!=null){
            Tabs++;
            visit(ctx.expression());
            Tabs--;
        }
        if(ctx.PYCOMMA()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        return null;
    }

    @Override
    public Object visitExpressionAST(MonkeyParser.ExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Expression ");
        if(ctx.additionExpression()!=null){
            Tabs++;
            visit(ctx.additionExpression());
            Tabs--;
        }
        if(ctx.comparison()!=null){
            Tabs++;
            visit(ctx.comparison());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitComparisonAST(MonkeyParser.ComparisonASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Comparison ");
        for(int i = 0; i < ctx.additionExpression().size();i++){
            Tabs++;
            visit(ctx.additionExpression(i));
            Tabs--;
        }
        for(int i = 0; i < ctx.LT().size();i++){
            VisitorTree.add(" "+ctx.LT().get(i).getText());
        }
        for(int i = 0; i < ctx.GT().size();i++){
            VisitorTree.add(" "+ctx.GT().get(i).getText());
        }
        for(int i = 0; i < ctx.LE().size();i++){
            VisitorTree.add(" "+ctx.LE().get(i).getText());
        }
        for(int i = 0; i < ctx.GE().size();i++){
            VisitorTree.add(" "+ctx.GE().get(i).getText());
        }
        for(int i = 0; i < ctx.EQUAL().size();i++){
            VisitorTree.add(" "+ctx.EQUAL().get(i).getText());
        }
        return null;
    }

    @Override
    public Object visitAdditionExpressionAST(MonkeyParser.AdditionExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Additional Expression ");
        if(ctx.additionFactor()!=null){
            Tabs++;
            visit(ctx.additionFactor());
            Tabs--;
        }
        if(ctx.multiplicationExpression()!=null){
            Tabs++;
            visit(ctx.multiplicationExpression());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitAdditionFactorAST(MonkeyParser.AdditionFactorASTContext ctx) {
        for(int i = 0; i < ctx.multiplicationExpression().size();i++){
            Tabs++;
            visit(ctx.multiplicationExpression(i));
            Tabs--;
        }
        for(int i = 0; i < ctx.ADD().size();i++){
            VisitorTree.add(" "+ctx.ADD().get(i).getText());
        }
        for(int i = 0; i < ctx.SUB().size();i++){
            VisitorTree.add(" "+ctx.SUB().get(i).getText());
        }
        return null;
    }

    @Override
    public Object visitMultiplicationExpressionAST(MonkeyParser.MultiplicationExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Multiplication Expression ");
        if(ctx.elementExpression()!=null){
            Tabs++;
            visit(ctx.elementExpression());
            Tabs--;
        }
        if(ctx.multiplicationFactor()!=null){
            Tabs++;
            visit(ctx.multiplicationFactor());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitMultiplicationFactorAST(MonkeyParser.MultiplicationFactorASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Multiplication Factor ");
        for(int i = 0; i < ctx.elementExpression().size();i++){
            Tabs++;
            visit(ctx.elementExpression(i));
            Tabs--;
        }
        for(int i = 0; i < ctx.MUL().size();i++){
            VisitorTree.add(" "+ctx.MUL().get(i).getText());
        }
        for(int i = 0; i < ctx.DIV().size();i++){
            VisitorTree.add(" "+ctx.DIV().get(i).getText());
        }
        return null;
    }

    @Override
    public Object visitElementExpressionAST(MonkeyParser.ElementExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Element Expression ");
        if(ctx.primitiveExpression()!=null){
            Tabs++;
            visit(ctx.primitiveExpression());
            Tabs--;
        }
        if(ctx.elementAccess()!=null){
            Tabs++;
            visit(ctx.elementAccess());
            Tabs--;
        }
        if(ctx.callExpression()!=null){
            Tabs++;
            visit(ctx.callExpression());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitElementAccessAST(MonkeyParser.ElementAccessASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Element Access ");
        if(ctx.L_BRACK()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.R_BRACK()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.expression()!=null){
            Tabs++;
            visit(ctx.expression());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitCallExpressionAST(MonkeyParser.CallExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Call Expression ");
        if(ctx.L_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.R_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.expressionList()!=null){
            Tabs++;
            visit(ctx.expressionList());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_numberAST(MonkeyParser.PrimitiveExpression_numberASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Integer ");
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_stringAST(MonkeyParser.PrimitiveExpression_stringASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" String ");
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_identAST(MonkeyParser.PrimitiveExpression_identASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Ident ");
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_trueAST(MonkeyParser.PrimitiveExpression_trueASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" True ");
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_falseAST(MonkeyParser.PrimitiveExpression_falseASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" False ");
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_expressionAST(MonkeyParser.PrimitiveExpression_expressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Expression ");
        if(ctx.L_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.R_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.expression()!=null){
            Tabs++;
            visit(ctx.expression());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_literalAST(MonkeyParser.PrimitiveExpression_literalASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Primitive Expression - Literal ");
        Tabs++;
        visit(ctx.arrayLiteral());
        Tabs--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_ArrayFunctionsAST(MonkeyParser.PrimitiveExpression_ArrayFunctionsASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Primitive Expression - ArrayFunctions ");

        if(ctx.L_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.R_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.arrayFunctions()!=null){
            Tabs++;
            visit(ctx.arrayFunctions());
            Tabs--;
        }
        if(ctx.expressionList()!=null){
            Tabs++;
            visit(ctx.expressionList());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_FunctionLiteralAST(MonkeyParser.PrimitiveExpression_FunctionLiteralASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Primitive Expression - Function Literal ");
        Tabs++;
        visit(ctx.functionLiteral());
        Tabs--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_HashLiteralAST(MonkeyParser.PrimitiveExpression_HashLiteralASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Primitive Expression - Hash Literal ");
        Tabs++;
        visit(ctx.hashLiteral());
        Tabs--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_PrintExpressionAST(MonkeyParser.PrimitiveExpression_PrintExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Primitive Expression - Print ");
        Tabs++;
        visit(ctx.printExpression());
        Tabs--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpression_IfExpressionAST(MonkeyParser.PrimitiveExpression_IfExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Primitive Expression - If ");
        Tabs++;
        visit(ctx.ifExpression());
        Tabs--;
        return null;
    }

    @Override
    public Object visitArrayFunctions_lenAST(MonkeyParser.ArrayFunctions_lenASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Len ");
        return null;
    }

    @Override
    public Object visitArrayFunctions_firstAST(MonkeyParser.ArrayFunctions_firstASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" First ");
        return null;
    }

    @Override
    public Object visitArrayFunctions_lastAST(MonkeyParser.ArrayFunctions_lastASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Last ");
        return null;
    }

    @Override
    public Object visitArrayFunctions_restAST(MonkeyParser.ArrayFunctions_restASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Rest ");
        return null;
    }

    @Override
    public Object visitArrayFunctions_pushAST(MonkeyParser.ArrayFunctions_pushASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Push ");
        return null;
    }

    @Override
    public Object visitArrayLiteralAST(MonkeyParser.ArrayLiteralASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Array Literal ");
        if(ctx.L_BRACK()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.R_BRACK()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.expressionList()!=null){
            Tabs++;
            visit(ctx.expressionList());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitFunctionLiteralAST(MonkeyParser.FunctionLiteralASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Function Literal ");
        if(ctx.FN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.L_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.R_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.functionParameters()!=null){
            Tabs++;
            visit(ctx.functionParameters());
            Tabs--;
        }
        if(ctx.blockStatement()!=null){
            Tabs++;
            visit(ctx.blockStatement());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitFunctionParametersAST(MonkeyParser.FunctionParametersASTContext ctx) {
        VisitorTree.add(Tabs(Tabs) + " Function Parameters ");
        for(int i = 0; i < ctx.IDENT().size();i++){
            VisitorTree.add(" "+ ctx.IDENT().get(i).getText());
        }
        for(int i = 0; i < ctx.COMMA().size();i++){
            VisitorTree.add(" "+ ctx.COMMA().get(i).getText());
        }
        return null;
    }

    @Override
    public Object visitHashLiteralAST(MonkeyParser.HashLiteralASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Hash Literal ");
        for(int i = 0; i < ctx.hashContent().size();i++){
            Tabs++;
            visit(ctx.hashContent(i));
            Tabs--;
        }
        for(int i = 0; i < ctx.COMMA().size();i++){
            VisitorTree.add(" "+ ctx.COMMA().get(i).getText());
        }
        if(ctx.L_BRACE()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        return null;
    }

    @Override
    public Object visitHashContentAST(MonkeyParser.HashContentASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Hash Content ");
        if(ctx.COLON()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        for(int i = 0; i < ctx.expression().size();i++){
            VisitorTree.add(" "+ctx.expression().get(i).getText());
        }
        return null;
    }

    @Override
    public Object visitExpressionList_expressionAST(MonkeyParser.ExpressionList_expressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Expression List - Expression ");
        for(int i = 0; i < ctx.expression().size();i++){
            VisitorTree.add(" "+ ctx.expression().get(i).getText());
        }
        for(int i = 0; i < ctx.COMMA().size();i++){
            VisitorTree.add(" "+ ctx.expression().get(i).getText());
        }
        return null;
    }

    @Override
    public Object visitExpressionList_nullAST(MonkeyParser.ExpressionList_nullASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Expression List - Null ");
        return null;
    }

    @Override
    public Object visitPrintExpressionAST(MonkeyParser.PrintExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Print - Expression ");
        if(ctx.PUTS()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.L_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.R_PAREN()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.expression()!=null){
            Tabs++;
            visit(ctx.expression());
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitIfExpressionAST(MonkeyParser.IfExpressionASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" If - Expression ");
        if(ctx.IF()!=null){
            VisitorTree.add(" "+ ctx.getText());
        }
        if(ctx.ELSE()!=null){
            VisitorTree.add(" "+ctx.getText());
        }
        if(ctx.expression()!=null){
            Tabs++;
            visit(ctx.expression());
            Tabs--;
        }
        for(int i = 0; i < ctx.blockStatement().size();i++){
            Tabs++;
            visit(ctx.blockStatement(i));
            Tabs--;
        }
        return null;
    }

    @Override
    public Object visitBlockStatementAST(MonkeyParser.BlockStatementASTContext ctx) {
        VisitorTree.add("\n" + Tabs(Tabs)+" Block Statement ");
        for(int i = 0; i < ctx.statement().size();i++){
            Tabs++;
            visit(ctx.statement(i));
            Tabs--;
        }
        return null;
    }

}
