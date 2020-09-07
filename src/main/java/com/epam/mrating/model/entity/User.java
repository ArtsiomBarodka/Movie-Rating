package com.epam.mrating.model.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * The type User.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class User extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 8754771268404700871L;

    private String uid;
    private String imageLink;
    private Timestamp created;
    private Boolean banned;
    private Integer rating;
    private Account account;
    private List<Comment>comments;

    /**
     * Instantiates a new User.
     */
    public User() {
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
     * Gets created.
     *
     * @return the created
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Gets banned.
     *
     * @return the banned
     */
    public Boolean getBanned() {
        return banned;
    }

    /**
     * Sets banned.
     *
     * @param banned the banned
     */
    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Gets account.
     *
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets account.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
        this.account = account;
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
        return "User{" +
                "uid='" + uid + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", created=" + created +
                ", banned=" + banned +
                ", rating=" + rating +
                ", account=" + account +
                ", comments=" + comments +
                ", id=" + id +
                '}';
    }
}
