package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

//Class Variable, will represent "Iden" as simple's variable declaration
public class Variable extends Ident {

    //TODO: Trabajar y analizar el tipo
    protected int value;
    protected String type;

    //Constructor
    public Variable(Token id, int level, ParserRuleContext declCtx, int value) {
        super(id, level, declCtx);
        this.value = value;
        this.type = "VAR";
    }

    //Setter & Getter
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
