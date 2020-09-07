package com.epam.mrating.model.entity;

import java.sql.Timestamp;

/**
 * The type Comment.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class Comment extends AbstractEntity<Long> {
    private static final long serialVersionUID = 5395272156057709066L;

    private String content;
    private Timestamp created;
    private double rating;
    private User user;
    private Movie movie;

    /**
     * Instantiates a new Comment.
     */
    public Comment() {
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
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
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets movie.
     *
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets movie.
     *
     * @param movie the movie
     */
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
