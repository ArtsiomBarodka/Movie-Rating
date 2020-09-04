package epam.my.project.dao.impl.jdbc;

import epam.my.project.dao.RoleDAO;
import epam.my.project.dao.pool.ConnectionPool;
import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.dao.impl.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.impl.jdbc.handler.ResultHandler;
import epam.my.project.dao.impl.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Role;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Role dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = getLogger(RoleDAOImpl.class);

    private static final ResultHandler<Role> ROLE_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.ROLE_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new Role dao.
     *
     * @param connectionPool the connection pool
     */
    RoleDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void createRole(String name) throws DataStorageException {
        if(Objects.isNull(name)) throw new DataStorageException("Name can`t be null");
        String sql = "INSERT INTO role (`name`) VALUES (?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, name);
            int result = ps.executeUpdate();
            if (result != 1) {
                logger.warn(String.format("Can't insert row to database. Result = %d", result));
                throw new DataStorageException("Can't insert row to database. Result = " + result);
            }
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public Optional<Role> getRoleByName(String name) throws DataStorageException {
        if(Objects.isNull(name)) throw new DataStorageException("Name can`t be null");
        String sql = "SELECT r.* FROM role r WHERE r.name=?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, name);
            ResultSet rs = ps.executeQuery();
            return Optional.ofNullable(ROLE_RESULT_ROW.handle(rs));
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
