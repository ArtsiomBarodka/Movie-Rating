package epam.my.project.exception;

/**
 * The type Internal server error exception.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class InternalServerErrorException extends Exception {
    private static final long serialVersionUID = 710602593591036789L;

    /**
     * Instantiates a new Internal server error exception.
     *
     * @param message the message
     */
    public InternalServerErrorException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Internal server error exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public InternalServerErrorException(String message, Exception cause) {
        super(message, cause);
    }
}
