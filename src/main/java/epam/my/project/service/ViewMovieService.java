package epam.my.project.service;

import epam.my.project.configuration.SortMode;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.*;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.model.form.SearchMovieForm;
import epam.my.project.model.domain.Page;
import java.util.List;

/**
 * The interface View movie service.
 */
public interface ViewMovieService {
    /**
     * List all movies list.
     *
     * @param sortMode the sort mode
     * @param page     the page
     * @return the list
     * @throws InternalServerErrorException the internal server error exception
     * @throws ObjectNotFoundException      the object not found exception
     */
    List<Movie> listAllMovies(SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException;

    /**
     * Count all movies int.
     *
     * @return the int
     * @throws InternalServerErrorException the internal server error exception
     */
    int countAllMovies() throws InternalServerErrorException;

    /**
     * List movies by genre list.
     *
     * @param genreName the genre name
     * @param sortMode  the sort mode
     * @param page      the page
     * @return the list
     * @throws InternalServerErrorException the internal server error exception
     * @throws ObjectNotFoundException      the object not found exception
     */
    List<Movie> listMoviesByGenre(String genreName, SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException;

    /**
     * Count movies by genre int.
     *
     * @param genreName the genre name
     * @return the int
     * @throws InternalServerErrorException the internal server error exception
     */
    int countMoviesByGenre(String genreName) throws InternalServerErrorException;

    /**
     * List all genres list.
     *
     * @return the list
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    List<Genre> listAllGenres() throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * List all categories list.
     *
     * @return the list
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    List<Category> listAllCategories() throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * List all countries list.
     *
     * @return the list
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    List<Country> listAllCountries() throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * List all filmmakers list.
     *
     * @return the list
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    List<Filmmaker> listAllFilmmakers() throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * List movies by search form list.
     *
     * @param searchMovieForm the search movie form
     * @param sortMode        the sort mode
     * @param page            the page
     * @return the list
     * @throws InternalServerErrorException the internal server error exception
     * @throws ObjectNotFoundException      the object not found exception
     */
    List<Movie> listMoviesBySearchForm(SearchMovieForm searchMovieForm, SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException;

    /**
     * Count movies by search form int.
     *
     * @param searchMovieForm the search movie form
     * @return the int
     * @throws InternalServerErrorException the internal server error exception
     */
    int countMoviesBySearchForm(SearchMovieForm searchMovieForm) throws InternalServerErrorException;
}
