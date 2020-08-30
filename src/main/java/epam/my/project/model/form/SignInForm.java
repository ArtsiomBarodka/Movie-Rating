package epam.my.project.model.form;

import epam.my.project.component.validator.annotation.Validate;
import epam.my.project.component.validator.model.AbstractForm;
import epam.my.project.component.validator.model.ValidType;

/**
 * The type Sign in form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class SignInForm extends AbstractForm {

    @Validate(type = ValidType.EMAIL, message = "Invalid email value")
    private String email;

    @Validate(type = ValidType.PASSWORD, message = "Invalid password value")
    private String password;

    /**
     * Instantiates a new Sign in form.
     */
    public SignInForm() {
        super();
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
