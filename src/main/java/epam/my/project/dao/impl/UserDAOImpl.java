package epam.my.project.dao.impl;

import epam.my.project.dao.UserDAO;
import epam.my.project.exception.logic.DataStorageException;
import epam.my.project.jdbc.handler.InsertParametersHandler;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.User;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.logging.log4j.LogManager.getLogger;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = getLogger(UserDAOImpl.class);
    private static final ResultHandler<User> USER_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.USER_RESULT_HANDLER);

    @Override
    public User getUserByAccountId(int accountId) {
        String sql = "SELECT u.id, u.uid, u.image_link, u.created, u.rating, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE u.fk_account_id=?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, accountId);
            ResultSet rs = ps.executeQuery();
            return USER_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public User getUserById(int id){
        String sql = "SELECT u.id, u.uid, u.image_link, u.created, u.rating, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE u.id=?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            ResultSet rs = ps.executeQuery();
            return USER_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public User getUserByName(String name) {
        String sql = "SELECT u.id, u.uid, u.image_link, u.created, u.banned, u.rating, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE a.name=?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, name);
            ResultSet rs = ps.executeQuery();
            return  USER_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public int createUser(User user){
        String sql = "INSERT INTO user (`uid`, `image_link`, `fk_account_id`) VALUES (?,?,?)";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps,
                    user.getUid(),
                    user.getImageLink(),
                    user.getAccount().getId());

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
            if (id < 0 ) {
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
    public void updateUser(int id, User user) {
        String sql = "UPDATE user SET rating=?, banned=? WHERE id=?";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps,
                    user.getRating(),
                    user.getBanned(),
                    id);

            ps.executeUpdate();
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
