package com.epam.mrating.service.exception;

import com.epam.mrating.component.validator.model.Violations;

/**
 * The type Validation exception.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class ValidationException extends Exception {
    private static final long serialVersionUID = -102825074086443220L;

    private final Violations violations;

    /**
     * Instantiates a new Validation exception.
     *
     * @param message    the message
     * @param violations the violations
     */
    public ValidationException(String message, Violations violations) {
        super(message);
        this.violations = violations;
    }

    /**
     * Gets violations.
     *
     * @return the violations
     */
    public Violations getViolations() {
        return violations;
    }
}
