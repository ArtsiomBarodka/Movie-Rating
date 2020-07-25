package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Account;
import java.util.Optional;

/**
 * The interface Account dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface AccountDAO {
    /**
     * Gets account by id.
     *
     * @param id the id
     * @return the account by id
     * @throws DataStorageException the data storage exception
     */
    Optional<Account> getAccountById(int id) throws DataStorageException;

    /**
     * Gets account by name.
     *
     * @param name the name
     * @return the account by name
     * @throws DataStorageException the data storage exception
     */
    Optional<Account> getAccountByName(String name) throws DataStorageException;

    /**
     * Gets account by email.
     *
     * @param email the email
     * @return the account by email
     * @throws DataStorageException the data storage exception
     */
    Optional<Account> getAccountByEmail(String email) throws DataStorageException;

    /**
     * Gets account by email and password.
     *
     * @param email    the email
     * @param password the password
     * @return the account by email and password
     * @throws DataStorageException the data storage exception
     */
    Optional<Account> getAccountByEmailAndPassword(String email, String password) throws DataStorageException;

    /**
     * Create account optional.
     *
     * @param account the account
     * @return the optional
     * @throws DataStorageException the data storage exception
     */
    Optional<Account> createAccount(Account account) throws DataStorageException;

    /**
     * Delete account boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DataStorageException the data storage exception
     */
    boolean deleteAccount(int id) throws DataStorageException;

    /**
     * Update account.
     *
     * @param id      the id
     * @param account the account
     * @throws DataStorageException the data storage exception
     */
    void updateAccount(int id, Account account) throws DataStorageException;

}
