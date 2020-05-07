package epam.my.project.dao.impl;

import epam.my.project.dao.AccountAuthTokenDAO;
import epam.my.project.dao.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.AccountAuthToken;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;

public class AccountAuthTokenDAOImpl implements AccountAuthTokenDAO {
    private static final Logger logger = getLogger(AccountAuthTokenDAOImpl.class);
    private static final ResultHandler<AccountAuthToken> TOKEN_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.ACCOUNT_AUTH_TOKEN_RESULT_HANDLER);
    private ConnectionPool connectionPool;

    public AccountAuthTokenDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public AccountAuthToken createAccountAuthToken(AccountAuthToken accountAuthToken) throws DataStorageException {
        if(Objects.isNull(accountAuthToken)) throw new DataStorageException("Account authentication token can`t be null");
        String sql = "INSERT INTO account_auth_token (`selector`, `validator`, `fk_account_id`) " +
                "VALUES (?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps,
                    accountAuthToken.getSelector(),
                    accountAuthToken.getValidator(),
                    accountAuthToken.getAccountId());

            int result = ps.executeUpdate();
            if (result != 1) {
                logger.warn("Can't insert row to database. Result = " + result);
                throw new DataStorageException("Can't insert row to database. Result = " + result);
            }
            long id = -1;
            ResultSet generatedValues = ps.getGeneratedKeys();
            if(generatedValues.next()){
                id = generatedValues.getLong(1);
            }
            if (id < 0) {
                logger.warn("Can't generate id in database. id = " + id);
                throw new DataStorageException("Can't generate id in database. id = " + id);
            }
            accountAuthToken.setId(id);
            return accountAuthToken;
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public AccountAuthToken getAccountAuthTokenBySelector(String selector) throws DataStorageException {
        if(Objects.isNull(selector)) throw new DataStorageException("Selector can`t be null");
        String sql = "SELECT t.* FROM account_auth_token t WHERE t.selector=?";
        return getAccountAuthToken(sql, selector);
    }

    @Override
    public AccountAuthToken getAccountAuthTokenBySelectorAndValidator(String selector, String validator) throws DataStorageException {
        if(Objects.isNull(selector)) throw new DataStorageException("Selector can`t be null");
        if(Objects.isNull(validator)) throw new DataStorageException("Validator can`t be null");
        String sql = "SELECT t.* FROM account_auth_token t WHERE t.selector=? AND t.validator=?";
        return getAccountAuthToken(sql, selector, validator);
    }

    @Override
    public void updateAccountAuthToken(AccountAuthToken accountAuthToken, long idToken) throws DataStorageException {
        if(Objects.isNull(accountAuthToken)) throw new DataStorageException("Account authentication token can`t be null");
        String sql = "UPDATE account_auth_token SET validator=? WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, accountAuthToken.getValidator(), idToken);
            ps.executeUpdate();
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    private AccountAuthToken getAccountAuthToken(String sql, Object ...params) throws DataStorageException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, params);
            ResultSet rs = ps.executeQuery();
            return TOKEN_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
