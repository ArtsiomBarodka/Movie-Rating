package epam.my.project.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class AccountDetails implements Serializable {
    private static final long serialVersionUID = 4922847827424229169L;

    private String role;
    private String name;
    private String uid;
    private int id;

    public AccountDetails() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDetails accountDetails = (AccountDetails) o;
        return id == accountDetails.id &&
                Objects.equals(role, accountDetails.role) &&
                Objects.equals(name, accountDetails.name) &&
                Objects.equals(uid, accountDetails.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, name, id);
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
