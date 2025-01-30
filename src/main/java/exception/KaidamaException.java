package exception;

public class KaidamaException extends Exception {
    private final String msg  = "OH NO!!!";
    private String errorMsg;
    public KaidamaException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString(){
        return msg + " " + this.errorMsg;
    }

}
