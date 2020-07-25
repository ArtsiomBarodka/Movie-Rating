package epam.my.project.model.domain;

import java.io.Serializable;

/**
 * The type Social account.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class SocialAccount implements Serializable {
    private String name;
    private String email;


    /**
     * Instantiates a new Social account.
     */
    public SocialAccount() {
    }

    /**
     * Instantiates a new Social account.
     *
     * @param name  the name
     * @param email the email
     */
    public SocialAccount(String name, String email) {
        this.name = name;
        this.email = email;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
