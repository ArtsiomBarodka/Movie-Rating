package epam.my.project.dao.factory;

import epam.my.project.dao.*;
import epam.my.project.dao.impl.*;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.dao.jdbc.pool.impl.DataSource;

/**
 * The enum Dao factory.
 */
public enum DAOFactory {
    /**
     * Dao jdbc factory dao factory.
     */
    DAO_JDBC_FACTORY;
    
    private AccountDAO accountDAO;
    private AccountAuthTokenDAO accountAuthTokenDAO;
    private CategoryDAO categoryDAO;
    private CommentDAO commentDAO;
    private CountryDAO countryDAO;
    private FilmmakerDAO filmmakerDAO;
    private GenreDAO genreDAO;
    private MovieDAO movieDAO;
    private RoleDAO roleDAO;
    private UserDAO userDAO;
    private ConnectionPool connectionPool;
    
    DAOFactory(){
        init();
    }

    /**
     * Gets account dao.
     *
     * @return the account dao
     */
    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    /**
     * Gets account auth token dao.
     *
     * @return the account auth token dao
     */
    public AccountAuthTokenDAO getAccountAuthTokenDAO() {
        return accountAuthTokenDAO;
    }

    /**
     * Gets category dao.
     *
     * @return the category dao
     */
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    /**
     * Gets comment dao.
     *
     * @return the comment dao
     */
    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    /**
     * Gets country dao.
     *
     * @return the country dao
     */
    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    /**
     * Gets filmmaker dao.
     *
     * @return the filmmaker dao
     */
    public FilmmakerDAO getFilmmakerDAO() {
        return filmmakerDAO;
    }

    /**
     * Gets genre dao.
     *
     * @return the genre dao
     */
    public GenreDAO getGenreDAO() {
        return genreDAO;
    }

    /**
     * Gets movie dao.
     *
     * @return the movie dao
     */
    public MovieDAO getMovieDAO() {
        return movieDAO;
    }

    /**
     * Gets role dao.
     *
     * @return the role dao
     */
    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Shutdown pool.
     */
    public void shutdownPool(){
        connectionPool.shutdown();
    }

    private void init() {
        this.connectionPool = DataSource.CONNECTION_POOL_INSTANCE;
        this.accountDAO = new AccountDAOImp(connectionPool);
        this.accountAuthTokenDAO = new AccountAuthTokenDAOImpl(connectionPool);
        this.categoryDAO = new CategoryDAOImpl(connectionPool);
        this.commentDAO = new CommentDAOImpl(connectionPool);
        this.countryDAO = new CountryDAOImpl(connectionPool);
        this.filmmakerDAO = new FilmmakerDAOImpl(connectionPool);
        this.genreDAO = new GenreDAOImpl(connectionPool);
        this.movieDAO = new MovieDAOImpl(connectionPool);
        this.roleDAO = new RoleDAOImpl(connectionPool);
        this.userDAO = new UserDAOImpl(connectionPool);
    }
}
