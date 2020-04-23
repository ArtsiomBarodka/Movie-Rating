package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.domain.SQLSearchQuery;
import epam.my.project.model.entity.Movie;
import java.util.List;

public interface MovieDAO {
    Movie getMovieById(int id) throws DataStorageException;

    int createMovie(Movie movie) throws DataStorageException;

    void updateMovie(int id, Movie movie) throws DataStorageException;

    boolean deleteMovie(int id) throws DataStorageException;

    List<Movie> listAllMoviesOrderByRatingDesc(int offset, int limit) throws DataStorageException;

    List<Movie> listAllMoviesOrderByAddedAsc(int offset, int limit) throws DataStorageException;

    int countAllMovies() throws DataStorageException;

    List<Movie> listMoviesByGenreOrderByRatingDesc(String genreName, int offset, int limit) throws DataStorageException;

    List<Movie> listMoviesByGenreOrderByAddedAsc(String genreName, int offset, int limit) throws DataStorageException;

    int countAllMoviesByGenre(String genreName) throws DataStorageException;

    List<Movie> listMoviesBySearchOrderByRatingDesc(SQLSearchQuery sqlSearchQuery, int offset, int limit) throws DataStorageException;

    List<Movie> listMoviesBySearchOrderByAddedAsc(SQLSearchQuery sqlSearchQuery, int offset, int limit) throws DataStorageException;

    int countAllMoviesBySearch(SQLSearchQuery sqlSearchQuery) throws DataStorageException;
}