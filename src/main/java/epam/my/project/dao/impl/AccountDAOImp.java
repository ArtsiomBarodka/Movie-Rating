package epam.my.project.dao.impl;

import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.dao.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Account;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Account dao imp.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class AccountDAOImp implements AccountDAO {
    private static final String SELECT_ACCOUNT = "SELECT a.id, a.name, a.password, a.email, r.* " +
            "FROM account a JOIN role r ON r.id=a.fk_role_id ";

    private static final Logger logger = getLogger(AccountDAOImp.class);

    private static final ResultHandler<Account> ACCOUNT_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.ACCOUNT_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new Account dao imp.
     *
     * @param connectionPool the connection pool
     */
     AccountDAOImp(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<Account> getAccountById(int id) throws DataStorageException {
        String sql = SELECT_ACCOUNT + "WHERE a.id=?";

        return Optional.ofNullable(getAccount(sql, id));
    }

    @Override
    public Optional<Account> getAccountByName(String name) throws DataStorageException {
        if(Objects.isNull(name)) throw new DataStorageException("Name can`t be null");
        String sql = SELECT_ACCOUNT + "WHERE a.name=?";

        return Optional.ofNullable(getAccount(sql, name));
    }

    @Override
    public Optional<Account> getAccountByEmail(String email) throws DataStorageException {
        if(Objects.isNull(email)) throw new DataStorageException("Email can`t be null");
        String sql = SELECT_ACCOUNT + "WHERE a.email=?";

        return Optional.ofNullable(getAccount(sql, email));
    }

    @Override
    public Optional<Account> getAccountByEmailAndPassword(String email, String password) throws DataStorageException {
        if(Objects.isNull(email)) throw new DataStorageException("Email can`t be null");
        if(Objects.isNull(password)) throw new DataStorageException("Password can`t be null");
        String sql = SELECT_ACCOUNT + "WHERE a.email=? AND a.password=?";

        return Optional.ofNullable(getAccount(sql, email, password));
    }

    @Override
    public Optional<Account> createAccount(Account account) throws DataStorageException {
        if(Objects.isNull(account)) throw new DataStorageException("Account can`t be null");
        String sql = "INSERT INTO account (`name`, `password`, `email`, `fk_role_id`) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps,
                    account.getName(),
                    account.getPassword(),
                    account.getEmail(),
                    account.getRole().getId());

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
            if (id < 0) {
                logger.warn(String.format("Can't generate id in database. id = %d", id));
                throw new DataStorageException(String.format("Can't generate id in database. id = %d", id));
            }
            account.setId(id);
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException(String.format("Can't execute SQL request: %s", e.getMessage()), e);
        }
        return Optional.ofNullable(account);
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
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public void updateAccount(int id, Account account) throws DataStorageException {
        if(Objects.isNull(account)) throw new DataStorageException("Account can`t be null");
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
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    private Account getAccount(String sql, Object ...params) throws DataStorageException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, params);
            ResultSet rs = ps.executeQuery();
            return ACCOUNT_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
