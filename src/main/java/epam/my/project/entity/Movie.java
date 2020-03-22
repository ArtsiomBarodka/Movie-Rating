package epam.my.project.entity;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;


public class Movie extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 6363780319231117683L;

    private String imageLink;
    private String name;
    private String description;
    private Date year;
    private Long budget;
    private Long fees;
    private Time duration;
    private double rating;
    private Timestamp added;
    private Filmmaker filmmaker;
    private Genre genre;
    private Category category;
    private Country country;
    private List<Comment> comments;

    public Movie() {
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getFees() {
        return fees;
    }

    public void setFees(Long fees) {
        this.fees = fees;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    public Filmmaker getFilmmaker() {
        return filmmaker;
    }

    public void setFilmmaker(Filmmaker filmmaker) {
        this.filmmaker = filmmaker;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imageLink='" + imageLink + '\'' +
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
