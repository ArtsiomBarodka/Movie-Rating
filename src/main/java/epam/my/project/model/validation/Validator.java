package epam.my.project.model.validation;

/**
 * The interface Validator.
 *
 * @param <F> the type parameter
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
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
