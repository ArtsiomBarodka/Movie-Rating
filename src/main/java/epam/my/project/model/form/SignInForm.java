package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

/**
 * The type Sign in form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class SignInForm extends AbstractForm{
    private String email;
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
        if(!ValidatorFactory.ACCOUNT_EMAIL_VALIDATOR.validate(email)){
            violations.addViolation("email", "Invalid email value : " + email);
        } else {
            this.email = email;
        }
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        if(!ValidatorFactory.ACCOUNT_PASSWORD_VALIDATOR.validate(password)){
            violations.addViolation("password", "Invalid password value : " + password);
        } else {
            this.password = password;

        }
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
