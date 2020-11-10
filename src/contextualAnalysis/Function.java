package contextualAnalysis;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.TYPE;

import java.util.ArrayList;

//Class Function, will represent "Iden" as method's declaration
public class Function extends Ident {
    public ArrayList<Ident> parameters;
        public String returnValue;

    //------------------------------------------------------------------------------------------------------------------
    //Constructor
    public Function(TerminalNode token, TYPE type, int level, ParserRuleContext declCtx) {
        super(token, type, level, declCtx);
        this.parameters = new ArrayList<>();
        this.returnValue = "VOID";
    }

    public Function(TerminalNode token, TYPE type, int level, ParserRuleContext declCtx, ArrayList<Ident> parameters) {
        super(token, type, level, declCtx);
        this.parameters = parameters;
        this.returnValue = "VOID";
    }

    public Function(TerminalNode token, TYPE type, int level, ParserRuleContext declCtx,ArrayList<Ident> parameters,  String returnValue) {
        super(token, type, level, declCtx);
        this.parameters = parameters;
        this.returnValue = returnValue;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Getter & Setter
    public ArrayList<Ident> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Ident> parameters) {
        this.parameters = parameters;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "\n(" + token.getSymbol().getLine()+ "," + token.getSymbol().getCharPositionInLine()  + ") ID = " + super.getId() +", Name = " + token.getText() + ", Level = " + level +  ", Type = " + type  + ", Parameters = " + parameters.size() + ", Return = " + returnValue + ", Declaration Context=" + declCtx.getText();
    }

    //------------------------------------------------------------------------------------------------------------------
    //Check if parameter exist
    public Boolean existParameter(String param){
        for (int i = 0; i < getParameters().size(); i++) {
            if(getParameters().get(i).equals(param)){
                return true;
            }
        }
        return false;
    }

    //------------------------------------------------------------------------------------------------------------------


}
