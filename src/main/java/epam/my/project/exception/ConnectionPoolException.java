package epam.my.project.exception;

/**
 * The type Connection pool exception.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class ConnectionPoolException extends RuntimeException {
    private static final long serialVersionUID = 8803733756957855357L;

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message the message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
