package epam.my.project.exception;

import epam.my.project.model.validation.Violations;

/**
 * The type Validation exception.
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
