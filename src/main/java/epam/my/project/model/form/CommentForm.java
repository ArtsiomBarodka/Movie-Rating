package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

/**
 * The type Comment form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class CommentForm extends AbstractForm{
    private String content;
    private Integer rating;
    private Integer userId;
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
        if(!ValidatorFactory.COMMENT_CONTENT_VALIDATOR.validate(content)){
            violations.addViolation("content", "Invalid content value :" + content);
        } else {
            this.content = content;
        }
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(String rating) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(rating)){
            violations.addViolation("rating", "Invalid rating value :" + rating);
        } else {
            this.rating = convertToInteger(rating);
        }
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(userId)){
            violations.addViolation("userId", "Invalid user id value : " + userId);
        } else {
            this.userId = convertToInteger(userId);
        }
    }

    /**
     * Sets movie id.
     *
     * @param movieId the movie id
     */
    public void setMovieId(String movieId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(movieId)){
            violations.addViolation("movieId", "Invalid movie id value : " + movieId);
        } else {
            this.movieId = convertToInteger(movieId);
        }
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
