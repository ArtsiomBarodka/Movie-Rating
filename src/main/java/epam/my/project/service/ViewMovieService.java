package epam.my.project.service;

import epam.my.project.configuration.SortMode;
import epam.my.project.entity.Category;
import epam.my.project.entity.Country;
import epam.my.project.entity.Genre;
import epam.my.project.entity.Movie;
import epam.my.project.form.MovieForm;
import epam.my.project.form.SearchMovieForm;
import epam.my.project.model.Page;

import java.util.List;

public interface ViewMovieService {
    List<Movie> listAllMovies(SortMode sortMode, Page page);

    int countAllMovies();

    List<Movie> listMoviesByGenre(String genreName, SortMode sortMode, Page page);

    int countMoviesByGenre(String genreName);

    List<Genre> listAllGenres();

    List<Category> listAllCategories();

    List<Country> listAllCountries();

    List<Movie> listMoviesBySearchForm(SearchMovieForm searchMovieForm, SortMode sortMode, Page page);

    int countMoviesBySearchForm(SearchMovieForm searchMovieForm);
}
