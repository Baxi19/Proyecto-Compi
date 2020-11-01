package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

//Class Ident "Father"
public abstract class Ident {
    public Token id;
    protected String type;
    public int level;
    public ParserRuleContext declCtx;

    //Constructor
    public Ident(Token id, String type, int level, ParserRuleContext declCtx) {
        this.id = id;
        this.type = type;
        this.level = level;
        this.declCtx = declCtx;
    }

    //Setter & Getter
    public Token getId() {
        return id;
    }

    public void setId(Token id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
