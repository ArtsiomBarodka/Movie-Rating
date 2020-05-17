package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;
import java.sql.Date;
import java.sql.Time;

public class MovieForm extends AbstractForm{
    private String imageLink;
    private String name;
    private String description;
    private Date year;
    private Long budget;
    private Long fees;
    private Time duration;
    private Integer filmmakerId;
    private Integer genreId;
    private Integer categoryId;
    private Integer countryId;

    public MovieForm() {

    }

    public void setImageLink(String imageLink) {
        if(!ValidatorFactory.IMAGE_LINK_VALIDATOR.validate(imageLink)){
            violations.addViolation("imageLink", "Invalid link of image value : " + imageLink);
        } else {
            this.imageLink = imageLink;
        }
    }

    public void setName(String name) {
        if(!ValidatorFactory.MOVIE_NAME_VALIDATOR.validate(name)){
            violations.addViolation("name", "Invalid name value : " + name);
        } else {
            this.name = name;
        }
    }

    public void setDescription(String description) {
        if(!ValidatorFactory.MOVIE_DESCRIPTION_VALIDATOR.validate(description)){
            violations.addViolation("description", "Invalid description value : " + description);
        } else {
            this.description = description;
        }
    }

    public void setYear(String year) {
        if(!ValidatorFactory.MOVIE_YEAR_VALIDATOR.validate(year)){
            violations.addViolation("year", "Invalid year value : " + year);
        } else {
            this.year =  convertToDate(year);
        }
    }

    public void setBudget(String budget) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(budget)){
            violations.addViolation("budget", "Invalid budget value : " + budget);
        } else {
            this.budget = convertToLong(budget);
        }
    }

    public void setFees(String fees) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(fees)){
            violations.addViolation("fees", "Invalid fees value : " + fees);
        } else {
            this.fees = convertToLong(fees);
        }
    }

    public void setDuration(String duration) {
        if(!ValidatorFactory.MOVIE_DURATION_VALIDATOR.validate(duration)){
            violations.addViolation("duration", "Invalid duration value : " + duration);
        } else {
            this.duration = convertToTime(duration);
        }
    }

    public void setFilmmakerId(String filmmakerId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(filmmakerId)){
            violations.addViolation("filmmakerId", "Invalid filmmaker id value : " + filmmakerId);
        } else {
            this.filmmakerId = convertToInteger(filmmakerId);
        }
    }

    public void setGenreId(String genreId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(genreId)){
            violations.addViolation("genreId", "Invalid genre id value : " + genreId);
        } else {
            this.genreId = convertToInteger(genreId);
        }
    }

    public void setCategoryId(String categoryId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(categoryId)){
            violations.addViolation("categoryId", "Invalid category id value : " + categoryId);
        } else {
            this.categoryId = convertToInteger(categoryId);
        }
    }

    public void setCountryId(String countryId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(countryId)){
            violations.addViolation("countryId", "Invalid country id value : " + countryId);
        } else {
            this.countryId = convertToInteger(countryId);
        }
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getYear() {
        return year;
    }

    public Long getBudget() {
        return budget;
    }

    public Long getFees() {
        return fees;
    }

    public Time getDuration() {
        return  duration;
    }

    public Integer getFilmmakerId() {
        return filmmakerId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getCountryId() {
        return countryId;
    }
}
