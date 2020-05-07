package epam.my.project.exception;

import javax.servlet.ServletException;

public class ObjectNotFoundException extends ServletException {
    private static final long serialVersionUID = -4778296552637580453L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
