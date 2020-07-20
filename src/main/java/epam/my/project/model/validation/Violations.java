package epam.my.project.model.validation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Violations.
 */
public class Violations implements Serializable {
    private Map<String, String> violations;

    /**
     * Instantiates a new Violations.
     */
    public Violations() {
        this.violations = new HashMap<>();
    }

    /**
     * Add violation.
     *
     * @param field   the field
     * @param message the message
     */
    public void addViolation(String field, String message){
        violations.put(field, message);
    }

    /**
     * Has error boolean.
     *
     * @param field the field
     * @return the boolean
     */
    public boolean hasError(String field) {
        return violations.containsKey(field);
    }

    /**
     * Has errors boolean.
     *
     * @return the boolean
     */
    public boolean hasErrors() {
        return !violations.isEmpty();
    }

    /**
     * Get message string.
     *
     * @param field the field
     * @return the string
     */
    public String getMessage(String field){
        return violations.get(field);
    }
}
