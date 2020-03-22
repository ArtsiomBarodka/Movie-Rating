package epam.my.project.dao.impl;

import epam.my.project.dao.UserDAO;
import epam.my.project.db.handler.insert.InsertParametersHandler;
import epam.my.project.db.handler.select.ResultHandler;
import epam.my.project.db.handler.select.ResultHandlerFactory;
import epam.my.project.db.pool.impl.ConnectionPoolImpl;
import epam.my.project.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static final ResultHandler<User> USER_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.USER_RESULT_HANDLER);

    @Override
    public User getUserByAccountId(int accountId) throws SQLException {
        User result = null;
        String sql = "SELECT u.id, u.created, u.rating, c.rating, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE u.fk_account_id=?";

        try(Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, accountId);
            ResultSet rs = ps.executeQuery();
            result = USER_RESULT_ROW.handle(rs);
        }
        return result;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User result = null;
        String sql = "SELECT u.id, u.created, u.rating, c.rating, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE u.id=?";

        try(Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            ResultSet rs = ps.executeQuery();
            result = USER_RESULT_ROW.handle(rs);
        }
        return result;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        User result = null;
        String sql = "SELECT u.id, u.created, u.banned, u.rating, a.id, a.name, a.password, a.email, r.* " +
                "FROM user u " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN role r on r.id=a.fk_role_id " +
                "WHERE a.name=?";

        try(Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, name);
            ResultSet rs = ps.executeQuery();
            result = USER_RESULT_ROW.handle(rs);
        }
        return result;
    }

    @Override
    public int createUser(long accountId) throws SQLException {
        String sql = "INSERT INTO user (`fk_account_id`) VALUES (?)";
        try (Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps, accountId);
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new SQLException("Can't insert row to database. Result=" + result);
            }

            int id = -1;

            ResultSet generatedValues = ps.getGeneratedKeys();
            if(generatedValues.next()){
                id = generatedValues.getInt(1);
            }
            if (id < 0 ) {
                throw new SQLException("Can't generate values in database.");
            }

            return id;
        }
    }

    @Override
    public void updateUser(int id, User user) throws SQLException {
        String sql = "UPDATE user SET rating=?, banned=? WHERE id=?";
        try (Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps,
                    user.getRating(),
                    user.getBanned(),
                    id);

            ps.executeUpdate();
        }
    }
}
