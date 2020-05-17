package epam.my.project.exception;

import epam.my.project.model.validation.Violations;

public class ValidationException extends Exception {
    private static final long serialVersionUID = -102825074086443220L;

    private Violations violations;

    public ValidationException(String message, Violations violations) {
        super(message);
        this.violations = violations;
    }

    public Violations getViolations() {
        return violations;
    }
}
