package epam.my.project.exception;

/**
 * The type Access denied exception.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class AccessDeniedException extends Exception {
    private static final long serialVersionUID = -2988556117797256376L;

    /**
     * Instantiates a new Access denied exception.
     *
     * @param message the message
     */
    public AccessDeniedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Access denied exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AccessDeniedException(String message, Exception cause) {
        super(message, cause);
    }
}
