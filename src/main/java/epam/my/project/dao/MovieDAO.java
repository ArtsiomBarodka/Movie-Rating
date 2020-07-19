package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.domain.SQLSearchQuery;
import epam.my.project.model.entity.Movie;
import java.util.List;
import java.util.Optional;

/**
 * The interface Movie dao.
 */
public interface MovieDAO {
    /**
     * Gets movie by u id.
     *
     * @param uid the uid
     * @return the movie by u id
     * @throws DataStorageException the data storage exception
     */
    Optional<Movie> getMovieByUId(String uid) throws DataStorageException;

    /**
     * Gets movie by id.
     *
     * @param id the id
     * @return the movie by id
     * @throws DataStorageException the data storage exception
     */
    Optional<Movie> getMovieById(int id) throws DataStorageException;

    /**
     * Gets movie by name.
     *
     * @param name the name
     * @return the movie by name
     * @throws DataStorageException the data storage exception
     */
    Optional<Movie> getMovieByName(String name) throws DataStorageException;

    /**
     * Create movie int.
     *
     * @param movie the movie
     * @return the int
     * @throws DataStorageException the data storage exception
     */
    int createMovie(Movie movie) throws DataStorageException;

    /**
     * Update movie.
     *
     * @param id    the id
     * @param movie the movie
     * @throws DataStorageException the data storage exception
     */
    void updateMovie(int id, Movie movie) throws DataStorageException;

    /**
     * Delete movie boolean.
     *
     * @param uid the uid
     * @return the boolean
     * @throws DataStorageException the data storage exception
     */
    boolean deleteMovie(String uid) throws DataStorageException;

    /**
     * List all movies order by rating desc list.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listAllMoviesOrderByRatingDesc(int offset, int limit) throws DataStorageException;

    /**
     * List all movies order by added desc list.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listAllMoviesOrderByAddedDesc(int offset, int limit) throws DataStorageException;

    /**
     * List all movies order by budget desc list.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listAllMoviesOrderByBudgetDesc(int offset, int limit) throws DataStorageException;

    /**
     * List all movies order by fees desc list.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listAllMoviesOrderByFeesDesc(int offset, int limit) throws DataStorageException;

    /**
     * List all movies order by duration desc list.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listAllMoviesOrderByDurationDesc(int offset, int limit) throws DataStorageException;

    /**
     * Count all movies int.
     *
     * @return the int
     * @throws DataStorageException the data storage exception
     */
    int countAllMovies() throws DataStorageException;

    /**
     * List movies by genre order by rating desc list.
     *
     * @param genreName the genre name
     * @param offset    the offset
     * @param limit     the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listMoviesByGenreOrderByRatingDesc(String genreName, int offset, int limit) throws DataStorageException;

    /**
     * List movies by genre order by added desc list.
     *
     * @param genreName the genre name
     * @param offset    the offset
     * @param limit     the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listMoviesByGenreOrderByAddedDesc(String genreName, int offset, int limit) throws DataStorageException;

    /**
     * Count all movies by genre int.
     *
     * @param genreName the genre name
     * @return the int
     * @throws DataStorageException the data storage exception
     */
    int countAllMoviesByGenre(String genreName) throws DataStorageException;

    /**
     * List movies by search order by rating desc list.
     *
     * @param sqlSearchQuery the sql search query
     * @param offset         the offset
     * @param limit          the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listMoviesBySearchOrderByRatingDesc(SQLSearchQuery sqlSearchQuery, int offset, int limit) throws DataStorageException;

    /**
     * List movies by search order by added asc list.
     *
     * @param sqlSearchQuery the sql search query
     * @param offset         the offset
     * @param limit          the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Movie> listMoviesBySearchOrderByAddedAsc(SQLSearchQuery sqlSearchQuery, int offset, int limit) throws DataStorageException;

    /**
     * Count all movies by search int.
     *
     * @param sqlSearchQuery the sql search query
     * @return the int
     * @throws DataStorageException the data storage exception
     */
    int countAllMoviesBySearch(SQLSearchQuery sqlSearchQuery) throws DataStorageException;

}