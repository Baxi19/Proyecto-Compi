package errors;

//Class will be used to get best info from errors
public class Error {
    public int row;
    public int colum;
    public String msj;
    public String type;

    public Error(int row, int colum, String msj, String type) {
        this.row = row;
        this.colum = colum;
        this.msj = msj;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColum() {
        return colum;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
