package epam.my.project.exception;

/**
 * The type Data storage exception.
 */
public class DataStorageException extends Exception {
    private static final long serialVersionUID = 4204289571442301903L;

    /**
     * Instantiates a new Data storage exception.
     *
     * @param message the message
     */
    public DataStorageException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Data storage exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DataStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
