package epam.my.project.entity;

import java.sql.Timestamp;

public class User extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 8754771268404700871L;

    private Timestamp created;
    private Boolean banned;
    private Status status;
    private Account account;

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

    @Override
    public String toString() {
        return "User{" +
                "created=" + created +
                ", banned=" + banned +
                ", status=" + status +
                ", account=" + account +
                ", id=" + id +
                '}';
    }
}
