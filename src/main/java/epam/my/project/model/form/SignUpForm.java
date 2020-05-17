package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

public class SignUpForm extends AbstractForm{
    private String name;
    private String email;
    private String password;

    public SignUpForm() {
    }

    public void setName(String name) {
        if(!ValidatorFactory.ACCOUNT_NAME_VALIDATOR.validate(name)){
            violations.addViolation("name", "Invalid name value : " + name);
        } else {
            this.name = name;
        }
    }

    public void setEmail(String email) {
        if(!ValidatorFactory.ACCOUNT_EMAIL_VALIDATOR.validate(email)){
            violations.addViolation("email","Invalid email value : " + email);
        } else {
            this.email = email;
        }
    }

    public void setPassword(String password) {
        if(!ValidatorFactory.ACCOUNT_PASSWORD_VALIDATOR.validate(password)){
            violations.addViolation("password","Invalid password value : " + password);
        } else {
            this.password = password;
        }
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

}
