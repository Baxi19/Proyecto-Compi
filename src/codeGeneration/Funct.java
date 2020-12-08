package codeGeneration;

public class Funct {
    public long id;
    public String name;
    public int param;

    public Funct(long id, String name, int param) {
        this.id = id;
        this.name = name;
        this.param = param;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }

}
