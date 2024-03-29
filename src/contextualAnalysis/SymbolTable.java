package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.TYPE;

import java.util.Collections;
import java.util.ArrayList;

// Symbols Table
public class SymbolTable {
    public ArrayList<Ident> table ;

    //------------------------------------------------------------------------------------------------------------------
    //Constructor
    public SymbolTable() {
        table = new ArrayList<>();
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to insert "type Var"
    public void insertVar(TerminalNode token, TYPE type, int level, ParserRuleContext decl) {
        if(search(token, type, level) == null){
            Variable var = new Variable(token,type,level,decl);
            table.add(var);
        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getSymbol().getLine(), token.getSymbol().getCharPositionInLine(),
                            "Variable '" + token.getText() + "' has not been declared in current context",
                            "SINTAX ERROR  "));
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to insert "type Method"
    public void insertMet(TerminalNode token, TYPE type, int level, ParserRuleContext decl) {
        //get Parameters as list[] of string
        ArrayList<String> param = IDLE.getInstance().getFuntionInfo(decl.getText().split("\\=")[1]);

        //If some parameters have the same name, the param will be null, => New Error
        if(param == null){
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getSymbol().getLine(), token.getSymbol().getCharPositionInLine(),
                            "Some Parameter has the same name in method :" + token.getText(),
                            "SINTAX ERROR  "));
            return;
        }

        if(search(token,type,level) == null){
            Function function = new Function(token,type,level,decl);
            ArrayList<TerminalNode> parm = IDLE.getInstance().parameters;
            for (int i = 0; i < parm.size(); i++) {
                Parameter parameter = new Parameter(parm.get(i), TYPE.PARAMETER, level, decl, true, function);
                function.getParameters().add(parameter);
                table.add(parameter);
            }
            if(IDLE.getInstance().existReturn){
                function.setReturnValue(IDLE.getInstance().returnStatement);
                IDLE.getInstance().existReturn = false;
            }

            table.add(function);

        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getSymbol().getLine(), token.getSymbol().getCharPositionInLine(),
                            "Method '" + token.getText() + "' has not been declared in current context",
                            "SINTAX ERROR  "));
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to insert "HASH CONTENT"
    public void insertHashContent(TerminalNode token, TYPE type, int level, ParserRuleContext decl) {
        if(search(token, type, level) == null){
            HashContent var = new HashContent(token,type,level,decl);
            table.add(var);
        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getSymbol().getLine(), token.getSymbol().getCharPositionInLine(),
                            "Hash Content '" + token.getText() + "' has not been declared in current context",
                            "SINTAX ERROR  "));
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to insert "HASH Literal"
    public void insertHashLiteral(TerminalNode token, TYPE type, int level, ParserRuleContext decl) {
        if(search(token, type, level) == null){
            HashLiteral var = new HashLiteral(token,type,level,decl);
            table.add(var);
        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getSymbol().getLine(), token.getSymbol().getCharPositionInLine(),
                            "Hash Literal '" + token.getText() + "' has not been declared in current context",
                            "SINTAX ERROR  "));
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Methods or Vars in table
    public Ident search(TerminalNode token, TYPE type, int level){
        switch (type) {
            case FUNCTION:
                if(searchFunction(token,level) != null){
                    return searchFunction(token,level);
                }else{
                    return callFunction(token,level);
                }
            case VARIABLE:
                return searchVariable(token,level);
            case PARAMETER:
                return searchParameter(token,level);
            case HASHCONTENT:
                return searchHashContent(token,level);
            case HASHLITERAL:
                return searchHashLiteral(token,level);

        }
        return null;

    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Variable
    public Ident searchVariable(TerminalNode token, int level){
        if(level > 1){
            return searchParameter(token,level);
        }else{
            Collections.reverse(table);
            for(int i = 0; i<table.size(); i++){
                if(table.get(i).getType() == TYPE.VARIABLE){
                    //If same name
                    if(table.get(i).getToken().getText().equals(token.getText())){
                        //if same level
                        if(table.get(i).getLevel() == level){
                            Collections.reverse(table);
                            return table.get(i);
                        }
                    }
                }
            }
            Collections.reverse(table);
            return null;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Function
    public Ident searchFunction(TerminalNode token, int level){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getType() == TYPE.FUNCTION){
                //If same name
                if(table.get(i).getToken().getText().equals(token.getText())){
                    //if same level
                    if(table.get(i).getLevel() >= level){
                        Collections.reverse(table);
                        return table.get(i);
                    }
                }
            }
        }
        Collections.reverse(table);
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Function
    public Ident callFunction(TerminalNode token, int level){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getType() == TYPE.FUNCTION){
                //If same name
                if(table.get(i).getToken().getText().equals(token.getText())){
                    //if same level

                        Collections.reverse(table);
                        return table.get(i);

                }
            }
        }
        Collections.reverse(table);
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Parameter
    public Ident searchParameter(TerminalNode token, int level){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getType() ==  TYPE.PARAMETER){
                //If same name
                if(table.get(i).getToken().getText().equals(token.getText())){
                    //if same level
                    if(table.get(i).getLevel() == level){
                        Collections.reverse(table);
                        return table.get(i);
                    }
                }
            }
        }
        Collections.reverse(table);
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Hash Content
    public Ident searchHashContent(TerminalNode token, int level){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getType() ==  TYPE.HASHCONTENT){
                //If same name
                if(table.get(i).getToken().getText().equals(token.getText())){
                    //if same level
                    if(table.get(i).getLevel() == level){
                        Collections.reverse(table);
                        return table.get(i);
                    }
                }
            }
        }
        Collections.reverse(table);
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Hash Literal
    public Ident searchHashLiteral(TerminalNode token, int level){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getType() == TYPE.HASHLITERAL){
                //If same name
                if(table.get(i).getToken().getText().equals(token.getText())){
                    //if same level
                    if(table.get(i).getLevel() == level){
                        Collections.reverse(table);
                        return table.get(i);
                    }
                }
            }
        }
        Collections.reverse(table);
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to print table info
    public String printTables() {
        Collections.reverse(table);
        String tableData = "\n\n*******************************************************************************************";
        //Funtion
        tableData +=  "\n\n-----TABLE FUNCTION------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals(TYPE.FUNCTION)){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";

        //Parameter
        tableData +=  "\n\n-----TABLE PARAMETER------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals(TYPE.PARAMETER)){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";

        //Variable
        tableData +=  "\n\n-----TABLE VARIABLE------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals(TYPE.VARIABLE)){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";

        //List
        tableData +=  "\n\n-----TABLE HASH CONTENT------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals(TYPE.HASHCONTENT)){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";

        //Hash
        tableData +=  "\n\n-----TABLE HASH LITERAL------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals(TYPE.HASHLITERAL)){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";

        Collections.reverse(table);
        return tableData += "\n\n*******************************************************************************************\n\n";
    }

    //------------------------------------------------------------------------------------------------------------------
    public Boolean check(String searched){
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getToken().getText().equals(searched)){
                return true;
            }
        }
        return false;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Function
    public Boolean checkParameter(String name, int par){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getType() == TYPE.FUNCTION){
                //If same name
                if(table.get(i).getToken().getText().equals(name)){
                    //if same parameters
                    Function function = (Function) table.get(i);
                    if(function.getParameters().size() == par){
                        Collections.reverse(table);
                        return true;
                    }
                }
            }
        }
        Collections.reverse(table);
        return false;
    }

    public Ident rewrite(TerminalNode token, int level){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getToken().getText().equals(token.getText())){
                if(table.get(i).level == level){
                    table.remove(i);
                }
                return null;
            }

        }
        Collections.reverse(table);
        return null;
    }


    public void closeScope(int level){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).level == level){
                table.remove(i);
            }
        }
        Collections.reverse(table);
    }
}
