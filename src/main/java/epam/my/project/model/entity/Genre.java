package epam.my.project.model.entity;

/**
 * The type Genre.
 */
public class Genre extends AbstractEntity<Integer>{
    private static final long serialVersionUID = 6486558238916883878L;

    private String name;
    private int moviesCount;

    /**
     * Instantiates a new Genre.
     */
    public Genre() {
    }

    /**
     * Instantiates a new Genre.
     *
     * @param name the name
     */
    public Genre(String name) {
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

    /**
     * Gets movies count.
     *
     * @return the movies count
     */
    public int getMoviesCount() {
        return moviesCount;
    }

    /**
     * Sets movies count.
     *
     * @param moviesCount the movies count
     */
    public void setMoviesCount(int moviesCount) {
        this.moviesCount = moviesCount;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", moviesCount=" + moviesCount +
                ", id=" + id +
                '}';
    }
}
