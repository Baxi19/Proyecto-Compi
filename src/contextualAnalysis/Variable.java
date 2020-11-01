package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

//Class Variable, will represent "Iden" as simple's variable declaration
public class Variable extends Ident {
    //TODO: Trabajar y analizar el tipo
    protected int value;

    //Constructor
    public Variable(Token id, String type, int level, ParserRuleContext declCtx) {
        super(id, type, level, declCtx);
    }

    //Setter & Getter
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    //to String
    @Override
    public String toString() {
        return "\nName = " + id.getText() + ", Level = " + level+ ", Row = " + id.getLine()+ ", Column = " + id.getCharPositionInLine()  + ", Type = " + type  + ", Value = " + value + ", Declaration Context=" + declCtx.getText();
    }
}
