package epam.my.project.dao.factory;

import epam.my.project.dao.*;

public interface DAOFactory {
    AccountDAO getAccountDAO();
    AccountAuthTokenDAO getAccountAuthTokenDAO();
    CategoryDAO getCategoryDAO();
    CommentDAO getCommentDAO();
    CountryDAO getCountryDAO();
    FilmmakerDAO getFilmmakerDAO();
    GenreDAO getGenreDAO();
    MovieDAO getMovieDAO();
    RoleDAO getRoleDAO();
    UserDAO getUserDAO();
    void shutdownPool();
}
