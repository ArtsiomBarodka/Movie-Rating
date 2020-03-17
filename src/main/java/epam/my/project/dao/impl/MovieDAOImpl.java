package epam.my.project.dao.impl;

import epam.my.project.dao.MovieDAO;
import epam.my.project.db.handler.insert.InsertHandler;
import epam.my.project.db.handler.select.ResultHandler;
import epam.my.project.db.handler.select.ResultHandlerFactory;
import epam.my.project.db.pool.impl.ConnectionPoolImpl;
import epam.my.project.entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    private static final ResultHandler<Movie> MOVIE_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.MOVIE_RESULT_HANDLER);
    private static final ResultHandler<List<Movie>> MOVIE_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.MOVIE_RESULT_HANDLER);

    @Override
    public Movie getMovieById(int id) {
        Movie result = null;
        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, f.* , g.*, cat.*, c.*  " +
                "FROM movie m " +
                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
                "JOIN genre g ON g.id=m.fk_genre_id " +
                "JOIN category cat ON cat.id=m.fk_category_id " +
                "JOIN country c ON c.id=m.fk_country_id " +
                "WHERE m.id=?";

        try(Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            result = MOVIE_RESULT_ROW.handle(rs);
        } catch (SQLException e){

        }
        return result;
    }

    @Override
    public void createMovie(Movie movie) throws SQLException {
        String sql = "INSERT INTO movie (`image_link`, `name`, `description`, `year`, `budget`, `fees`, `duration`, `fk_filmmaker_id`, `fk_genre_id`, `fk_category_id`, `fk_country_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            InsertHandler.insertParameters(ps,
                    movie.getImageLink(),
                    movie.getName(),
                    movie.getDescription(),
                    movie.getYear(),
                    movie.getBudget(),
                    movie.getFees(),
                    movie.getDuration(),
                    movie.getFilmmaker().getId(),
                    movie.getGenre().getId(),
                    movie.getCategory().getId(),
                    movie.getCountry().getId());

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new SQLException("Can't insert row to database. Result=" + result);
            }
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) {

    }

    @Override
    public boolean deleteMovie(int id) {
        return false;
    }

    @Override
    public List<Movie> listAllMovies(int page, int limit) {
        List<Movie> result = Collections.emptyList();
        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, f.* , g.*, cat.*, c.*  " +
                "FROM movie m " +
                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
                "JOIN genre g ON g.id=m.fk_genre_id " +
                "JOIN category cat ON cat.id=m.fk_category_id " +
                "JOIN country c ON c.id=m.fk_country_id " +
                "ORDER BY m.id LIMIT ? OFFSET ?";

        try(Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
                PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setInt(1,page);
            pr.setInt(2,limit);
            ResultSet rs = pr.executeQuery();
            result = MOVIE_RESULT_LIST.handle(rs);
        } catch (SQLException e){

        }
        return result;
    }

    @Override
    public List<Movie> listMoviesByGenre(String genreName, int page, int limit) {
        List<Movie> result = Collections.emptyList();
        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, f.* , g.*, cat.*, c.*  " +
                "FROM movie m " +
                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
                "JOIN genre g ON g.id=m.fk_genre_id " +
                "JOIN category cat ON cat.id=m.fk_category_id " +
                "JOIN country c ON c.id=m.fk_country_id " +
                "WHERE g.name=?" +
                "ORDER BY m.id LIMIT ? OFFSET ?";

        try(Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setString(1,genreName);
            pr.setInt(2,page);
            pr.setInt(3,limit);
            ResultSet rs = pr.executeQuery();
            result = MOVIE_RESULT_LIST.handle(rs);
        } catch (SQLException e){

        }
        return result;
    }
}
