package epam.my.project.exception.logic;

public class ConfigException extends RuntimeException {
    private static final long serialVersionUID = -433922203248842841L;

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
