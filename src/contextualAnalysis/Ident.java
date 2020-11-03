package contextualAnalysis;

import backend.IDLE;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

//Class Ident "Father"
public abstract class Ident {
    public int id;
    public Token token;
    protected String type;
    public int level;
    public ParserRuleContext declCtx;

    //Constructor
    public Ident(Token token, String type, int level, ParserRuleContext declCtx) {
        this.id = IDLE.getInstance().getNewId();
        this.token = token;
        this.type = type;
        this.level = level;
        this.declCtx = declCtx;
    }

    //Setter & Getter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public abstract String toString();
}
