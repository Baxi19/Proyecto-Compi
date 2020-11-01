package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

//Class Function, will represent "Iden" as method's declaration
public class Function extends Ident {
    public ArrayList<Token> parameters;
    public Object returnValue;

    //Constructor
    public Function(Token id, int level, ParserRuleContext declCtx) {
        super(id, level, declCtx);
        this.parameters = new ArrayList<>();
        this.returnValue = returnValue;
    }

    //Getter & Setter
    public ArrayList<Token> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Token> parameters) {
        this.parameters = parameters;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

}
