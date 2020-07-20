package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

/**
 * The type Sign up form.
 */
public class SignUpForm extends AbstractForm{
    private String name;
    private String email;
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
        if(!ValidatorFactory.ACCOUNT_NAME_VALIDATOR.validate(name)){
            violations.addViolation("name", "Invalid name value : " + name);
        } else {
            this.name = name;
        }
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        if(!ValidatorFactory.ACCOUNT_EMAIL_VALIDATOR.validate(email)){
            violations.addViolation("email","Invalid email value : " + email);
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
            violations.addViolation("password","Invalid password value : " + password);
        } else {
            this.password = password;
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
