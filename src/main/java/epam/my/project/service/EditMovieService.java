package epam.my.project.service;

import epam.my.project.entity.Movie;
import epam.my.project.form.MovieForm;

public interface EditMovieService {
    Movie getMovieById(int movieId);

    Movie createMovie(MovieForm movieForm);

    Movie updateMovie(MovieForm movieForm, int movieId);

    boolean deleteMovie(int movieId);
}
