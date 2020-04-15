package epam.my.project.exception;

public class InternalServerErrorException extends Exception {
    private static final long serialVersionUID = 710602593591036789L;

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
