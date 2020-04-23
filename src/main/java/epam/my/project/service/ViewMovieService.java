package epam.my.project.service;

import epam.my.project.configuration.SortMode;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.Category;
import epam.my.project.model.entity.Country;
import epam.my.project.model.entity.Genre;
import epam.my.project.model.entity.Movie;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.form.SearchMovieForm;
import epam.my.project.model.domain.Page;

import java.util.List;

public interface ViewMovieService {
    List<Movie> listAllMovies(SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException;

    int countAllMovies() throws InternalServerErrorException;

    List<Movie> listMoviesByGenre(String genreName, SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException;

    int countMoviesByGenre(String genreName) throws InternalServerErrorException;

    List<Genre> listAllGenres() throws ObjectNotFoundException, InternalServerErrorException;

    List<Category> listAllCategories() throws ObjectNotFoundException, InternalServerErrorException;

    List<Country> listAllCountries() throws ObjectNotFoundException, InternalServerErrorException;

    List<Movie> listMoviesBySearchForm(SearchMovieForm searchMovieForm, SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException;

    int countMoviesBySearchForm(SearchMovieForm searchMovieForm) throws InternalServerErrorException;
}
