package epam.my.project.dao.impl.jdbc;

import epam.my.project.dao.FilmmakerDAO;
import epam.my.project.dao.pool.ConnectionPool;
import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.dao.impl.jdbc.handler.ResultHandler;
import epam.my.project.dao.impl.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Filmmaker;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Filmmaker dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class FilmmakerDAOImpl implements FilmmakerDAO {
    private static final Logger logger = getLogger(FilmmakerDAOImpl.class);

    private static final ResultHandler<List<Filmmaker>> FILMMAKER_RESULT =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.FILMMAKER_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new Filmmaker dao.
     *
     * @param connectionPool the connection pool
     */
     FilmmakerDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Filmmaker> listAllFilmmakers() throws DataStorageException {
        String sql = "SELECT f.* FROM filmmaker f ORDER BY id";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return FILMMAKER_RESULT.handle(rs);
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
