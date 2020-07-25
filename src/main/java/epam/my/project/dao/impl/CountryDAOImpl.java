package epam.my.project.dao.impl;

import epam.my.project.dao.CountryDAO;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Country;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Country dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class CountryDAOImpl implements CountryDAO {
    private static final Logger logger = getLogger(CountryDAOImpl.class);

    private static final ResultHandler<List<Country>> COUNTRY_RESULT =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.COUNTRY_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new Country dao.
     *
     * @param connectionPool the connection pool
     */
     CountryDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Country> listAllCountries() throws DataStorageException {
        String sql = "SELECT c.* FROM country c ORDER BY id";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return COUNTRY_RESULT.handle(rs);
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
