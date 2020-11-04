package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.TYPE;

//Class Variable, will represent "Iden" as simple's variable declaration
public class Variable extends Ident {
    protected int value;

    //Constructor
    public Variable(TerminalNode id, TYPE type, int level, ParserRuleContext declCtx) {
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
        return "\n(" + token.getSymbol().getLine()+ "," + token.getSymbol().getCharPositionInLine()  + ") ID = " + super.getId() +", Name = " + token.getText() + ", Level = " + level+  ", Type = " + type  + ", Value = " + value + ", Declaration Context=" + declCtx.getText();
    }
}
