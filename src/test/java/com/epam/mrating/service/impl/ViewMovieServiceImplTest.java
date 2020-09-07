package com.epam.mrating.service.impl;

import com.epam.mrating.dao.*;
import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.model.domain.SQLSearchQuery;
import com.epam.mrating.model.entity.*;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.configuration.SortMode;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.domain.Page;
import com.epam.mrating.model.form.SearchMovieForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MovieDAO.class, GenreDAO.class, CountryDAO.class,
        CategoryDAO.class , FilmmakerDAO.class, SortMode.class})
public class ViewMovieServiceImplTest {
    private MovieDAO movieDAO;
    private GenreDAO genreDAO;
    private CountryDAO countryDAO;
    private CategoryDAO categoryDAO;
    private FilmmakerDAO filmmakerDAO;
    private ViewMovieServiceImpl viewMovieService;
    private SortMode sortMode;

    @Before
    public void setUp() throws Exception {
        movieDAO = mock(MovieDAO.class);
        genreDAO = mock(GenreDAO.class);
        countryDAO = mock(CountryDAO.class);
        categoryDAO = mock(CategoryDAO.class);
        filmmakerDAO = mock(FilmmakerDAO.class);
        sortMode = mock(SortMode.class);
        viewMovieService = new ViewMovieServiceImpl(
                movieDAO, genreDAO, countryDAO, categoryDAO, filmmakerDAO);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllMovies_NULL_SORT_MODE_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = null;
        Page page = mock(Page.class);

        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllMovies_NULL_PAGE_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException { ;
        Page page = null;

        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllMovies_DAO_LAYER_RETURN_NULL_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByAddedDesc(anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listAllMovies(sortMode, page);

    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllMovies_DAO_LAYER_THROW_EXCEPTION_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByAddedDesc(anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listAllMovies(sortMode, page);

    }

    @Test
    public void testListAllMovies_SHOULD_RETURN_LIST_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByAddedDesc(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listAllMovies(sortMode, page);

        assertNotNull(result);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllMovies_DAO_LAYER_RETURN_NULL_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByRatingDesc(anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllMovies_DAO_LAYER_THROW_EXCEPTION_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByRatingDesc(anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test
    public void testListAllMovies_SHOULD_RETURN_LIST_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByRatingDesc(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listAllMovies(sortMode, page);

        assertNotNull(result);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllMovies_DAO_LAYER_RETURN_NULL_SORT_BY_DURATION() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.DURATION;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByDurationDesc(anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllMovies_DAO_LAYER_THROW_EXCEPTION_SORT_BY_DURATION() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.DURATION;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByDurationDesc(anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test
    public void testListAllMovies_SHOULD_RETURN_LIST_SORT_BY_DURATION() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.DURATION;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByDurationDesc(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listAllMovies(sortMode, page);

        assertNotNull(result);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllMovies_DAO_LAYER_RETURN_NULL_SORT_BY_FEES() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.FEES;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByFeesDesc(anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllMovies_DAO_LAYER_THROW_EXCEPTION_SORT_BY_FEES() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.FEES;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByFeesDesc(anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test
    public void testListAllMovies_SHOULD_RETURN_LIST_SORT_BY_FEES() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.FEES;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByFeesDesc(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listAllMovies(sortMode, page);

        assertNotNull(result);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllMovies_DAO_LAYER_RETURN_NULL_SORT_BY_BUDGET() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.BUDGET;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByBudgetDesc(anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllMovies_DAO_LAYER_THROW_EXCEPTION_SORT_BY_BUDGET() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.BUDGET;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByBudgetDesc(anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listAllMovies(sortMode, page);
    }

    @Test
    public void testListAllMovies_SHOULD_RETURN_LIST_SORT_BY_BUDGET() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SortMode sortMode = SortMode.BUDGET;
        Page page = spy(new Page(1,2));

        when(movieDAO.listAllMoviesOrderByBudgetDesc(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listAllMovies(sortMode, page);

        assertNotNull(result);
    }


    @Test(expected = InternalServerErrorException.class)
    public void testCountAllMovies_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException {
        when(movieDAO.countAllMovies()).thenThrow(new DataStorageException(""));
        viewMovieService.countAllMovies();
    }

    @Test
    public void testCountAllMovies_SHOULD_RETUTN_INT() throws DataStorageException, InternalServerErrorException {
        int expected = 1;

        when(movieDAO.countAllMovies()).thenReturn(1);
        int result = viewMovieService.countAllMovies();

        assertEquals(expected, result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesByGenre_NULL_GENRE_NAME_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        String genreName = null;
        Page page = mock(Page.class);

        viewMovieService.listMoviesByGenre(genreName, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesByGenre_NULL_PAGE_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        String genreName = "";
        Page page = null;

        viewMovieService.listMoviesByGenre(genreName, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesByGenre_NULL_SORT_MODE_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        String genreName = "";
        SortMode sortMode = null;
        Page page = mock(Page.class);

        viewMovieService.listMoviesByGenre(genreName, sortMode, page);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListMoviesByGenre_DAO_LAYER_RETURN_NULL_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String genreName = "";
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesByGenreOrderByAddedDesc(anyString(), anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listMoviesByGenre(genreName, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesByGenre_DAO_LAYER_THROW_EXCEPTION_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String genreName = "";
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesByGenreOrderByAddedDesc(anyString(), anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listMoviesByGenre(genreName, sortMode, page);
    }

    @Test
    public void testListMoviesByGenre_SHOULD_RETURN_LIST_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String genreName = "";
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesByGenreOrderByAddedDesc(anyString(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listMoviesByGenre(genreName, sortMode, page);

        assertNotNull(result);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListMoviesByGenre_DAO_LAYER_RETURN_NULL_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String genreName = "";
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesByGenreOrderByRatingDesc(anyString(), anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listMoviesByGenre(genreName, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesByGenre_DAO_LAYER_THROW_EXCEPTION_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String genreName = "";
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesByGenreOrderByRatingDesc(anyString(), anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listMoviesByGenre(genreName, sortMode, page);
    }

    @Test
    public void testListMoviesByGenre_SHOULD_RETURN_LIST_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String genreName = "";
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesByGenreOrderByRatingDesc(anyString(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listMoviesByGenre(genreName, sortMode, page);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesByGenre_SHOULD_RETURN_LIST_SORT_BY_UNSUPPORTED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        String genreName = "";
        SortMode sortMode = SortMode.FEES;
        Page page = spy(new Page(1,2));

        viewMovieService.listMoviesByGenre(genreName, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCountMoviesByGenre_NULL_GENRE_NAME_PARAMETER() throws InternalServerErrorException {
        String genreName = null;

        viewMovieService.countMoviesByGenre(genreName);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCountMoviesByGenre_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, DataStorageException {
        String genreName = "";

        when(movieDAO.countAllMoviesByGenre(anyString())).thenThrow(new DataStorageException(""));
        viewMovieService.countMoviesByGenre(genreName);
    }

    @Test
    public void testCountMoviesByGenre_SHOULD_RETUTN_INT() throws InternalServerErrorException, DataStorageException {
        int expected = 1;
        String genreName = "";

        when(movieDAO.countAllMoviesByGenre(anyString())).thenReturn(1);
        int result = viewMovieService.countMoviesByGenre(genreName);

        assertEquals(expected, result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllGenres_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(genreDAO.listAllGenres()).thenThrow(new DataStorageException(""));
        viewMovieService.listAllGenres();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllGenres_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(genreDAO.listAllGenres()).thenReturn(null);
        viewMovieService.listAllGenres();
    }

    @Test
    public void testListAllGenres_SHOULD_RETUTN_LIST() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(genreDAO.listAllGenres()).thenReturn(Collections.emptyList());
        List<Genre> result = viewMovieService.listAllGenres();

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllCategories_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(categoryDAO.listAllCategories()).thenThrow(new DataStorageException(""));
        viewMovieService.listAllCategories();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllCategories_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(categoryDAO.listAllCategories()).thenReturn(null);
        viewMovieService.listAllCategories();
    }

    @Test
    public void testListAllCategories_SHOULD_RETUTN_LIST() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(categoryDAO.listAllCategories()).thenReturn(Collections.emptyList());
        List<Category> result = viewMovieService.listAllCategories();

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllCountries_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(countryDAO.listAllCountries()).thenThrow(new DataStorageException(""));
        viewMovieService.listAllCountries();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllCountries_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(countryDAO.listAllCountries()).thenReturn(null);
        viewMovieService.listAllCountries();
    }

    @Test
    public void testListAllCountries_SHOULD_RETUTN_LIST() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(countryDAO.listAllCountries()).thenReturn(Collections.emptyList());
        List<Country> result = viewMovieService.listAllCountries();

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllFilmmakers_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(filmmakerDAO.listAllFilmmakers()).thenThrow(new DataStorageException(""));
        viewMovieService.listAllFilmmakers();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllFilmmakers_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(filmmakerDAO.listAllFilmmakers()).thenReturn(null);
        viewMovieService.listAllFilmmakers();
    }

    @Test
    public void testListAllFilmmakers_SHOULD_RETUTN_LIST() throws DataStorageException, InternalServerErrorException, ObjectNotFoundException {
        when(filmmakerDAO.listAllFilmmakers()).thenReturn(Collections.emptyList());
        List<Filmmaker> result = viewMovieService.listAllFilmmakers();

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesBySearchForm_NULL_SEARCH_MOVIE_FORM_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        SearchMovieForm searchMovieForm = null;
        Page page = mock(Page.class);

        viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesBySearchForm_NULL_SORT_MODE_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        SortMode sortMode = null;
        Page page = mock(Page.class);

        viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesBySearchForm_NULL_PAGE_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        Page page = null;

        viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListMoviesBySearchForm_DAO_LAYER_RETURN_NULL_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesBySearchOrderByAddedAsc(any(SQLSearchQuery.class), anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesBySearchForm_DAO_LAYER_THROW_EXCEPTION_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesBySearchOrderByAddedAsc(any(SQLSearchQuery.class), anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);
    }

    @Test
    public void testListMoviesBySearchForm_SHOULD_RETURN_LIST_SORT_BY_ADDED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        SortMode sortMode = SortMode.ADDED;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesBySearchOrderByAddedAsc(any(SQLSearchQuery.class), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);

        assertNotNull(result);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListMoviesBySearchForm_DAO_LAYER_RETURN_NULL_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesBySearchOrderByRatingDesc(any(SQLSearchQuery.class), anyInt(), anyInt())).thenReturn(null);
        viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesBySearchForm_DAO_LAYER_THROW_EXCEPTION_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesBySearchOrderByRatingDesc(any(SQLSearchQuery.class), anyInt(), anyInt())).thenThrow(new DataStorageException(""));
        viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);
    }

    @Test
    public void testListMoviesBySearchForm_SHOULD_RETURN_LIST_SORT_BY_RATING() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        SortMode sortMode = SortMode.RATING;
        Page page = spy(new Page(1,2));

        when(movieDAO.listMoviesBySearchOrderByRatingDesc(any(SQLSearchQuery.class), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Movie> result = viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListMoviesBySearchForm_SHOULD_RETURN_LIST_SORT_BY_UNSUPPORTED() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException { ;
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);
        SortMode sortMode = SortMode.BUDGET;
        Page page = spy(new Page(1,2));

        viewMovieService.listMoviesBySearchForm(searchMovieForm, sortMode, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCountMoviesBySearchForm_NULL_SEARCH_MOVIE_FORM_PARAMETER() throws InternalServerErrorException {
        SearchMovieForm searchMovieForm = null;
        viewMovieService.countMoviesBySearchForm(searchMovieForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCountMoviesBySearchForm_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, DataStorageException {
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);

        when(movieDAO.countAllMoviesBySearch(any(SQLSearchQuery.class))).thenThrow(new DataStorageException(""));
        viewMovieService.countMoviesBySearchForm(searchMovieForm);
    }

    @Test
    public void testCountMoviesBySearchForm_SHOULD_RETURN_INT() throws InternalServerErrorException, DataStorageException {
        int expected = 1;
        SearchMovieForm searchMovieForm = mock(SearchMovieForm.class);

        when(movieDAO.countAllMoviesBySearch(any(SQLSearchQuery.class))).thenReturn(1);
        int result = viewMovieService.countMoviesBySearchForm(searchMovieForm);

        assertEquals(expected, result);
    }
}