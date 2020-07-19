package epam.my.project.model.validation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Violations implements Serializable {
    private Map<String, String> violations;

    public Violations() {
        this.violations = new HashMap<>();
    }

    public void addViolation(String field, String message){
        violations.put(field, message);
    }

    public boolean hasError(String field) {
        return violations.containsKey(field);
    }

    public boolean hasErrors() {
        return !violations.isEmpty();
    }

    public String getMessage(String field){
        return violations.get(field);
    }
}
