package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.domain.SQLSearchQuery;
import epam.my.project.model.entity.Movie;
import java.util.List;

public interface MovieDAO {
    Movie getMovieByUId(String uid) throws DataStorageException;

    Movie getMovieById(int id) throws DataStorageException;

    Movie getMovieByName(String name) throws DataStorageException;

    int createMovie(Movie movie) throws DataStorageException;

    void updateMovie(int id, Movie movie) throws DataStorageException;

    boolean deleteMovie(String uid) throws DataStorageException;

    List<Movie> listAllMoviesOrderByRatingDesc(int offset, int limit) throws DataStorageException;

    List<Movie> listAllMoviesOrderByAddedDesc(int offset, int limit) throws DataStorageException;

    List<Movie> listAllMoviesOrderByBudgetDesc(int offset, int limit) throws DataStorageException;

    List<Movie> listAllMoviesOrderByFeesDesc(int offset, int limit) throws DataStorageException;

    List<Movie> listAllMoviesOrderByDurationDesc(int offset, int limit) throws DataStorageException;

    int countAllMovies() throws DataStorageException;

    List<Movie> listMoviesByGenreOrderByRatingDesc(String genreName, int offset, int limit) throws DataStorageException;

    List<Movie> listMoviesByGenreOrderByAddedDesc(String genreName, int offset, int limit) throws DataStorageException;

    int countAllMoviesByGenre(String genreName) throws DataStorageException;

    List<Movie> listMoviesBySearchOrderByRatingDesc(SQLSearchQuery sqlSearchQuery, int offset, int limit) throws DataStorageException;

    List<Movie> listMoviesBySearchOrderByAddedAsc(SQLSearchQuery sqlSearchQuery, int offset, int limit) throws DataStorageException;

    int countAllMoviesBySearch(SQLSearchQuery sqlSearchQuery) throws DataStorageException;

}