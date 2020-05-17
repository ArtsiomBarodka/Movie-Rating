package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

public class SignUpWithSocialForm extends AbstractForm{
    private String name;

    public SignUpWithSocialForm() {
    }

    public void setName(String name) {
        if(!ValidatorFactory.ACCOUNT_NAME_VALIDATOR.validate(name)){
            violations.addViolation("name", "Invalid name value : " + name);
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

}
