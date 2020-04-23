package epam.my.project.exception;

public class ConnectionPoolException extends RuntimeException {
    private static final long serialVersionUID = 8803733756957855357L;

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
