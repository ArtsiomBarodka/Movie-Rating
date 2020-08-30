package epam.my.project.dao.impl.jdbc;

import epam.my.project.dao.GenreDAO;
import epam.my.project.dao.impl.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.pool.ConnectionPool;
import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.dao.impl.jdbc.handler.ResultHandler;
import epam.my.project.dao.impl.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Genre;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Genre dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class GenreDAOImpl implements GenreDAO {
    private static final String SELECT_GENRE = "SELECT g.* FROM genre g, movie m ";

    private static final Logger logger = getLogger(GenreDAOImpl.class);

    private static final ResultHandler<Genre> GENRE_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.GENRE_RESULT_HANDLER);

    private static final ResultHandler<List<Genre>> GENRE_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.GENRE_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new Genre dao.
     *
     * @param connectionPool the connection pool
     */
     GenreDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public Optional<Genre> getGenreByMovieUid(String uidMovie) throws DataStorageException {
        String sql = SELECT_GENRE + "WHERE g.id=m.fk_genre_id AND m.uid=?";

        return Optional.ofNullable(getGenre(sql, uidMovie));
    }

    public Optional<Genre> getGenreByMovieId(int idMovie) throws DataStorageException {
        String sql = SELECT_GENRE + "WHERE g.id=m.fk_genre_id AND m.id=?";

        return Optional.ofNullable(getGenre(sql, idMovie));
    }

    @Override
    public List<Genre> listAllGenres() throws DataStorageException {
        String sql = "SELECT g.* FROM genre g ORDER BY id";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return GENRE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
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
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    private Genre getGenre(String sql, Object ...params) throws DataStorageException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, params);
            ResultSet rs = ps.executeQuery();
            return GENRE_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
