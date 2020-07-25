package epam.my.project.model.entity;

/**
 * The type Filmmaker.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class Filmmaker extends AbstractEntity<Integer>{
    private static final long serialVersionUID = 8485914243075800755L;

    private String firstName;
    private String lastName;

    /**
     * Instantiates a new Filmmaker.
     */
    public Filmmaker() {
    }

    /**
     * Instantiates a new Filmmaker.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Filmmaker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Filmmaker{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}
