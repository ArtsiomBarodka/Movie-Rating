package epam.my.project.entity;

import java.util.List;

public class Genre extends AbstractEntity<Integer>{
    private static final long serialVersionUID = 6486558238916883878L;

    private String name;
    private List<Movie> movies;

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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", movies=" + movies +
                ", id=" + id +
                '}';
    }
}
