package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.AccountAuthToken;

public interface AccountAuthTokenDAO {
    long createAccountAuthToken(AccountAuthToken accountAuthToken) throws DataStorageException;

    AccountAuthToken getAccountAuthTokenBySelector(String selector) throws DataStorageException;

    AccountAuthToken getAccountAuthTokenBySelectorAndValidator(String selector, String validator) throws DataStorageException;

    void updateAccountAuthToken(AccountAuthToken accountAuthToken, long idToken) throws DataStorageException;
}
