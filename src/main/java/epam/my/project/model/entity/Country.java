package epam.my.project.model.entity;

/**
 * The type Country.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class Country extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 1313806529887977090L;

    private String name;

    /**
     * Instantiates a new Country.
     */
    public Country() {
    }

    /**
     * Instantiates a new Country.
     *
     * @param name the name
     */
    public Country(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "CountryDAOImpl{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
