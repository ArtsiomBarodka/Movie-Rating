package epam.my.project.dao;

import epam.my.project.entity.Movie;

import java.sql.SQLException;
import java.util.List;

public interface MovieDAO {
    Movie getMovieById(int id) throws SQLException;

    void createMovie(Movie movie) throws SQLException;

    void updateMovie(int id, Movie movie) throws SQLException;

    boolean deleteMovie(int id) throws SQLException;

    List<Movie> listAllMovies(int page, int limit) throws SQLException;

    List<Movie> listMoviesByGenre(String genreName, int page, int limit) throws SQLException;

}
