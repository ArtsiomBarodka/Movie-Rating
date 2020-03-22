package epam.my.project.entity;

import java.sql.Timestamp;
import java.util.List;

public class User extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 8754771268404700871L;

    private Timestamp created;
    private Boolean banned;
    private double rating;
    private Account account;
    private List<Comment>comments;

    public User() {
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "created=" + created +
                ", banned=" + banned +
                ", rating=" + rating +
                ", account=" + account +
                ", comments=" + comments +
                ", id=" + id +
                '}';
    }
}
