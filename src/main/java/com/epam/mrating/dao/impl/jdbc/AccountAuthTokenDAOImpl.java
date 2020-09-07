package com.epam.mrating.dao.impl.jdbc;

import com.epam.mrating.dao.AccountAuthTokenDAO;
import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.dao.impl.jdbc.handler.InsertParametersHandler;
import com.epam.mrating.dao.impl.jdbc.handler.ResultHandler;
import com.epam.mrating.dao.impl.jdbc.handler.ResultHandlerFactory;
import com.epam.mrating.dao.pool.ConnectionPool;
import com.epam.mrating.model.entity.AccountAuthToken;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Account auth token dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class AccountAuthTokenDAOImpl implements AccountAuthTokenDAO {
    private static final String SELECT_ACCOUNT_AUTH_TOKEN = "SELECT t.* FROM account_auth_token t ";

    private static final Logger logger = getLogger(AccountAuthTokenDAOImpl.class);

    private static final ResultHandler<AccountAuthToken> TOKEN_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.ACCOUNT_AUTH_TOKEN_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new Account auth token dao.
     *
     * @param connectionPool the connection pool
     */
    AccountAuthTokenDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public long createAccountAuthToken(AccountAuthToken accountAuthToken) throws DataStorageException {
        if(Objects.isNull(accountAuthToken)) throw new DataStorageException("Account authentication token can`t be null");
        String sql = "INSERT INTO account_auth_token (`selector`, `validator`, `fk_account_id`) " +
                "VALUES (?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps,
                    accountAuthToken.getSelector(),
                    accountAuthToken.getValidator(),
                    accountAuthToken.getAccountId());

            int result = ps.executeUpdate();
            if (result != 1) {
                logger.warn(String.format("Can't insert row to database. Result = %d", result));
                throw new DataStorageException("Can't insert row to database. Result = " + result);
            }
            long id = -1;
            ResultSet generatedValues = ps.getGeneratedKeys();
            if(generatedValues.next()){
                id = generatedValues.getLong(1);
            }
            if (id < 0) {
                logger.warn(String.format("Can't generate id in database. id = %d", id));
                throw new DataStorageException(String.format("Can't generate id in database. id = %d", id));
            }
            return id;
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException(String.format("Can't execute SQL request: %s", e.getMessage()), e);
        }
    }

    @Override
    public Optional<AccountAuthToken> getAccountAuthTokenBySelector(String selector) throws DataStorageException {
        if(Objects.isNull(selector)) throw new DataStorageException("Selector can`t be null");
        String sql = SELECT_ACCOUNT_AUTH_TOKEN + "WHERE t.selector=?";
        return Optional.ofNullable(getAccountAuthToken(sql, selector));
    }

    @Override
    public Optional<AccountAuthToken> getAccountAuthTokenBySelectorAndValidator(String selector, String validator) throws DataStorageException {
        if(Objects.isNull(selector)) throw new DataStorageException("Selector can`t be null");
        if(Objects.isNull(validator)) throw new DataStorageException("Validator can`t be null");
        String sql = SELECT_ACCOUNT_AUTH_TOKEN + "WHERE t.selector=? AND t.validator=?";
        return Optional.ofNullable(getAccountAuthToken(sql, selector, validator));
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
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
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
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
