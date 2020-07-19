package epam.my.project.exception;

public class PageException extends RuntimeException {
    private static final long serialVersionUID = 9064680761453683168L;
    private final int code;

    public PageException(String message, int code) {
        super(message);
        this.code = code;
    }

    public PageException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
