package epam.my.project.dao.impl;

import epam.my.project.dao.UserDAO;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.dao.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.User;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = getLogger(UserDAOImpl.class);
    private static final ResultHandler<User> USER_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.USER_RESULT_HANDLER);
    private ConnectionPool connectionPool;

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public User getUserByAccountId(int accountId) throws DataStorageException {
        String sql = "SELECT u.id, u.uid, u.image_link, u.created, u.rating, u.banned, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE u.fk_account_id=?";

        return getUser(sql, accountId);
    }

    @Override
    public User getUserByUId(String uid) throws DataStorageException {
        if(Objects.isNull(uid)) throw new DataStorageException("User uid can`t be null");
        String sql = "SELECT u.id, u.uid, u.image_link, u.created, u.rating, u.banned, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE u.uid=?";

        return getUser(sql, uid);
    }

    @Override
    public User getUserById(int id) throws DataStorageException {
        String sql = "SELECT u.id, u.uid, u.image_link, u.created, u.rating, u.banned, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE u.id=?";

        return getUser(sql, id);
    }

    @Override
    public User getUserByName(String name) throws DataStorageException {
        if(Objects.isNull(name)) throw new DataStorageException("Name can`t be null");
        String sql = "SELECT u.id, u.uid, u.image_link, u.created, u.banned, u.rating, u.banned, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE a.name=?";

        return getUser(sql, name);
    }

    @Override
    public int createUser(User user) throws DataStorageException {
        if(Objects.isNull(user)) throw new DataStorageException("User can`t be null");
        String sql = "INSERT INTO user (`uid`, `image_link`, `fk_account_id`) VALUES (?,?,?)";
        try (Connection connection = connectionPool.getConnection();
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
    public void updateUser(int id, User user) throws DataStorageException {
        if(Objects.isNull(user)) throw new DataStorageException("User can`t be null");
        String sql = "UPDATE user SET rating=?, banned=?, uid=?, image_link=? WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps,
                    user.getRating(),
                    user.getBanned(),
                    user.getUid(),
                    user.getImageLink(),
                    id);

            ps.executeUpdate();
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    private User getUser(String sql, Object ...params) throws DataStorageException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, params);
            ResultSet rs = ps.executeQuery();
            return USER_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
