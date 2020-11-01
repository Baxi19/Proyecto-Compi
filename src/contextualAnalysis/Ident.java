package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

//Class Ident "Father"
public  class Ident {
    public Token id;
    public int level;
    public ParserRuleContext declCtx;

    //Constructor
    public Ident(Token id, int level, ParserRuleContext declCtx) {
        this.id = id;
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
}
