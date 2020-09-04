package epam.my.project.dao.factory;

import epam.my.project.dao.*;

/**
 * The interface Dao factory.
 */
public interface DAOFactory {
    /**
     * Gets account dao.
     *
     * @return the account dao
     */
    AccountDAO getAccountDAO();

    /**
     * Gets account auth token dao.
     *
     * @return the account auth token dao
     */
    AccountAuthTokenDAO getAccountAuthTokenDAO();

    /**
     * Gets category dao.
     *
     * @return the category dao
     */
    CategoryDAO getCategoryDAO();

    /**
     * Gets comment dao.
     *
     * @return the comment dao
     */
    CommentDAO getCommentDAO();

    /**
     * Gets country dao.
     *
     * @return the country dao
     */
    CountryDAO getCountryDAO();

    /**
     * Gets filmmaker dao.
     *
     * @return the filmmaker dao
     */
    FilmmakerDAO getFilmmakerDAO();

    /**
     * Gets genre dao.
     *
     * @return the genre dao
     */
    GenreDAO getGenreDAO();

    /**
     * Gets movie dao.
     *
     * @return the movie dao
     */
    MovieDAO getMovieDAO();

    /**
     * Gets role dao.
     *
     * @return the role dao
     */
    RoleDAO getRoleDAO();

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    UserDAO getUserDAO();

    /**
     * Shutdown pool.
     */
    void shutdownPool();
}
