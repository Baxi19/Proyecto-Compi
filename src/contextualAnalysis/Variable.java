package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Variable extends Ident {
    protected Token token;
    protected int level;
    //protected int value;
    protected ParserRuleContext declCtx;

    //Constructor


    public Variable() {
    }

    public Variable(Token token, int level, ParserRuleContext declCtx) {
        this.token = token;
        this.level = level;
        //this.value = value;
        this.declCtx = declCtx;
    }

    //Getter & Setter
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

    public ParserRuleContext getDeclCtx() {
        return declCtx;
    }

    public void setDeclCtx(ParserRuleContext declCtx) {
        this.declCtx = declCtx;
    }


}
