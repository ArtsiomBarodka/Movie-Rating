package epam.my.project.exception;

public class RetrieveSocialAccountFailedException extends Exception {
    private static final long serialVersionUID = -319370054776894102L;

    public RetrieveSocialAccountFailedException(String message) {
        super(message);
    }

    public RetrieveSocialAccountFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
