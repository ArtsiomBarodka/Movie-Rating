package epam.my.project.exception;

import java.rmi.ServerException;

public class InternalServerErrorException extends ServerException {
    private static final long serialVersionUID = 710602593591036789L;

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Exception cause) {
        super(message, cause);
    }
}
