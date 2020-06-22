package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

import java.util.Objects;

public class UserForm extends AbstractForm{
    private String name;
    private String imageLink;
    private Integer rating;
    private Boolean isBanned;

    public UserForm() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!ValidatorFactory.ACCOUNT_NAME_VALIDATOR.validate(name)){
            violations.addViolation("name", "Invalid name value : " + name);
        } else {
            this.name = name;
        }
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        if(!ValidatorFactory.IMAGE_LINK_VALIDATOR.validate(imageLink)){
            violations.addViolation("imageLink", "Invalid link of image value : " + imageLink);
        } else {
            this.imageLink = imageLink;
        }
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(String rating) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(rating)){
            violations.addViolation("rating", "Invalid rating value : " + rating);
        } else {
            this.rating = convertToInteger(rating);
        }
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(String banned) {
        isBanned = convertToBoolean(banned);
    }
}
