package epam.my.project.exception;

public abstract class AbstractException extends RuntimeException {
    private static final long serialVersionUID = 63036958543734215L;
    private final int code;

    public AbstractException(String message, int code) {
        super(message);
        this.code = code;
    }

    public AbstractException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
