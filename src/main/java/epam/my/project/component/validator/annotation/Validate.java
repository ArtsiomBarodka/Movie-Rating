package epam.my.project.component.validator.annotation;

import epam.my.project.component.validator.model.ValidType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Validate.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.TYPE})
public @interface Validate{
    /**
     * Type valid type.
     *
     * @return the valid type
     */
    public ValidType type();

    /**
     * Message string.
     *
     * @return the string
     */
    public String message();
}
