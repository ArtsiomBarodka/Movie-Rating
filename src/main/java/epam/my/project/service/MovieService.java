package epam.my.project.service;

import epam.my.project.entity.Category;
import epam.my.project.entity.Country;
import epam.my.project.entity.Genre;
import epam.my.project.entity.Movie;
import epam.my.project.form.MovieForm;
import epam.my.project.form.SearchMovieForm;

import java.util.List;

public interface MovieService {
    List<Movie> listAllMovies(int page, int limit);

    int countAllMovies();

    List<Movie> listMoviesByGenre(String genreName, int page, int limit);

    int countMoviesByGenre(String genreName);

    List<Genre> listAllGenres();

    List<Category> listAllCategories();

    List<Country> listAllCountries();

    List<Movie> listMoviesBySearchForm(SearchMovieForm searchMovieForm, int page, int limit);

    int countMoviesBySearchForm(SearchMovieForm searchMovieForm);

    Movie getMovieById(int movieId);

    Movie createMovie(MovieForm movieForm);

    Movie updateMovie(MovieForm movieForm, int movieId);

    boolean deleteMovie(int movieId);


}
