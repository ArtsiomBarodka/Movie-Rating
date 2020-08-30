package epam.my.project.model.form;

import epam.my.project.component.validator.annotation.Validate;
import epam.my.project.component.validator.model.AbstractForm;
import epam.my.project.component.validator.model.ValidType;

/**
 * The type Sign up form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class SignUpForm extends AbstractForm {
    @Validate(type = ValidType.ACCOUNT_NAME, message = "Invalid name value")
    private String name;

    @Validate(type = ValidType.EMAIL, message = "Invalid email value")
    private String email;

    @Validate(type = ValidType.PASSWORD, message = "Invalid password value")
    private String password;

    /**
     * Instantiates a new Sign up form.
     */
    public SignUpForm() {
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
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

}
