package contextualAnalysis;

import backend.IDLE;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.TYPE;

//Class Ident "Father"
public abstract class Ident {
    public int id;
    public TerminalNode token;
    protected TYPE type;
    public int level;
    public ParserRuleContext declCtx;

    //Constructor
    public Ident(TerminalNode token, TYPE type, int level, ParserRuleContext declCtx) {
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

    public TerminalNode getToken() {
        return token;
    }

    public void setToken(TerminalNode token) {
        this.token = token;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
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
