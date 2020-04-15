package epam.my.project.exception;

public class ValidationException extends Exception {
    private static final long serialVersionUID = -102825074086443220L;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
