package epam.my.project.exception;

public class ObjectNotFoundException extends Exception {
    private static final long serialVersionUID = -4778296552637580453L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
