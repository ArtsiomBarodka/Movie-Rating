package epam.my.project.exception;

/**
 * The type Object not found exception.
 */
public class ObjectNotFoundException extends Exception {
    private static final long serialVersionUID = -4778296552637580453L;

    /**
     * Instantiates a new Object not found exception.
     *
     * @param message the message
     */
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
