package epam.my.project.service;

import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.MovieForm;

/**
 * The interface Edit movie service.
 */
public interface EditMovieService {
    /**
     * Gets movie by id.
     *
     * @param movieId the movie id
     * @return the movie by id
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    Movie getMovieById(int movieId) throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * Gets movie by u id.
     *
     * @param movieUId the movie u id
     * @return the movie by u id
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    Movie getMovieByUId(String movieUId) throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * Is already exist movie boolean.
     *
     * @param movieName the movie name
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     */
    boolean isAlreadyExistMovie(String movieName) throws InternalServerErrorException;

    /**
     * Create movie movie.
     *
     * @param movieForm the movie form
     * @return the movie
     * @throws InternalServerErrorException the internal server error exception
     * @throws ValidationException          the validation exception
     */
    Movie createMovie(MovieForm movieForm) throws InternalServerErrorException, ValidationException;

    /**
     * Update movie movie.
     *
     * @param movieForm the movie form
     * @param movieId   the movie id
     * @return the movie
     * @throws InternalServerErrorException the internal server error exception
     * @throws ObjectNotFoundException      the object not found exception
     * @throws ValidationException          the validation exception
     */
    Movie updateMovie(MovieForm movieForm, int movieId) throws InternalServerErrorException, ObjectNotFoundException, ValidationException;

    /**
     * Delete movie boolean.
     *
     * @param movieUId the movie u id
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     */
    boolean deleteMovie(String movieUId) throws InternalServerErrorException;
}
