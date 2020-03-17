package epam.my.project.dao;

import epam.my.project.entity.Movie;

import java.sql.SQLException;
import java.util.List;

public interface MovieDAO {
    Movie getMovieById(int id);

    void createMovie(Movie movie) throws SQLException;

    void updateMovie(int id, Movie movie);

    boolean deleteMovie(int id);

    List<Movie> listAllMovies(int page, int limit);

    List<Movie> listMoviesByGenre(String genreName, int page, int limit);

}
