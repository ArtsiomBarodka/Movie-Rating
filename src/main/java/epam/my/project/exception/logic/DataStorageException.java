package epam.my.project.exception.logic;

public class DataStorageException extends RuntimeException {
    private static final long serialVersionUID = 4204289571442301903L;

    public DataStorageException(String message) {
        super(message);
    }

    public DataStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
