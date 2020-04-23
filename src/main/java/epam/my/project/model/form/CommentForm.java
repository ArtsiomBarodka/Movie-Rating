package epam.my.project.model.form;

import epam.my.project.exception.ValidationException;
import epam.my.project.model.validation.ValidatorFactory;
import java.util.Objects;

public class CommentForm {
    private String content;
    private Integer rating;
    private Integer userId;
    private Integer movieId;

    public CommentForm(String content, String rating, String userId, String movieId) throws ValidationException {
        validate(content, rating, userId, movieId);
        this.content = content;
        this.rating = convert(rating);
        this.userId = convert(userId);
        this.movieId = convert(movieId);
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

    private void validate(String content, String rating, String userId, String movieId) throws ValidationException {
        if(Objects.isNull(content) || content.isEmpty()){
            throw new ValidationException("Invalid content value : " + content, "content");
        }
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(rating)){
            throw new ValidationException("Invalid rating value : " + rating, "rating");
        }
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(userId)){
            throw new ValidationException("Invalid user id value : " + userId, "userId");
        }
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(movieId)){
            throw new ValidationException("Invalid movie id value : " + movieId, "movieId");
        }
    }

    private Integer convert(String s) {
        return Integer.parseInt(s);
    }

}
