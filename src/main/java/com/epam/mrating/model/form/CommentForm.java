package com.epam.mrating.model.form;

import com.epam.mrating.component.validator.annotation.Validate;
import com.epam.mrating.component.validator.model.AbstractForm;
import com.epam.mrating.component.validator.model.ValidType;

/**
 * The type Comment form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class CommentForm extends AbstractForm {
    @Validate(type = ValidType.CONTENT, message = "Invalid content value")
    private String content;

    @Validate(type = ValidType.NUMBER, message = "Invalid rating value")
    private Integer rating;

    @Validate(type = ValidType.NUMBER, message = "Invalid user id value")
    private Integer userId;

    @Validate(type = ValidType.NUMBER, message = "Invalid movie id value")
    private Integer movieId;

    /**
     * Instantiates a new Comment form.
     */
    public CommentForm() {

    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
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
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = Integer.parseInt(userId);
    }

    /**
     * Sets movie id.
     *
     * @param movieId the movie id
     */
    public void setMovieId(String movieId) {
        this.movieId = Integer.parseInt(movieId);
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
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
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Gets movie id.
     *
     * @return the movie id
     */
    public Integer getMovieId() {
        return movieId;
    }
}
