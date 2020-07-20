package epam.my.project.model.form;

import epam.my.project.model.validation.ValidatorFactory;

/**
 * The type Movie form.
 */
public class MovieForm extends AbstractForm{
    private String imageLink;
    private String name;
    private String description;
    private Short year;
    private Long budget;
    private Long fees;
    private String duration;
    private Integer filmmakerId;
    private Integer genreId;
    private Integer categoryId;
    private Integer countryId;

    /**
     * Instantiates a new Movie form.
     */
    public MovieForm() {

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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        if(!ValidatorFactory.MOVIE_NAME_VALIDATOR.validate(name)){
            violations.addViolation("name", "Invalid name value : " + name);
        } else {
            this.name = name;
        }
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        if(!ValidatorFactory.MOVIE_DESCRIPTION_VALIDATOR.validate(description)){
            violations.addViolation("description", "Invalid description value : " + description);
        } else {
            this.description = description;
        }
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(String year) {
        if(!ValidatorFactory.MOVIE_YEAR_VALIDATOR.validate(year)){
            violations.addViolation("year", "Invalid year value : " + year);
        } else {
            this.year =  convertToYear(year);
        }
    }

    /**
     * Sets budget.
     *
     * @param budget the budget
     */
    public void setBudget(String budget) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(budget)){
            violations.addViolation("budget", "Invalid budget value : " + budget);
        } else {
            this.budget = convertToLong(budget);
        }
    }

    /**
     * Sets fees.
     *
     * @param fees the fees
     */
    public void setFees(String fees) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(fees)){
            violations.addViolation("fees", "Invalid fees value : " + fees);
        } else {
            this.fees = convertToLong(fees);
        }
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(String duration) {
        if(!ValidatorFactory.MOVIE_DURATION_VALIDATOR.validate(duration)){
            violations.addViolation("duration", "Invalid duration value : " + duration);
        } else {
            this.duration = duration;
        }
    }

    /**
     * Sets filmmaker id.
     *
     * @param filmmakerId the filmmaker id
     */
    public void setFilmmakerId(String filmmakerId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(filmmakerId)){
            violations.addViolation("filmmakerId", "Invalid filmmaker id value : " + filmmakerId);
        } else {
            this.filmmakerId = convertToInteger(filmmakerId);
        }
    }

    /**
     * Sets genre id.
     *
     * @param genreId the genre id
     */
    public void setGenreId(String genreId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(genreId)){
            violations.addViolation("genreId", "Invalid genre id value : " + genreId);
        } else {
            this.genreId = convertToInteger(genreId);
        }
    }

    /**
     * Sets category id.
     *
     * @param categoryId the category id
     */
    public void setCategoryId(String categoryId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(categoryId)){
            violations.addViolation("categoryId", "Invalid category id value : " + categoryId);
        } else {
            this.categoryId = convertToInteger(categoryId);
        }
    }

    /**
     * Sets country id.
     *
     * @param countryId the country id
     */
    public void setCountryId(String countryId) {
        if(!ValidatorFactory.IS_NUMBER_VALUE.validate(countryId)){
            violations.addViolation("countryId", "Invalid country id value : " + countryId);
        } else {
            this.countryId = convertToInteger(countryId);
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public Short getYear() {
        return year;
    }

    /**
     * Gets budget.
     *
     * @return the budget
     */
    public Long getBudget() {
        return budget;
    }

    /**
     * Gets fees.
     *
     * @return the fees
     */
    public Long getFees() {
        return fees;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public String getDuration() {
        return  duration;
    }

    /**
     * Gets filmmaker id.
     *
     * @return the filmmaker id
     */
    public Integer getFilmmakerId() {
        return filmmakerId;
    }

    /**
     * Gets genre id.
     *
     * @return the genre id
     */
    public Integer getGenreId() {
        return genreId;
    }

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Gets country id.
     *
     * @return the country id
     */
    public Integer getCountryId() {
        return countryId;
    }
}
