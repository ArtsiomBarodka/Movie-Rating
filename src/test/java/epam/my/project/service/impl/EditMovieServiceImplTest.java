package epam.my.project.service.impl;

import epam.my.project.dao.GenreDAO;
import epam.my.project.dao.MovieDAO;
import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.service.exception.ValidationException;
import epam.my.project.model.entity.Genre;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.MovieForm;
import epam.my.project.component.validator.model.Violations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MovieDAO.class, GenreDAO.class})
public class EditMovieServiceImplTest {
    private MovieDAO movieDAO;
    private GenreDAO genreDAO;
    private EditMovieServiceImpl editMovieService;

    @Before
    public void setUp() throws Exception {
        movieDAO = mock(MovieDAO.class);
        genreDAO = mock(GenreDAO.class);
        editMovieService = new EditMovieServiceImpl(movieDAO, genreDAO);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetMovieById_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int movieId = 1;
        when(movieDAO.getMovieById(anyInt())).thenThrow(new DataStorageException(""));
        editMovieService.getMovieById(movieId);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testGetMovieById_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int movieId = 1;
        when(movieDAO.getMovieById(anyInt())).thenReturn(Optional.ofNullable(null));
        editMovieService.getMovieById(movieId);
    }

    @Test
    public void testGetMovieById_SHOULD_RETURN_MOVIE() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        int movieId = 1;
        when(movieDAO.getMovieById(anyInt()))
                .thenReturn(Optional.ofNullable(mock(Movie.class)));
        Movie result = editMovieService.getMovieById(movieId);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetMovieByUId_NULL_MOVIE_UID_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        String movieUid = null;
        editMovieService.getMovieByUId(movieUid);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testGetMovieByUId_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String movieUid = "";
        when(movieDAO.getMovieByUId(anyString())).thenThrow(new DataStorageException(""));
        editMovieService.getMovieByUId(movieUid);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testGetMovieByUId_DAO_LAYER_RETURN_NULL() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String movieUid = "";
        when(movieDAO.getMovieByUId(anyString())).thenReturn(Optional.ofNullable(null));
        editMovieService.getMovieByUId(movieUid);
    }

    @Test
    public void testGetMovieByUId_SHOULD_RETURN_MOVIE() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String movieUid = "";
        when(movieDAO.getMovieByUId(anyString()))
                .thenReturn(Optional.ofNullable(mock(Movie.class)));
        Movie result = editMovieService.getMovieByUId(movieUid);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testIsAlreadyExistMovie_NULL_MOVIE_NAME_PARAMETER() throws InternalServerErrorException {
        String movieName = null;
        editMovieService.isAlreadyExistMovie(movieName);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testIsAlreadyExistMovie_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, DataStorageException {
        String movieName = "";
        when(movieDAO.getMovieByName(anyString())).thenThrow(new DataStorageException(""));
        editMovieService.isAlreadyExistMovie(movieName);
    }

    @Test
    public void testIsAlreadyExistMovie_DAO_LAYER_RETURN_NULL() throws InternalServerErrorException, DataStorageException {
        String movieName = "";
        when(movieDAO.getMovieByName(anyString())).thenReturn(Optional.ofNullable(null));
        boolean result = editMovieService.isAlreadyExistMovie(movieName);

        assertFalse(result);
    }

    @Test
    public void testIsAlreadyExistMovie_DAO_LAYER_RETURN_MOVIE() throws InternalServerErrorException, DataStorageException {
        String movieName = "";
        when(movieDAO.getMovieByName(anyString()))
                .thenReturn(Optional.ofNullable(mock(Movie.class)));
        boolean result = editMovieService.isAlreadyExistMovie(movieName);

        assertTrue(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateMovie_NULL_MOVIE_FORM_PARAMETER() throws InternalServerErrorException, ValidationException {
        MovieForm movieForm = null;
        editMovieService.createMovie(movieForm);
    }

    @Test(expected = ValidationException.class)
    public void testCreateMovie_MOVIE_FORM_HAS_ERROR() throws InternalServerErrorException, ValidationException {
        MovieForm movieForm = mock(MovieForm.class);
        when(movieForm.getViolations()).thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors()).thenReturn(true);
        editMovieService.createMovie(movieForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateMovie_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, ValidationException, DataStorageException {
        MovieForm movieForm = mock(MovieForm.class);
        when(movieForm.getViolations()).thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors()).thenReturn(false);
        when(movieDAO.createMovie(any(Movie.class))).thenThrow(new DataStorageException(""));
        editMovieService.createMovie(movieForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateMovie_DAO_LAYER_RETURN_NULL_GENRE() throws InternalServerErrorException, ValidationException, DataStorageException {
        MovieForm movieForm = mock(MovieForm.class);
        when(movieForm.getViolations()).thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors()).thenReturn(false);
        when(movieDAO.createMovie(any(Movie.class))).thenReturn(1);
        when(genreDAO.getGenreByMovieId(anyInt())).thenReturn(Optional.ofNullable(null));
        editMovieService.createMovie(movieForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateMovie_DAO_LAYER_RETURN_NULL_MOVIE() throws InternalServerErrorException, ValidationException, DataStorageException {
        MovieForm movieForm = spy(new MovieForm());
        Genre genre = new Genre();
        genre.setId(1);
        when(movieForm.getViolations())
                .thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors())
                .thenReturn(false);
        when(movieDAO.createMovie(any(Movie.class)))
                .thenReturn(1);
        when(genreDAO.getGenreByMovieId(anyInt()))
                .thenReturn(Optional.ofNullable(spy(genre)));
        doNothing().when(genreDAO).updateGenre(any(Genre.class),anyInt());
        when(movieDAO.getMovieById(anyInt()))
                .thenReturn(Optional.ofNullable(null));
        editMovieService.createMovie(movieForm);
    }

    @Test
    public void testCreateMovie_SHOULD_RETURN_NEW_MOVIE() throws InternalServerErrorException, ValidationException, DataStorageException {
        MovieForm movieForm = spy(new MovieForm());
        Genre genre = new Genre();
        genre.setId(1);
        when(movieForm.getViolations())
                .thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors())
                .thenReturn(false);
        when(movieDAO.createMovie(any(Movie.class)))
                .thenReturn(1);
        when(genreDAO.getGenreByMovieId(anyInt()))
                .thenReturn(Optional.ofNullable(spy(genre)));
        doNothing().when(genreDAO).updateGenre(any(Genre.class),anyInt());
        when(movieDAO.getMovieById(anyInt()))
                .thenReturn(Optional.ofNullable(mock(Movie.class)));
        Movie result = editMovieService.createMovie(movieForm);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateMovie_NULL_MOVIE_FORM_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        int movieId = 1;
        MovieForm movieForm = null;
        editMovieService.updateMovie(movieForm, movieId);
    }

    @Test(expected = ValidationException.class)
    public void testUpdateMovie_MOVIE_FORM_HAS_ERROR() throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        int movieId = 1;
        MovieForm movieForm = mock(MovieForm.class);
        when(movieForm.getViolations()).thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors()).thenReturn(true);
        editMovieService.updateMovie(movieForm, movieId);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateMovie_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, ObjectNotFoundException, ValidationException, DataStorageException {
        int movieId = 1;
        MovieForm movieForm = spy(new MovieForm());
        when(movieForm.getViolations())
                .thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors())
                .thenReturn(false);
        when(movieDAO.getMovieById(anyInt()))
                .thenReturn(Optional.ofNullable(spy(new Movie())));
        doThrow(new DataStorageException("")).when(movieDAO).updateMovie(anyInt(), any(Movie.class));
        editMovieService.updateMovie(movieForm, movieId);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateMovie_DAO_LAYER_RETURN_NULL() throws InternalServerErrorException, ObjectNotFoundException, ValidationException, DataStorageException {
        int movieId = 1;
        MovieForm movieForm = spy(new MovieForm());
        when(movieForm.getViolations()).thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors()).thenReturn(false);
        when(movieDAO.getMovieById(anyInt())).thenReturn(Optional.ofNullable(null));
        editMovieService.updateMovie(movieForm, movieId);
    }

    @Test
    public void testUpdateMovie_SHOULD_RETURN_NEW_MOVIE() throws InternalServerErrorException, ObjectNotFoundException, ValidationException, DataStorageException {
        int movieId = 1;
        MovieForm movieForm = spy(new MovieForm());
        when(movieForm.getViolations())
                .thenReturn(mock(Violations.class));
        when(movieForm.getViolations().hasErrors())
                .thenReturn(false);
        when(movieDAO.getMovieById(anyInt()))
                .thenReturn(Optional.ofNullable(spy(new Movie())));
        doNothing().when(movieDAO).updateMovie(anyInt(), any(Movie.class));
        editMovieService.updateMovie(movieForm, movieId);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDeleteMovie_DAO_LAYER_RETURN_NULL_GENRE() throws InternalServerErrorException, DataStorageException {
        String movieUId = "";
        when(genreDAO.getGenreByMovieUid(anyString())).thenReturn(Optional.ofNullable(null));
        editMovieService.deleteMovie(movieUId);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDeleteMovie_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, DataStorageException {
        String movieUId = "";
        when(genreDAO.getGenreByMovieUid(anyString())).thenThrow(new DataStorageException(""));
        editMovieService.deleteMovie(movieUId);
    }

    @Test
    public void testDeleteMovie_SHOULD_RETURN_FALSE() throws InternalServerErrorException, DataStorageException {
        String movieUId = "";
        when(genreDAO.getGenreByMovieUid(anyString()))
                .thenReturn(Optional.ofNullable(spy(new Genre())));
        when(movieDAO.deleteMovie(anyString())).thenReturn(false);
        boolean result = editMovieService.deleteMovie(movieUId);

        assertFalse(result);
    }

    @Test
    public void testDeleteMovie_SHOULD_RETURN_TRUE() throws InternalServerErrorException, DataStorageException {
        String movieUId = "";
        Genre genre = new Genre();
        genre.setId(1);
        when(genreDAO.getGenreByMovieUid(anyString())).thenReturn(Optional.of(genre));
        when(movieDAO.deleteMovie(anyString())).thenReturn(true);
        doNothing().when(genreDAO).updateGenre(any(Genre.class), anyInt());
        boolean result = editMovieService.deleteMovie(movieUId);

        assertTrue(result);
    }
}