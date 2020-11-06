package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.TYPE;

import java.util.Collections;
import java.util.ArrayList;
import java.util.regex.*;

// Symbols Table
public class SymbolTable {
    public ArrayList<Ident> table ;

    //public int level;
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
            table.add(function);

        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getSymbol().getLine(), token.getSymbol().getCharPositionInLine(),
                            "Method '" + token.getText() + "' has not been declared in current context",
                            "SINTAX ERROR  "));
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Methods or Vars in table
    //TODO: DEBUG THIS METHOD
    public Ident search(TerminalNode token, TYPE type, int level){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getToken().getText().equals(token.getText()) &&
                    (table.get(i).getType().equals(type) ||
                            table.get(i).getType().equals(TYPE.PARAMETER))){
                return table.get(i);
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
        tableData +=  "\n\n-----TABLE FUNTION------";
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
        tableData +=  "\n\n-----TABLE LIST------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals(TYPE.LIST)){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";

        //Hash
        tableData +=  "\n\n-----TABLE HASH------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals(TYPE.HASH)){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";

        Collections.reverse(table);
        return tableData += "\n\n*******************************************************************************************\n\n";
    }

    //------------------------------------------------------------------------------------------------------------------
    /*public void openScope(){
        level++;
    }

    public void closeScope(){
        table.removeIf(n -> (((Ident)n).getLevel() == level));
        level--;
    }

     */

    public Boolean check(String searched){
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getToken().getText().equals(searched)){
                return true;
            }
        }
        return false;
    }

}
