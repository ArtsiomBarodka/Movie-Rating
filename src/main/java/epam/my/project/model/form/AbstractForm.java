package epam.my.project.model.form;

import epam.my.project.model.validation.Violations;
import java.util.Objects;


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
    Violations violations;

    /**
     * Instantiates a new Abstract form.
     */
    AbstractForm() {
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

    /**
     * Convert to integer integer.
     *
     * @param s the s
     * @return the integer
     */
    Integer convertToInteger(String s){
        return Integer.parseInt(s);
    }

    /**
     * Convert to long long.
     *
     * @param s the s
     * @return the long
     */
    Long convertToLong(String s){
        return Long.parseLong(s);
    }

    /**
     * Convert to year short.
     *
     * @param year the year
     * @return the short
     */
    Short convertToYear(String year) {
        return Short.valueOf(year);
    }

    /**
     * Convert to boolean boolean.
     *
     * @param s the s
     * @return the boolean
     */
    Boolean convertToBoolean(String s){
        return Objects.nonNull(s);
    }
}
