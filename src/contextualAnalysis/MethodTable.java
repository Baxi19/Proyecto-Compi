package contextualAnalysis;

import java.util.ArrayList;

public class MethodTable {
    ArrayList<Object> table;
    private int currentLevel;
    //TODO: Trabajar en la cantidad de parametros, puede ser una lista
    //Constructor
    public MethodTable(ArrayList<Object> table) {
        this.table = table;
        this.currentLevel = -1;
    }

    //Getter & Setter
    public ArrayList<Object> getTable() {
        return table;
    }

    public void setTable(ArrayList<Object> table) {
        this.table = table;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    //Methods


}
