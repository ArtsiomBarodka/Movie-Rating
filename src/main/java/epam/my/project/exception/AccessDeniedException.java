package epam.my.project.exception;

public class AccessDeniedException extends Exception {
    private static final long serialVersionUID = -2988556117797256376L;

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
