package epam.my.project.model.entity;

public class Genre extends AbstractEntity<Integer>{
    private static final long serialVersionUID = 6486558238916883878L;

    private String name;
    private int moviesCount;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoviesCount() {
        return moviesCount;
    }

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
