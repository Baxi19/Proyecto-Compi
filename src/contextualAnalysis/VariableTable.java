package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.Collections;

public class VariableTable {
    protected ArrayList<Variable> table;
    protected int currentLevel;

    //Constructor
    public VariableTable() {
        this.table = new ArrayList<>();
        this.currentLevel = -1;
    }

    //Getter & Setter
    public ArrayList<Variable> getTable() {
        return table;
    }

    public void setTable(ArrayList<Variable> table) {
        this.table = table;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    //Methods
    public void insertar(Token token, int level, ParserRuleContext declCtx) {
        //no se puede insertar un elemento repetido en el mismo nivel
        Variable var = new Variable(token, level,declCtx);
        table.add(var);
        /*
        if(!exist(token.getText(), level)){
            //
        }else{
            IDLE.getInstance().errorsContextual.add(new Error(token.getLine(), token.getCharPositionInLine(),"Variable '" + token.getText() + "' has not been declared in current context", "CONTEXT ERROR"));
        }

         */

    }

    //Check if exist in table
    public Boolean exist(String name, int level){

        for (int i = 0; i < table.size(); i++) {
            if(table.get(i).getToken().getText().equals(name)){
                if(table.get(i).level == level){
                    return true;
                }
            }
        }

        return false;
    }


    //Search
    public Variable search(String name){
        Variable temp=null;
        for(Object id : table) {
            if ((( Variable ) id).token.getText().equals(name)){
                return (( Variable ) id);
            }
        }
        return temp;
    }

    //Serach reverse
    public Variable searchReverse(String name){
        Collections.reverse(table);
        Variable temp=null;
        for(Object id : table){
            if ((( Variable )id).token.getText().equals(name)){
                temp = (( Variable )id);
            }
        }
        Collections.reverse(table);
        return temp;
    }

    //Increment level
    public void openScope(){
        currentLevel++;
    }

    //Decrement level
    public void closeScope(){
        table.removeIf(n -> (
                (( Variable )n).level == currentLevel)
        );
        currentLevel--;
    }

    //To String
    public String imprimir() {
        String tableData = "\n-----TABLE START------";
        for (int i = 0; i < table.size(); i++) {
            Token s = ( Token ) (table.get(i)).token;
            tableData += "\nNombre: " + s.getText() + " - Nivel: " + (table.get(i)).level;
        }
        tableData += "\n------TABLE END-------";
        return tableData;
    }
}
