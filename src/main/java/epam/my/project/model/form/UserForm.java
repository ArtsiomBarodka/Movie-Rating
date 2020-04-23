package epam.my.project.model.form;

import epam.my.project.exception.ValidationException;
import epam.my.project.model.validation.ValidatorFactory;

import java.util.Objects;

public class UserForm {
    private String name;
    private String imageLink;
    private Integer rating;
    private boolean isBanned;

    public UserForm(String name, String imageLink, String rating, boolean isBanned) throws ValidationException {
        validate(name, imageLink, rating);
        this.name = name;
        this.imageLink = imageLink;
        this.rating = convert(rating);
        this.isBanned = isBanned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public boolean isBanned() {
        return isBanned;
    }

    private void validate(String name, String imageLink, String rating) throws ValidationException {
        if(!ValidatorFactory.NAME_VALIDATOR.validate(name)){
            throw new ValidationException("Invalid name value : " + name, "name");
        }
        if(Objects.isNull(imageLink) || imageLink.isEmpty()){
            throw new ValidationException("Invalid link of image value : " + imageLink, "imageLink");
        }
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(rating)){
            throw new ValidationException("Invalid rating value : " + rating, "rating");
        }
    }

    private Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
