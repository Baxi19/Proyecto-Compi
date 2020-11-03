package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

//Class Function, will represent "Iden" as method's declaration
public class Function extends Ident {
    public ArrayList<String> parameters;
    public Object returnValue;

    //------------------------------------------------------------------------------------------------------------------
    //Constructor
    public Function(Token token, String type, int level, ParserRuleContext declCtx, ArrayList<String> parameters) {
        super(token, type, level, declCtx);
        this.parameters = parameters;
        this.returnValue = null;
    }

    public Function(Token token, String type, int level, ParserRuleContext declCtx,ArrayList<String> parameters,  Object returnValue) {
        super(token, type, level, declCtx);
        this.type = "MET";
        this.parameters = parameters;
        this.returnValue = returnValue;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Getter & Setter
    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "\n(" + token.getLine()+ "," + token.getCharPositionInLine()  + ") ID = " + super.getId() +", Name = " + token.getText() + ", Level = " + level +  ", Type = " + type  + ", Parameters = " + parameters.size() + ", Declaration Context=" + declCtx.getText();
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
