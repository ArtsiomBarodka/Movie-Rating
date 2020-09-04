package epam.my.project.model.form;

import epam.my.project.component.validator.annotation.Validate;
import epam.my.project.component.validator.model.AbstractForm;
import epam.my.project.component.validator.model.ValidType;

import java.util.Objects;

/**
 * The type User form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class UserForm extends AbstractForm {
    @Validate(type = ValidType.ACCOUNT_NAME, message = "Invalid name value")
    private String name;

    @Validate(type = ValidType.IMAGE_LINK, message = "Invalid link of image value")
    private String imageLink;

    @Validate(type = ValidType.NUMBER, message = "Invalid rating value")
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
        this.name = name;
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
        this.imageLink = imageLink;
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
        this.rating = Integer.parseInt(rating);
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
        isBanned = Objects.nonNull(banned);
    }
}
