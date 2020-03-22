package epam.my.project.service;

import epam.my.project.entity.Category;
import epam.my.project.entity.Country;
import epam.my.project.entity.Genre;
import epam.my.project.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAllMovies(int page, int limit);

    int countAllMovies();

    List<Movie> listMoviesByGenre(String genreName, int page, int limit);

    int countAllMoviesByGenre(String genreName);

    List<Genre> listAllGenres();

    List<Category> listAllCategories();

    List<Country> listAllCountries();

}
