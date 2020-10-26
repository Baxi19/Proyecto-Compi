package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.Collections;
import java.util.ArrayList;

// Symbols Table
public class SymbolTable {
    ArrayList<Object> table;
    private int currentLevel;

    class Ident{
        Token token;
        int level;
        int value;
        ParserRuleContext declCtx;

        public Ident(Token tok, ParserRuleContext decl){
            token = tok;
            level=currentLevel;
            value = 0;
            declCtx=decl;
        }

        public void setValue(int v){
            value = v;
        }

    }

    public SymbolTable() {
        table = new ArrayList<Object>();
        this.currentLevel=-1;
    }

    //TODO: Make
    public void insertar(Token id, ParserRuleContext decl) {
        //no se puede insertar un elemento repetido en el mismo nivel
        Ident i = new Ident(id,decl);
        table.add(i);
    }

    public Ident search(String nombre){
        Ident temp=null;
        for(Object id : table) {
            if ((( Ident ) id).token.getText().equals(nombre)){
                temp = (( Ident ) id);
            }
        }
        return temp;
    }

    public Ident searchReverse(String nombre){
        Collections.reverse(table);
        Ident temp=null;
        for(Object id : table){
            if (((Ident)id).token.getText().equals(nombre)){
                temp = ((Ident)id);
            }
        }
        Collections.reverse(table);
        return temp;
    }

    public void openScope(){
        currentLevel++;
    }

    public void closeScope(){
        table.removeIf(n -> (((Ident)n).level == currentLevel));
        currentLevel--;
    }

    public String imprimir() {
        String tableData = "\n-----TABLE START------";
        for (int i = 0; i < table.size(); i++) {
            Token s = ( Token ) ((Ident) table.get(i)).token;
            tableData += "\nNombre: " + s.getText() + " - Nivel: " + ((Ident) table.get(i)).level;
        }
        tableData += "\n------TABLE END-------";
        return tableData;
    }
}
