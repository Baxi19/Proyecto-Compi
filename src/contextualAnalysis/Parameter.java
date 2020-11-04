package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.TYPE;

public class Parameter extends Ident {
    public Boolean parameter;
    public int funtionId;

    public Parameter(TerminalNode token, TYPE type, int level, ParserRuleContext declCtx, Boolean parameter, int funtionId) {
        super(token, type, level, declCtx);
        this.parameter = parameter;
        this.funtionId = funtionId;
    }

    public Boolean getParameter() {
        return parameter;
    }

    public void setParameter(Boolean parameter) {
        this.parameter = parameter;
    }

    public int getFuntionId() {
        return funtionId;
    }

    public void setFuntionId(int funtionId) {
        this.funtionId = funtionId;
    }

    @Override
    public String toString() {
        return "\n(" + token.getSymbol().getLine()+ "," + token.getSymbol().getCharPositionInLine()  + ") ID = " + super.getId() +", Name = " + token.getText() + ", Level = " + level +  ", Type = " + type  + ", FuntionID = " + funtionId + ", Declaration Context=" + declCtx.getText();
    }
}
