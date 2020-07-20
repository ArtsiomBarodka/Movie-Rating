package epam.my.project.exception;

/**
 * The type Retrieve social account failed exception.
 */
public class RetrieveSocialAccountFailedException extends Exception {
    private static final long serialVersionUID = -319370054776894102L;

    /**
     * Instantiates a new Retrieve social account failed exception.
     *
     * @param message the message
     */
    public RetrieveSocialAccountFailedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Retrieve social account failed exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public RetrieveSocialAccountFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
