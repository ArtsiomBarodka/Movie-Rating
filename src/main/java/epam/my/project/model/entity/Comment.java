package epam.my.project.model.entity;

import java.sql.Timestamp;

public class Comment extends AbstractEntity<Long> {
    private static final long serialVersionUID = 5395272156057709066L;

    private String content;
    private Timestamp created;
    private double rating;
    private User user;
    private Movie movie;

    public Comment() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", created=" + created +
                ", rating=" + rating +
                ", user=" + user +
                ", movie=" + movie +
                ", id=" + id +
                '}';
    }
}
