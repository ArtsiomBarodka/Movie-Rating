package epam.my.project.service.impl;

import epam.my.project.configuration.SortMode;
import epam.my.project.entity.Category;
import epam.my.project.entity.Country;
import epam.my.project.entity.Genre;
import epam.my.project.entity.Movie;
import epam.my.project.form.SearchMovieForm;
import epam.my.project.model.Page;
import epam.my.project.service.ViewMovieService;

import java.util.List;

public class ViewMovieServiceImpl implements ViewMovieService {
    @Override
    public List<Movie> listAllMovies(SortMode sortMode, Page page) {
        return null;
    }

    @Override
    public int countAllMovies() {
        return 0;
    }

    @Override
    public List<Movie> listMoviesByGenre(String genreName, SortMode sortMode, Page page) {
        return null;
    }

    @Override
    public int countMoviesByGenre(String genreName) {
        return 0;
    }

    @Override
    public List<Genre> listAllGenres() {
        return null;
    }

    @Override
    public List<Category> listAllCategories() {
        return null;
    }

    @Override
    public List<Country> listAllCountries() {
        return null;
    }

    @Override
    public List<Movie> listMoviesBySearchForm(SearchMovieForm searchMovieForm, SortMode sortMode, Page page) {
        return null;
    }

    @Override
    public int countMoviesBySearchForm(SearchMovieForm searchMovieForm) {
        return 0;
    }
}
