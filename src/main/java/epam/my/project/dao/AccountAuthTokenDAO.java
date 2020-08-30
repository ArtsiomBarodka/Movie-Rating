package epam.my.project.dao;

import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.model.entity.AccountAuthToken;
import java.util.Optional;


/**
 * The interface Account auth token dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface AccountAuthTokenDAO {

    /**
     * Create account auth token long.
     *
     * @param accountAuthToken the account auth token
     * @return the long
     * @throws DataStorageException the data storage exception
     */
    long createAccountAuthToken(AccountAuthToken accountAuthToken) throws DataStorageException;

    /**
     * Gets account auth token by selector.
     *
     * @param selector the selector
     * @return the account auth token by selector
     * @throws DataStorageException the data storage exception
     */
    Optional<AccountAuthToken> getAccountAuthTokenBySelector(String selector) throws DataStorageException;

    /**
     * Gets account auth token by selector and validator.
     *
     * @param selector  the selector
     * @param validator the validator
     * @return the account auth token by selector and validator
     * @throws DataStorageException the data storage exception
     */
    Optional<AccountAuthToken> getAccountAuthTokenBySelectorAndValidator(String selector, String validator) throws DataStorageException;

    /**
     * Update account auth token.
     *
     * @param accountAuthToken the account auth token
     * @param idToken          the id token
     * @throws DataStorageException the data storage exception
     */
    void updateAccountAuthToken(AccountAuthToken accountAuthToken, long idToken) throws DataStorageException;
}
