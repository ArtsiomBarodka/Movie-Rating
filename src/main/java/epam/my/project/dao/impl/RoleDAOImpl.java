package epam.my.project.dao.impl;

import epam.my.project.dao.RoleDAO;
import epam.my.project.exception.logic.DataStorageException;
import epam.my.project.jdbc.handler.InsertParametersHandler;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Role;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.logging.log4j.LogManager.getLogger;

public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = getLogger(RoleDAOImpl.class);
    private static final ResultHandler<Role> ROLE_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.ROLE_RESULT_HANDLER);

    @Override
    public void createRole(String name) {
        String sql = "INSERT INTO role (`name`) VALUES (?)";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, name);
            int result = ps.executeUpdate();
            if (result != 1) {
                logger.warn("Can't insert row to database. Result = " + result);
                throw new DataStorageException("Can't insert row to database. Result = " + result);
            }
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public Role getRoleByName(String name) {
        String sql = "SELECT r.* FROM role r WHERE r.name=?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, name);
            ResultSet rs = ps.executeQuery();
            return ROLE_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
