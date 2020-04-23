package epam.my.project.dao.factory;

import epam.my.project.dao.*;
import epam.my.project.dao.impl.*;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.dao.jdbc.pool.impl.DataSource;

public enum DAOFactory {
    DAO_JDBC_FACTORY;
    
    private AccountDAO accountDAO;
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

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public FilmmakerDAO getFilmmakerDAO() {
        return filmmakerDAO;
    }

    public GenreDAO getGenreDAO() {
        return genreDAO;
    }

    public MovieDAO getMovieDAO() {
        return movieDAO;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void shutdownPool(){
        connectionPool.shutdown();
    }

    private void init() {
        this.connectionPool = DataSource.CONNECTION_POOL_INSTANCE;
        this.accountDAO = new AccountDAOImp(connectionPool);
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
