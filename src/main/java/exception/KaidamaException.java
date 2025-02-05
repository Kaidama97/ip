package exception;

/**
 * Exception class to handle Kaidama application errors.
 */
public class KaidamaException extends Exception {

    private final String msg = "OH NO!!!";
    private String errorMsg;


    /**
     * Constructs a new KaidamaException with the specified detail message.
     *
     * @param errorMsg the detail message
     */
    public KaidamaException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return msg + " " + this.errorMsg;
    }

}
