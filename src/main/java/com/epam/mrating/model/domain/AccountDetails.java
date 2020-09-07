package com.epam.mrating.model.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Account details.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class AccountDetails implements Serializable {
    private static final long serialVersionUID = 4922847827424229169L;

    private String role;
    private String name;
    private String uid;
    private int id;

    /**
     * Instantiates a new Account details.
     */
    public AccountDetails() {
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
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
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
