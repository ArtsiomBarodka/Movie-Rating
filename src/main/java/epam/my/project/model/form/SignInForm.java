package epam.my.project.model.form;

import epam.my.project.exception.ValidationException;
import epam.my.project.model.validation.ValidatorFactory;

public class SignInForm {
    private String email;
    private String password;

    public SignInForm(String email, String password) throws ValidationException {
        this.email = email;
        this.password = password;
        validate(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private void validate(String email, String password) throws ValidationException {
        if(!ValidatorFactory.EMAIL_VALIDATOR.validate(email)){
            throw new ValidationException("Invalid email value : " + email, "email");
        }
        if(!ValidatorFactory.PASSWORD_VALIDATOR.validate(password)){
            throw new ValidationException("Invalid password value : " + password, "password");
        }
    }

}
