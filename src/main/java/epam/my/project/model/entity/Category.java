package epam.my.project.model.entity;

public class Category extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 7011686489726063252L;

    private String name;
    private int moviesCount;

    public Category() {
    }

    public Category(String name) {
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
        return "Category{" +
                "name='" + name + '\'' +
                ", moviesCount=" + moviesCount +
                ", id=" + id +
                '}';
    }
}
