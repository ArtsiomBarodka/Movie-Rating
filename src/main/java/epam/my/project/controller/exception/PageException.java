package epam.my.project.controller.exception;

/**
 * The type Page exception.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class PageException extends RuntimeException {
    private static final long serialVersionUID = 9064680761453683168L;

    private final int code;

    /**
     * Instantiates a new Page exception.
     *
     * @param message the message
     * @param code    the code
     */
    public PageException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     * Instantiates a new Page exception.
     *
     * @param message the message
     * @param cause   the cause
     * @param code    the code
     */
    public PageException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }
}
