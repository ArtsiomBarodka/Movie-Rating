package epam.my.project.service.impl;

import epam.my.project.dao.GenreDAO;
import epam.my.project.dao.MovieDAO;
import epam.my.project.dao.factory.DAOFactory;
import epam.my.project.exception.DataStorageException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Category;
import epam.my.project.model.entity.Country;
import epam.my.project.model.entity.Filmmaker;
import epam.my.project.model.entity.Genre;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.MovieForm;
import epam.my.project.service.EditMovieService;
import epam.my.project.util.DataUtil;

import java.util.Objects;

public class EditMovieServiceImpl implements EditMovieService {
    private MovieDAO movieDAO;
    private GenreDAO genreDAO;

    public EditMovieServiceImpl(DAOFactory daoFactory) {
        this.movieDAO = daoFactory.getMovieDAO();
        this.genreDAO = daoFactory.getGenreDAO();
    }

    @Override
    public Movie getMovieById(int movieId) throws ObjectNotFoundException, InternalServerErrorException {
        try {
            Movie movie = movieDAO.getMovieById(movieId);
            if(Objects.isNull(movie)){
                throw new ObjectNotFoundException("Movie not found");
            }
            return movie;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get movie from dao layer.", e);
        }

    }

    @Override
    public Movie getMovieByUId(String movieUId) throws ObjectNotFoundException, InternalServerErrorException {
        if(Objects.isNull(movieUId)) throw new InternalServerErrorException("Movie uid is null.");
        try {
            Movie movie = movieDAO.getMovieByUId(movieUId);
            if(Objects.isNull(movie)){
                throw new ObjectNotFoundException("Movie not found");
            }
            return movie;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get movie from dao layer.", e);
        }
    }

    @Override
    public Movie createMovie(MovieForm movieForm) throws InternalServerErrorException, ValidationException {
        if(Objects.isNull(movieForm)) throw new InternalServerErrorException("Movie form is null.");
        if(movieForm.getViolations().hasErrors()){
            throw new ValidationException("Movie form has invalid inputs", movieForm.getViolations());
        }
        try{
            Movie movie = new Movie();
            movie.setCategory(new Category());
            movie.setCountry(new Country());
            movie.setFilmmaker(new Filmmaker());
            movie.setGenre(new Genre());
            compareMovieWithForm(movieForm, movie);

            int movieId = movieDAO.createMovie(movie);

            Genre genre = genreDAO.getGenreByMovieId(movieId);
            genre.setMoviesCount(genre.getMoviesCount()+1);
            genreDAO.updateGenre(genre, genre.getId());

            return movieDAO.getMovieById(movieId);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t create movie from dao layer.", e);
        }
    }

    @Override
    public Movie updateMovie(MovieForm movieForm, int movieId) throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        if(Objects.isNull(movieForm)) throw new InternalServerErrorException("Movie form is null.");
        if(movieForm.getViolations().hasErrors()){
            throw new ValidationException("Movie form has invalid inputs", movieForm.getViolations());
        }
        try{
            Movie movie = getMovieById(movieId);
            compareMovieWithForm(movieForm, movie);
            movieDAO.updateMovie(movieId, movie);
            return movieDAO.getMovieById(movieId);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t update movie from dao layer.", e);
        }
    }

    @Override
    public boolean deleteMovie(int movieId) throws InternalServerErrorException {
        try{
            Genre genre = genreDAO.getGenreByMovieId(movieId);
            boolean isDeleteMovie = movieDAO.deleteMovie(movieId);
            if(isDeleteMovie){
                genre.setMoviesCount(genre.getMoviesCount()-1);
                genreDAO.updateGenre(genre, genre.getId());
            }
            return isDeleteMovie;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t delete movie from dao layer.", e);
        }
    }

    private void compareMovieWithForm(MovieForm movieForm, Movie movie){

        if(Objects.nonNull(movieForm.getImageLink()) &&
                !movieForm.getImageLink().equals(movie.getImageLink())){

            movie.setImageLink(movieForm.getImageLink());
        }
        if(Objects.nonNull(movieForm.getName()) &&
                !movieForm.getName().equals(movie.getName())){

            movie.setName(movieForm.getName());
            movie.setUid(DataUtil.generateUId(movieForm.getName()));
        }
        if(Objects.nonNull(movieForm.getDescription()) &&
                !movieForm.getDescription().equals(movie.getDescription())){

            movie.setDescription(movieForm.getDescription());
        }
        if(Objects.nonNull(movieForm.getYear()) &&
                !movieForm.getYear().equals(movie.getYear())){

            movie.setYear(movieForm.getYear());
        }
        if(Objects.nonNull(movieForm.getBudget()) &&
                !movieForm.getBudget().equals(movie.getBudget())){

            movie.setBudget(movieForm.getBudget());
        }
        if(Objects.nonNull(movieForm.getFees()) &&
                !movieForm.getFees().equals(movie.getFees())){

            movie.setFees(movieForm.getFees());
        }
        if(Objects.nonNull(movieForm.getDuration()) &&
                !movieForm.getDuration().equals(movie.getDuration())){

            movie.setDuration(movieForm.getDuration());
        }
        if(Objects.nonNull(movieForm.getFilmmakerId()) &&
                !movieForm.getFilmmakerId().equals(movie.getFilmmaker().getId())){

            movie.getFilmmaker().setId(movieForm.getFilmmakerId());
        }
        if(Objects.nonNull(movieForm.getGenreId()) &&
                !movieForm.getGenreId().equals(movie.getGenre().getId())){

            movie.getGenre().setId(movieForm.getGenreId());
        }
        if(Objects.nonNull(movieForm.getCategoryId()) &&
                !movieForm.getCategoryId().equals(movie.getCategory().getId())){

            movie.getCategory().setId(movieForm.getCategoryId());
        }
        if(Objects.nonNull(movieForm.getCountryId()) &&
                !movieForm.getCountryId().equals(movie.getCountry().getId())){

            movie.getCountry().setId(movieForm.getCountryId());
        }
    }
}
