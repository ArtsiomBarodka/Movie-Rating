package epam.my.project.component.validator.model;

/**
 * The type Abstract form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public abstract class AbstractForm {
    /**
     * The Violations.
     */
    protected Violations violations;

    /**
     * Instantiates a new Abstract form.
     */
    protected AbstractForm() {
        this.violations = new Violations();
    }

    /**
     * Get violations violations.
     *
     * @return the violations
     */
    public Violations getViolations(){
        return violations;
    }
}
