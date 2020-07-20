package epam.my.project.model.validation;

/**
 * The interface Validator.
 *
 * @param <F> the type parameter
 */
@FunctionalInterface
public interface Validator<F> {
    /**
     * Validate boolean.
     *
     * @param value the value
     * @return the boolean
     */
    boolean validate(F value);
}
