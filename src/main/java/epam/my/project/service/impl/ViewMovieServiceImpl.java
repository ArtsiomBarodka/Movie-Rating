package epam.my.project.service.impl;

import epam.my.project.configuration.SortMode;
import epam.my.project.dao.CategoryDAO;
import epam.my.project.dao.CountryDAO;
import epam.my.project.dao.GenreDAO;
import epam.my.project.dao.MovieDAO;
import epam.my.project.dao.factory.DAOFactory;
import epam.my.project.exception.DataStorageException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.SQLSearchQuery;
import epam.my.project.model.entity.Category;
import epam.my.project.model.entity.Country;
import epam.my.project.model.entity.Genre;
import epam.my.project.model.entity.Movie;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.model.form.SearchMovieForm;
import epam.my.project.model.domain.Page;
import epam.my.project.service.ViewMovieService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewMovieServiceImpl implements ViewMovieService {
    private MovieDAO movieDAO;
    private GenreDAO genreDAO;
    private CountryDAO countryDAO;
    private CategoryDAO categoryDAO;

    public ViewMovieServiceImpl(DAOFactory daoFactory) {
        this.movieDAO = daoFactory.getMovieDAO();
        this.genreDAO = daoFactory.getGenreDAO();
        this.countryDAO = daoFactory.getCountryDAO();
        this.categoryDAO = daoFactory.getCategoryDAO();
    }

    @Override
    public List<Movie> listAllMovies(SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException {
        if (Objects.isNull(sortMode))throw new InternalServerErrorException("Sort mod is null.");
        if (Objects.isNull(page)) throw new InternalServerErrorException("Page is null.");
        try{
            List<Movie> movies;
            switch (sortMode) {
                case MOVIE_ADDED:
                    movies = movieDAO.listAllMoviesOrderByAddedAsc(page.getOffset(), page.getLimit());
                    if(Objects.isNull(movies)){
                        throw new ObjectNotFoundException("Movies list not found");
                    }
                    return movies;

                case MOVIE_RATING:
                    movies =  movieDAO.listAllMoviesOrderByRatingDesc(page.getOffset(), page.getLimit());
                    if(Objects.isNull(movies)){
                        throw new ObjectNotFoundException("Movies list not found");
                    }
                    return movies;

                default:
                    throw new InternalServerErrorException("Unsupported sort mode: " + sortMode);
            }
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get movies from dao layer.", e);
        }
    }

    @Override
    public int countAllMovies() throws InternalServerErrorException {
        try {
            return movieDAO.countAllMovies();
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t count movies from dao layer.", e);
        }
    }

    @Override
    public List<Movie> listMoviesByGenre(String genreName, SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException {
        if (Objects.isNull(genreName))throw new InternalServerErrorException("Name of genre is null.");
        if (Objects.isNull(sortMode))throw new InternalServerErrorException("Sort mod is null.");
        if (Objects.isNull(page)) throw new InternalServerErrorException("Page is null.");
        try {
            List<Movie> movies;
            switch (sortMode) {
                case MOVIE_ADDED:
                    movies =  movieDAO.listMoviesByGenreOrderByAddedAsc(genreName ,page.getOffset(), page.getLimit());
                    if(Objects.isNull(movies)){
                        throw new ObjectNotFoundException("Movies list not found");
                    }
                    return movies;

                case MOVIE_RATING:
                    movies = movieDAO.listMoviesByGenreOrderByRatingDesc(genreName ,page.getOffset(), page.getLimit());
                    if(Objects.isNull(movies)){
                        throw new ObjectNotFoundException("Movies list not found");
                    }
                    return movies;

                default:
                    throw new InternalServerErrorException("Unsupported sort mode: " + sortMode);
            }
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get movies from dao layer.", e);
        }
    }

    @Override
    public int countMoviesByGenre(String genreName) throws InternalServerErrorException {
        if (Objects.isNull(genreName))throw new InternalServerErrorException("Name of genre is null.");
        try {
            return movieDAO.countAllMoviesByGenre(genreName);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t count movies from dao layer.", e);
        }
    }

    @Override
    public List<Genre> listAllGenres() throws ObjectNotFoundException, InternalServerErrorException {
        try {
            List<Genre> genres = genreDAO.listAllGenres();
            if(Objects.isNull(genres)){
                throw new ObjectNotFoundException("Genres list not found");
            }
            return genres;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get genres from dao layer.", e);
        }
    }

    @Override
    public List<Category> listAllCategories() throws ObjectNotFoundException, InternalServerErrorException {
        try {
            List<Category> categories = categoryDAO.listAllCategories();
            if(Objects.isNull(categories)){
                throw new ObjectNotFoundException("Categories list not found");
            }
            return categories;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get categories from dao layer.", e);
        }
    }

    @Override
    public List<Country> listAllCountries() throws ObjectNotFoundException, InternalServerErrorException {
        try {
            List<Country> countries = countryDAO.listAllCountries();
            if(Objects.isNull(countries)){
                throw new ObjectNotFoundException("Countries list not found");
            }
            return countries;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get countries from dao layer.", e);
        }
    }

    @Override
    public List<Movie> listMoviesBySearchForm(SearchMovieForm searchMovieForm, SortMode sortMode, Page page) throws InternalServerErrorException, ObjectNotFoundException {
        if (Objects.isNull(searchMovieForm))throw new InternalServerErrorException("Search form is null.");
        if (Objects.isNull(sortMode))throw new InternalServerErrorException("Sort mod is null.");
        if (Objects.isNull(page)) throw new InternalServerErrorException("Page is null.");
        try{
            String selectedFields = "m.id, m.uid, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, m.rating, m.added, f.* , g.*, cat.*, c.*";
            SQLSearchQuery sqlSearchQuery = buildSearchQuery(selectedFields, searchMovieForm);
            List<Movie> movies;
            switch (sortMode) {
                case MOVIE_ADDED:
                    movies = movieDAO.listMoviesBySearchOrderByAddedAsc(sqlSearchQuery, page.getOffset(), page.getLimit());
                    if(Objects.isNull(movies)){
                        throw new ObjectNotFoundException("Movies list not found");
                    }
                    return movies;

                case MOVIE_RATING:
                    movies = movieDAO.listMoviesBySearchOrderByRatingDesc(sqlSearchQuery, page.getOffset(), page.getLimit());
                    if(Objects.isNull(movies)){
                        throw new ObjectNotFoundException("Movies list not found");
                    }
                    return movies;

                default:
                    throw new InternalServerErrorException("Unsupported sort mode: " + sortMode);
            }
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get movies from dao layer.", e);
        }
    }

    @Override
    public int countMoviesBySearchForm(SearchMovieForm searchMovieForm) throws InternalServerErrorException {
        if (Objects.isNull(searchMovieForm))throw new InternalServerErrorException("Search form is null.");
        try{
            String selectedFields = "count(*)";
            SQLSearchQuery sqlSearchQuery = buildSearchQuery(selectedFields, searchMovieForm);
            return movieDAO.countAllMoviesBySearch(sqlSearchQuery);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t count movies from dao layer.", e);
        }
    }

    private SQLSearchQuery buildSearchQuery(String selectedFields, SearchMovieForm searchMovieForm) {
        List<Object> params = new ArrayList<>();
        params.add("%"+searchMovieForm.getMovieName()+"%");
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(selectedFields);
        sql.append(" FROM movie m JOIN filmmaker f ON f.id=m.fk_filmmaker_id JOIN genre g ON g.id=m.fk_genre_id JOIN category cat ON cat.id=m.fk_category_id JOIN country c ON c.id=m.fk_country_id WHERE ");
        sql.append("(m.name like ? )");

        fillSqlQueryWithParams(sql, params, searchMovieForm.getCategories(),"cat.id=?");
        fillSqlQueryWithParams(sql, params, searchMovieForm.getCountries(),"c.id=?");
        fillSqlQueryWithParams(sql, params, searchMovieForm.getGenres(),"g.id=?");

        return new SQLSearchQuery(sql.toString(), params);
    }

    private void fillSqlQueryWithParams(StringBuilder sql, List<Object> params, List<Integer> from, String expression){
        if(from != null && !from.isEmpty()){
            sql.append("AND ( ");
            for (int i = 0; i < from.size(); i++) {
                sql.append(expression).append(" ");
                params.add(from.get(i));
                if (i != from.size() - 1) {
                    sql.append(" or ");
                }
            }
            sql.append(") ");
        }
    }
}
