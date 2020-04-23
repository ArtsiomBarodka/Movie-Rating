package epam.my.project.dao.impl;

import epam.my.project.dao.MovieDAO;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.dao.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.domain.SQLSearchQuery;
import epam.my.project.model.entity.Movie;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class MovieDAOImpl implements MovieDAO {
    private static final Logger logger = getLogger(MovieDAOImpl.class);
    private static final ResultHandler<Movie> MOVIE_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.MOVIE_RESULT_HANDLER);
    private static final ResultHandler<List<Movie>> MOVIE_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.MOVIE_RESULT_HANDLER);
    private ConnectionPool connectionPool;

    public MovieDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Movie getMovieById(int id) throws DataStorageException {
        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, m.rating, m.added, f.* , g.*, cat.*, c.*  " +
                "FROM movie m " +
                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
                "JOIN genre g ON g.id=m.fk_genre_id " +
                "JOIN category cat ON cat.id=m.fk_category_id " +
                "JOIN country c ON c.id=m.fk_country_id " +
                "WHERE m.id=?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            ResultSet rs = ps.executeQuery();
            return MOVIE_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public int createMovie(Movie movie) throws DataStorageException {
        String sql = "INSERT INTO movie (`image_link`, `name`, `description`, `year`, `budget`, `fees`, `duration`, `fk_filmmaker_id`, `fk_genre_id`, `fk_category_id`, `fk_country_id`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps,
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
                logger.warn("Can't insert row to database. Result = " + result);
                throw new DataStorageException("Can't insert row to database. Result = " + result);
            }
            int id = -1;
            ResultSet generatedValues = ps.getGeneratedKeys();
            if(generatedValues.next()){
                id = generatedValues.getInt(1);
            }
            if (id < 0) {
                logger.warn("Can't generate id in database. id = " + id);
                throw new DataStorageException("Can't generate id in database. id = " + id);
            }
            return id;
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) throws DataStorageException {
        String sql = "UPDATE movie " +
                "SET image_link=?, name=?, description=?, year=?, budget=?, fees=?, duration=?, fk_filmmaker_id=?, fk_genre_id=?, fk_category_id=?, fk_country_id=? " +
                "WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps,
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
                    movie.getCountry().getId(),
                    id);

            ps.executeUpdate();
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteMovie(int id) throws DataStorageException {
        String sql = "DELETE FROM movie WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            int result = ps.executeUpdate();
            return (result > 0);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> listAllMoviesOrderByRatingDesc(int offset, int limit) throws DataStorageException {
        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, m.rating, m.added, f.* , g.*, cat.*, c.*  " +
                "FROM movie m " +
                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
                "JOIN genre g ON g.id=m.fk_genre_id " +
                "JOIN category cat ON cat.id=m.fk_category_id " +
                "JOIN country c ON c.id=m.fk_country_id " +
                "ORDER BY m.rating DESC LIMIT ? OFFSET ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, limit, offset);
            ResultSet rs = ps.executeQuery();
            return MOVIE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> listAllMoviesOrderByAddedAsc(int offset, int limit) throws DataStorageException {
        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, m.rating, m.added, f.* , g.*, cat.*, c.*  " +
                "FROM movie m " +
                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
                "JOIN genre g ON g.id=m.fk_genre_id " +
                "JOIN category cat ON cat.id=m.fk_category_id " +
                "JOIN country c ON c.id=m.fk_country_id " +
                "ORDER BY m.added ASC LIMIT ? OFFSET ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, limit, offset);
            ResultSet rs = ps.executeQuery();
            return MOVIE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public int countAllMovies() throws DataStorageException {
        String sql = "SELECT count(*) FROM movie m";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> listMoviesByGenreOrderByRatingDesc(String genreName, int offset, int limit) throws DataStorageException {
        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, m.rating, m.added, f.* , g.*, cat.*, c.*  " +
                "FROM movie m " +
                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
                "JOIN genre g ON g.id=m.fk_genre_id " +
                "JOIN category cat ON cat.id=m.fk_category_id " +
                "JOIN country c ON c.id=m.fk_country_id " +
                "WHERE g.name=?" +
                "ORDER BY m.rating DESC LIMIT ? OFFSET ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, genreName, limit, offset);
            ResultSet rs = ps.executeQuery();
            return MOVIE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> listMoviesByGenreOrderByAddedAsc(String genreName, int offset, int limit) throws DataStorageException {
        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, m.rating, m.added, f.* , g.*, cat.*, c.*  " +
                "FROM movie m " +
                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
                "JOIN genre g ON g.id=m.fk_genre_id " +
                "JOIN category cat ON cat.id=m.fk_category_id " +
                "JOIN country c ON c.id=m.fk_country_id " +
                "WHERE g.name=?" +
                "ORDER BY m.added ASC LIMIT ? OFFSET ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, genreName, limit, offset);
            ResultSet rs = ps.executeQuery();
            return MOVIE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public int countAllMoviesByGenre(String genreName) throws DataStorageException {
        String sql = "SELECT count(*) FROM movie m " +
                "JOIN genre g ON g.id=m.fk_genre_id" +
                " WHERE g.name=?";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, genreName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> listMoviesBySearchOrderByRatingDesc(SQLSearchQuery sqlSearchQuery, int offset, int limit) throws DataStorageException {
        String sql = sqlSearchQuery.getQuery()+" ORDER BY m.rating DESC LIMIT ? OFFSET ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, sqlSearchQuery.getParams().toArray(), limit, offset);
            ResultSet rs = ps.executeQuery();
            return MOVIE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> listMoviesBySearchOrderByAddedAsc(SQLSearchQuery sqlSearchQuery, int offset, int limit) throws DataStorageException {
        String sql = sqlSearchQuery.getQuery()+" ORDER BY m.added ASC LIMIT ? OFFSET ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            sqlSearchQuery.getParams().add(limit);
            sqlSearchQuery.getParams().add(offset);
            InsertParametersHandler.handle(ps, sqlSearchQuery.getParams().toArray());
            ResultSet rs = ps.executeQuery();
            return MOVIE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public int countAllMoviesBySearch(SQLSearchQuery sqlSearchQuery) throws DataStorageException {
        String sql = sqlSearchQuery.getQuery();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, sqlSearchQuery.getParams().toArray());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
