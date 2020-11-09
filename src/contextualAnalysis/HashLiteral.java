package contextualAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import utils.TYPE;

import java.util.ArrayList;

public class HashLiteral extends Ident {
    public String name;
    public ArrayList<Ident> data;

    //Contructor
    public HashLiteral(TerminalNode token, TYPE type, int level, ParserRuleContext declCtx, String name) {
        super(token, type, level, declCtx);
        this.name = name;
        this.data = new ArrayList<>();
    }

    //Setter & Getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ident> getData() {
        return data;
    }

    public void setData(ArrayList<Ident> data) {
        this.data = data;
    }

    //Return the size of Hash content
    public int len(){
        return this.getData().size();
    }

    //Return first element in Hash content
    public Ident first(){
        return this.getData().get(0);
    }

    //Return last element in Hash content
    public Ident last(){
        return this.getData().get(len()-1);
    }

    //Return Hash content with out first element
    public ArrayList<Ident> rest(){
        this.getData().remove(0);
        return this.getData();
    }

    //Insert value
    public Boolean push(Ident value){
        try {
            this.getData().add(value);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String toString() {
        return "\n(" + token.getSymbol().getLine()+ "," + token.getSymbol().getCharPositionInLine()  + ") ID = " + super.getId() +", Name = " + token.getText() + ", Level = " + level+  ", Type = " + type  + ", HASH Size = " + len() + ", Declaration Context=" + declCtx.getText();
    }
}
