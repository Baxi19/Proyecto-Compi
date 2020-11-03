package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

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
    public void insertVar(Token token, String type,int level, ParserRuleContext decl) {
        if(search(token, type) == null){
            Variable var = new Variable(token,type,level,decl);
            table.add(var);
        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getLine(), token.getCharPositionInLine(),
                            "Variable '" + token.getText() + "' has not been declared in current context",
                            "CONTEXT ERROR"));
        }
    }

    //TODO: get Parameters
    //------------------------------------------------------------------------------------------------------------------
    //Method to insert "type Method"
    public void insertMet(Token token, String type, int level, ParserRuleContext decl) {
        //get Parameters as list[] of string
        ArrayList<String> param = IDLE.getInstance().getFuntionInfo(decl.getText().split("\\=")[1]);

        //If some parameters have the same name, the param will be null, => New Error
        if(param == null){
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getLine(), token.getCharPositionInLine(),
                            "Some Parameter has the same name in method :" + token.getText(),
                            "CONTEXT ERROR "));
            return;
        }






        //TODO: It's only to check the info, we have to delete
        System.out.println("\n=>CTX: " + decl.getText());
        System.out.println("=>IDENT: " + token.getText());
        for (int i = 0; i < param.size(); i++) {
            System.out.println("@Parameter: "+param.get(i));
        }







        if(search(token, type) == null){
            Function function = new Function(token,type,level,decl,param);
            table.add(function);
        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getLine(), token.getCharPositionInLine(),
                            "Method '" + token.getText() + "' has not been declared in current context",
                            "CONTEXT ERROR "));
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to Search Methods or Vars in table
    public Ident search(Token token, String type){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getToken().getText().equals(token.getText()) &&  table.get(i).getType().equals(type)){
                return table.get(i);
            }
        }
        Collections.reverse(table);
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Method to print table info
    public String printTables() {
        String tableData = "\n\n*******************************************************************************************";
        tableData +=  "\n\n-----TABLE MET------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals("MET")){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";
        tableData +=  "\n\n-----TABLE VAR------";
        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getType().equals("VAR")){
                tableData += table.get(i).toString();
            }
        }
        tableData += "\n------END------";
        return tableData += "\n\n*******************************************************************************************\n\n";
    }

    //------------------------------------------------------------------------------------------------------------------
}
