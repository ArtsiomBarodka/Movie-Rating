package com.epam.mrating.dao.impl.jdbc;

import com.epam.mrating.dao.UserDAO;
import com.epam.mrating.dao.pool.ConnectionPool;
import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.dao.impl.jdbc.handler.InsertParametersHandler;
import com.epam.mrating.dao.impl.jdbc.handler.ResultHandler;
import com.epam.mrating.dao.impl.jdbc.handler.ResultHandlerFactory;
import com.epam.mrating.model.entity.User;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type User dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class UserDAOImpl implements UserDAO {
    private static final String SELECT_USER = "SELECT u.id, u.uid, u.image_link, u.created, u.rating, u.banned, a.id, a.name, a.password, a.email, r.* " +
            "FROM user u JOIN account a ON a.id=u.fk_account_id JOIN role r on r.id=a.fk_role_id ";

    private static final Logger logger = getLogger(UserDAOImpl.class);

    private static final ResultHandler<User> USER_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.USER_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new User dao.
     *
     * @param connectionPool the connection pool
     */
    UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<User> getUserByAccountId(int accountId) throws DataStorageException {
        String sql = SELECT_USER + "WHERE u.fk_account_id=?";

        return Optional.ofNullable(getUser(sql, accountId));
    }

    @Override
    public Optional<User> getUserByUId(String uid) throws DataStorageException {
        if(Objects.isNull(uid)) throw new DataStorageException("User uid can`t be null");
        String sql = SELECT_USER + "WHERE u.uid=?";

        return Optional.ofNullable(getUser(sql, uid));
    }

    @Override
    public Optional<User> getUserById(int id) throws DataStorageException {
        String sql = SELECT_USER + "WHERE u.id=?";

        return Optional.ofNullable(getUser(sql, id));
    }

    @Override
    public Optional<User> getUserByName(String name) throws DataStorageException {
        if(Objects.isNull(name)) throw new DataStorageException("Name can`t be null");
        String sql = SELECT_USER + "WHERE a.name=?";

        return Optional.ofNullable(getUser(sql, name));
    }

    @Override
    public int createUser(User user) throws DataStorageException {
        if(Objects.isNull(user)) throw new DataStorageException("User can`t be null");
        String sql = "INSERT INTO user (`uid`, `image_link`, `fk_account_id`) VALUES (?,?,?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps,
                    user.getUid(),
                    user.getImageLink(),
                    user.getAccount().getId());

            int result = ps.executeUpdate();
            if (result != 1) {
                logger.warn(String.format("Can't insert row to database. Result = %d", result));
                throw new DataStorageException("Can't insert row to database. Result = " + result);
            }

            int id = -1;

            ResultSet generatedValues = ps.getGeneratedKeys();
            if(generatedValues.next()){
                id = generatedValues.getInt(1);
            }
            if (id < 0 ) {
                logger.warn(String.format("Can't generate id in database. id = %d", id));
                throw new DataStorageException("Can't generate id in database. id = " + id);
            }

            return id;
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
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
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
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
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
