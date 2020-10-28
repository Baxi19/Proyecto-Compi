package contextualAnalysis;

import backend.IDLE;
import errors.Error;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.Collections;

// Symbols Table
public class SymbolTableFunc {
    ArrayList<Object> table;
    private int currentLevel;

    public static class Ident{

        Token token;
        int level;
        int value;
        TYPE type;

        //Type added
        enum TYPE {
            NEUTRAL,
            INT,
            STRING,
            LIST,
            TRUE,
            FALSE,
            NULL,
            FUNC
        }

        ParserRuleContext declCtx;

        public Ident(Token tok,int level ,ParserRuleContext decl){
            this.token = tok;
            this.level=level;
            this.value = 0;
            this.type = TYPE.NEUTRAL;
            this.declCtx=decl;
        }

        //Setter 7 getter
        public Token getToken() {
            return token;
        }

        public void setToken(Token token) {
            this.token = token;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public TYPE getType() {
            return type;
        }

        public void setType(TYPE type) {
            this.type = type;
        }

        public ParserRuleContext getDeclCtx() {
            return declCtx;
        }

        public void setDeclCtx(ParserRuleContext declCtx) {
            this.declCtx = declCtx;
        }
    }

    //Constructor
    public SymbolTableFunc() {
        table = new ArrayList<Object>();
        this.currentLevel=-1;
    }

    //TODO: Make
    public void insertar(Token token, int level, ParserRuleContext decl) {
        if(search(token.getText()) == null){
            Ident i = new Ident(token,level,decl);
            table.add(i);
        }else{
            IDLE.getInstance().errorsContextual.add(
                    new Error(token.getLine(), token.getCharPositionInLine(),
                            "Variable '" + token.getText() + "' has not been declared in current context",
                            "CONTEXT ERROR"));
        }

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
