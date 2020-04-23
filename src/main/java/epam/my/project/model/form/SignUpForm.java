package epam.my.project.model.form;

import epam.my.project.exception.ValidationException;
import epam.my.project.model.validation.ValidatorFactory;

public class SignUpForm {
    private String name;
    private String email;
    private String password;

    public SignUpForm(String name, String email, String password) throws ValidationException {
        this.name = name;
        this.email = email;
        this.password = password;
        validate(name, email, password);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private void validate(String name, String email, String password) throws ValidationException {
        if(!ValidatorFactory.NAME_VALIDATOR.validate(name)){
            throw new ValidationException("Invalid name value : " + name, "name");
        }
        if(!ValidatorFactory.EMAIL_VALIDATOR.validate(email)){
            throw new ValidationException("Invalid email value : " + email, "email");
        }
        if(!ValidatorFactory.PASSWORD_VALIDATOR.validate(password)){
            throw new ValidationException("Invalid password value : " + password, "password");
        }
    }
}
