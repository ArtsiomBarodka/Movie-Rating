package epam.my.project.entity;

import java.sql.Timestamp;

public class Comment extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 5395272156057709066L;

    private User user;
    private Film film;
    private String content;
    private Timestamp created;
    private double rating;

    public Comment() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", film=" + film +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", rating=" + rating +
                ", id=" + id +
                '}';
    }
}
