package epam.my.project.exception;

import javax.servlet.ServletException;

public class AccessDeniedException extends ServletException {
    private static final long serialVersionUID = -2988556117797256376L;

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Exception cause) {
        super(message, cause);
    }
}
