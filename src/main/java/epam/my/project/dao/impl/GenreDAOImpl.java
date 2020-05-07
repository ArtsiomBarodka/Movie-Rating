package epam.my.project.dao.impl;

import epam.my.project.dao.GenreDAO;
import epam.my.project.dao.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Genre;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;

public class GenreDAOImpl implements GenreDAO {
    private static final Logger logger = getLogger(GenreDAOImpl.class);
    private static final ResultHandler<Genre> GENRE_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.GENRE_RESULT_HANDLER);
    private static final ResultHandler<List<Genre>> GENRE_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.GENRE_RESULT_HANDLER);
    private ConnectionPool connectionPool;

    public GenreDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Genre getGenreByMovieId(int idMovie) throws DataStorageException {
        String sql = "SELECT g.* FROM genre g, movie m WHERE g.id=m.fk_genre_id AND m.id=?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, idMovie);
            ResultSet rs = ps.executeQuery();
            return GENRE_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Genre> listAllGenres() throws DataStorageException {
        String sql = "SELECT g.* FROM genre g ORDER BY id";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return GENRE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public void updateGenre(Genre genre, int idGenre) throws DataStorageException {
        if(Objects.isNull(genre)) throw new DataStorageException("Genre can`t be null");
        String sql = "UPDATE genre SET name=?, movies_count=? WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps,
                    genre.getName(),
                    genre.getMoviesCount(),
                    idGenre);
            ps.executeUpdate();
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
