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

    //Constructor
    public SymbolTable() {
        table = new ArrayList<>();
    }

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

    //Method to insert "type Method"
    public void insertMet(Token token, String type, int level, ParserRuleContext decl) {
        if(search(token, type) == null){
            Function function = new Function(token,type,level,decl);
            table.add(function);
        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getLine(), token.getCharPositionInLine(),
                            "Method '" + token.getText() + "' has not been declared in current context",
                            "CONTEXT ERROR"));
        }
    }

    //Method to Search Methods or Vars in table
    public Ident search(Token token, String type){
        Collections.reverse(table);
        for(int i = 0; i<table.size(); i++){
            if(table.get(i).getId().getText().equals(token.getText()) &&  table.get(i).getType().equals(type)){
                return table.get(i);
            }
        }
        Collections.reverse(table);
        /*if(type.equals("MET")){
            //if is method declared, return method
            for(int i = 0; i<table.size(); i++){
                if(table.get(i).getId().getText().equals(token.getText()) &&  table.get(i).getType().equals("MET")){
                    return table.get(i);
                }
            }
        }else{
            //if is var declared, return var
            for(int i = 0; i<table.size(); i++){
                if(table.get(i).getId().getText().equals(token.getText()) &&  table.get(i).getType().equals("VAR")){
                    return table.get(i);
                }
            }
        }

         */
        return null;
    }

    //Method to print table info
    public String printTables() {
        Collections.reverse(table);
        String tableData =  "\n\n-----TABLE------";
        for (int i = 0; i < table.size(); i++) {
            tableData += table.get(i).toString();
        }
        Collections.reverse(table);
        return tableData += "\n------END------";
    }
}
