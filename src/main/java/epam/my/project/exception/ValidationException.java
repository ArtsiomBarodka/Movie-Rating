package epam.my.project.exception;

public class ValidationException extends Exception {
    private static final long serialVersionUID = -102825074086443220L;

    private String field;

    public ValidationException(String message, String field) {
        super(message);
        this.field = field;
    }

    public ValidationException(String message, Throwable cause, String field) {
        super(message, cause);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
