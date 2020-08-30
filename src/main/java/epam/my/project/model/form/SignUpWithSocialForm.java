package epam.my.project.model.form;

import epam.my.project.component.validator.annotation.Validate;
import epam.my.project.component.validator.model.AbstractForm;
import epam.my.project.component.validator.model.ValidType;

/**
 * The type Sign up with social form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class SignUpWithSocialForm extends AbstractForm {
    @Validate(type = ValidType.ACCOUNT_NAME, message = "Invalid name value")
    private String name;

    /**
     * Instantiates a new Sign up with social form.
     */
    public SignUpWithSocialForm() {
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
