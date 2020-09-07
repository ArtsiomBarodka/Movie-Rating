package com.epam.mrating.model.form;

import com.epam.mrating.component.validator.annotation.Validate;
import com.epam.mrating.component.validator.model.ValidType;
import com.epam.mrating.component.validator.model.AbstractForm;

/**
 * The type Movie form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class MovieForm extends AbstractForm {
    @Validate(type = ValidType.IMAGE_LINK, message = "Invalid link of image value")
    private String imageLink;

    @Validate(type = ValidType.MOVIE_NAME, message = "Invalid name value")
    private String name;

    @Validate(type = ValidType.DESCRIPTION, message = "Invalid description value")
    private String description;

    @Validate(type = ValidType.YEAR, message = "Invalid year value")
    private Short year;

    @Validate(type = ValidType.NUMBER, message = "Invalid budget value")
    private Long budget;

    @Validate(type = ValidType.NUMBER, message = "Invalid fees value")
    private Long fees;

    @Validate(type = ValidType.DURATION, message = "Invalid duration value")
    private String duration;

    @Validate(type = ValidType.NUMBER, message = "Invalid filmmaker id value")
    private Integer filmmakerId;

    @Validate(type = ValidType.NUMBER, message = "Invalid genre id value")
    private Integer genreId;

    @Validate(type = ValidType.NUMBER, message = "Invalid category id value")
    private Integer categoryId;

    @Validate(type = ValidType.NUMBER, message = "Invalid country id value")
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
        this.imageLink = imageLink;
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
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(String year) {
        this.year =  Short.valueOf(year);
    }

    /**
     * Sets budget.
     *
     * @param budget the budget
     */
    public void setBudget(String budget) {
        this.budget = Long.parseLong(budget);
    }

    /**
     * Sets fees.
     *
     * @param fees the fees
     */
    public void setFees(String fees) {
        this.fees = Long.parseLong(fees);
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Sets filmmaker id.
     *
     * @param filmmakerId the filmmaker id
     */
    public void setFilmmakerId(String filmmakerId) {
        this.filmmakerId = Integer.parseInt(filmmakerId);
    }

    /**
     * Sets genre id.
     *
     * @param genreId the genre id
     */
    public void setGenreId(String genreId) {
        this.genreId = Integer.parseInt(genreId);
    }

    /**
     * Sets category id.
     *
     * @param categoryId the category id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = Integer.parseInt(categoryId);
    }

    /**
     * Sets country id.
     *
     * @param countryId the country id
     */
    public void setCountryId(String countryId) {
        this.countryId = Integer.parseInt(countryId);
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
