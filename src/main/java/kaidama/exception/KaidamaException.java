package kaidama.exception;

/**
 * Exception class to handle Kaidama application errors.
 */
public class KaidamaException extends Exception {

    private final String msg  = "OH NO!!!";
    private String errorMsg;

    /**
     * Constructs a KaidamaException with a specified error message.
     *
     * @param errorMsg The error message for this exception.
     */
    public KaidamaException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString(){
        return msg + " " + this.errorMsg;
    }

}
