package epam.my.project.service;

import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.MovieForm;

public interface EditMovieService {
    Movie getMovieById(int movieId) throws ObjectNotFoundException, InternalServerErrorException;

    Movie createMovie(MovieForm movieForm) throws InternalServerErrorException;

    Movie updateMovie(MovieForm movieForm, int movieId) throws InternalServerErrorException, ObjectNotFoundException;

    boolean deleteMovie(int movieId) throws InternalServerErrorException;
}
