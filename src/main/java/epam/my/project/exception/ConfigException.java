package epam.my.project.exception;

/**
 * The type Config exception.
 */
public class ConfigException extends RuntimeException {
    private static final long serialVersionUID = -433922203248842841L;

    /**
     * Instantiates a new Config exception.
     *
     * @param message the message
     */
    public ConfigException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Config exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
