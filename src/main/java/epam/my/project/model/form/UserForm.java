package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

/**
 * The type User form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class UserForm extends AbstractForm{
    private String name;
    private String imageLink;
    private Integer rating;
    private Boolean isBanned;

    /**
     * Instantiates a new User form.
     */
    public UserForm() {

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
     * Gets image link.
     *
     * @return the image link
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     * Sets image link.
     *
     * @param imageLink the image link
     */
    public void setImageLink(String imageLink) {
        if(!ValidatorFactory.IMAGE_LINK_VALIDATOR.validate(imageLink)){
            violations.addViolation("imageLink", "Invalid link of image value : " + imageLink);
        } else {
            this.imageLink = imageLink;
        }
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(String rating) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(rating)){
            violations.addViolation("rating", "Invalid rating value : " + rating);
        } else {
            this.rating = convertToInteger(rating);
        }
    }

    /**
     * Is banned boolean.
     *
     * @return the boolean
     */
    public Boolean isBanned() {
        return isBanned;
    }

    /**
     * Sets banned.
     *
     * @param banned the banned
     */
    public void setBanned(String banned) {
        isBanned = convertToBoolean(banned);
    }
}
