package epam.my.project.entity;

import java.sql.Timestamp;
import java.util.List;

public class User extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 8754771268404700871L;

    private Timestamp created;
    private Boolean banned;
    private Status status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
                ", status=" + status +
                ", account=" + account +
                ", comments=" + comments +
                ", id=" + id +
                '}';
    }
}
