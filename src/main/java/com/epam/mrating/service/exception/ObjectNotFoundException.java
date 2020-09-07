package com.epam.mrating.service.exception;

/**
 * The type Object not found exception.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class ObjectNotFoundException extends Exception {
    private static final long serialVersionUID = -4778296552637580453L;

    /**
     * Instantiates a new Object not found exception.
     *
     * @param message the message
     */
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
