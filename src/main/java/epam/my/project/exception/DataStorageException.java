package epam.my.project.exception;

public class DataStorageException extends Exception {
    private static final long serialVersionUID = 4204289571442301903L;

    public DataStorageException(String message) {
        super(message);
    }

    public DataStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
