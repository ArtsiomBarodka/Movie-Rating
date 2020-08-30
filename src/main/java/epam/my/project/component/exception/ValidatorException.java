package epam.my.project.component.exception;

/**
 * The type Validator exception.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class ValidatorException extends RuntimeException{
    private static final long serialVersionUID = 3647389733419842900L;

    /**
     * Instantiates a new Validator exception.
     *
     * @param message the message
     */
    public ValidatorException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Validator exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
