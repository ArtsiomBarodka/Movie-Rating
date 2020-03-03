package epam.my.project.entity;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;


public class Film extends AbstractEntity<Film> {
    private static final long serialVersionUID = 6363780319231117683L;

    private String imageLink;
    private String name;
    private String description;
    private Date year;
    private BigInteger budget;
    private BigInteger fees;
    private Time duration;
    private Filmmaker filmmaker;
    private Genre genre;
    private Category category;
    private Country country;

    public Film() {
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

    public BigInteger getBudget() {
        return budget;
    }

    public void setBudget(BigInteger budget) {
        this.budget = budget;
    }

    public BigInteger getFees() {
        return fees;
    }

    public void setFees(BigInteger fees) {
        this.fees = fees;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
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

    @Override
    public String toString() {
        return "Film{" +
                "imageLink='" + imageLink + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", budget=" + budget +
                ", fees=" + fees +
                ", duration=" + duration +
                ", filmmaker=" + filmmaker +
                ", genre=" + genre +
                ", category=" + category +
                ", country=" + country +
                ", id=" + id +
                '}';
    }
}
