package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

/**
 * The type Sign up with social form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class SignUpWithSocialForm extends AbstractForm{
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
        if(!ValidatorFactory.ACCOUNT_NAME_VALIDATOR.validate(name)){
            violations.addViolation("name", "Invalid name value : " + name);
        } else {
            this.name = name;
        }
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
