package epam.my.project.model.entity;

import java.sql.Timestamp;
import java.util.List;


/**
 * The type Movie.
 */
public class Movie extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 6363780319231117683L;

    private String uid;
    private String imageLink;
    private String name;
    private String description;
    private Short year;
    private Long budget;
    private Long fees;
    private String duration;
    private double rating;
    private Timestamp added;
    private Filmmaker filmmaker;
    private Genre genre;
    private Category category;
    private Country country;
    private List<Comment> comments;

    /**
     * Instantiates a new Movie.
     */
    public Movie() {
    }

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets uid.
     *
     * @param uid the uid
     */
    public void setUid(String uid) {
        this.uid = uid;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
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
     * Gets year.
     *
     * @return the year
     */
    public Short getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(Short year) {
        this.year = year;
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
     * Sets budget.
     *
     * @param budget the budget
     */
    public void setBudget(Long budget) {
        this.budget = budget;
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
     * Sets fees.
     *
     * @param fees the fees
     */
    public void setFees(Long fees) {
        this.fees = fees;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
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
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets added.
     *
     * @return the added
     */
    public Timestamp getAdded() {
        return added;
    }

    /**
     * Sets added.
     *
     * @param added the added
     */
    public void setAdded(Timestamp added) {
        this.added = added;
    }

    /**
     * Gets filmmaker.
     *
     * @return the filmmaker
     */
    public Filmmaker getFilmmaker() {
        return filmmaker;
    }

    /**
     * Sets filmmaker.
     *
     * @param filmmaker the filmmaker
     */
    public void setFilmmaker(Filmmaker filmmaker) {
        this.filmmaker = filmmaker;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "uid='" + uid + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", budget=" + budget +
                ", fees=" + fees +
                ", duration=" + duration +
                ", rating=" + rating +
                ", added=" + added +
                ", filmmaker=" + filmmaker +
                ", genre=" + genre +
                ", category=" + category +
                ", country=" + country +
                ", comments=" + comments +
                ", id=" + id +
                '}';
    }
}
