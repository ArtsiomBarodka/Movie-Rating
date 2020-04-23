package epam.my.project.dao.impl;

import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.dao.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Account;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.logging.log4j.LogManager.getLogger;

public class AccountDAOImp implements AccountDAO {
    private static final Logger logger = getLogger(AccountDAOImp.class);
    private static final ResultHandler<Account> ACCOUNT_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.ACCOUNT_RESULT_HANDLER);
    private ConnectionPool connectionPool;

    public AccountDAOImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Account getAccountByName(String name) throws DataStorageException {
        String sql = "SELECT a.id, a.name, a.password, a.email, r.* " +
                "FROM account a " +
                "JOIN role r ON r.id=a.fk_role_id " +
                "WHERE a.name=?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps,name);
            ResultSet rs = ps.executeQuery();
            return ACCOUNT_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public Account getAccountByEmail(String email) throws DataStorageException {
        String sql = "SELECT a.id, a.name, a.password, a.email, r.* " +
                "FROM account a " +
                "JOIN role r ON r.id=a.fk_role_id " +
                "WHERE a.email=?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, email);
            ResultSet rs = ps.executeQuery();
            return ACCOUNT_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public Account getAccountByEmailAndPassword(String email, String password) throws DataStorageException {
        String sql = "SELECT a.id, a.name, a.password, a.email, r.* " +
                "FROM account a " +
                "JOIN role r ON r.id=a.fk_role_id " +
                "WHERE a.email=? AND a.password=?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, email, password);
            ResultSet rs = ps.executeQuery();
            return ACCOUNT_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public Account createAccount(Account account) throws DataStorageException {
        String sql = "INSERT INTO account (`name`, `password`, `email`, `fk_role_id`) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps,
                    account.getName(),
                    account.getPassword(),
                    account.getEmail(),
                    account.getRole().getId());

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
            if (id < 0) {
                logger.warn("Can't generate id in database. id = " + id);
                throw new DataStorageException("Can't generate id in database. id = " + id);
            }
            account.setId(id);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
        return account;
    }

    @Override
    public boolean deleteAccount(int id) throws DataStorageException {
        String sql = "DELETE FROM account WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            int result = ps.executeUpdate();
            return (result > 0);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public void updateAccount(int id, Account account) throws DataStorageException {
        String sql = "UPDATE account " +
                "SET name=?, password=?, email=?, fk_role_id=? " +
                "WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps,
                    account.getName(),
                    account.getPassword(),
                    account.getEmail(),
                    account.getRole().getId(),
                    id);

            ps.executeUpdate();
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
