package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

public class CommentForm extends AbstractForm{
    private String content;
    private Integer rating;
    private Integer userId;
    private Integer movieId;

    public CommentForm() {

    }

    public void setContent(String content) {
        if(!ValidatorFactory.COMMENT_CONTENT_VALIDATOR.validate(content)){
            violations.addViolation("content", "Invalid content value :" + content);
        } else {
            this.content = content;
        }
    }

    public void setRating(String rating) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(rating)){
            violations.addViolation("rating", "Invalid rating value :" + rating);
        } else {
            this.rating = convertToInteger(rating);
        }
    }

    public void setUserId(String userId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(userId)){
            violations.addViolation("userId", "Invalid user id value : " + userId);
        } else {
            this.userId = convertToInteger(userId);
        }
    }

    public void setMovieId(String movieId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(movieId)){
            violations.addViolation("movieId", "Invalid movie id value : " + movieId);
        } else {
            this.movieId = convertToInteger(movieId);
        }
    }

    public String getContent() {
        return content;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getMovieId() {
        return movieId;
    }
}
