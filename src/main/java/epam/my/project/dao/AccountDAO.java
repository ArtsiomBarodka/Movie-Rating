package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Account;

public interface AccountDAO {
    Account getAccountByName(String name) throws DataStorageException;

    Account getAccountByEmail(String email) throws DataStorageException;

    Account getAccountByEmailAndPassword(String email, String password) throws DataStorageException;

    Account createAccount(Account account) throws DataStorageException;

    boolean deleteAccount(int id) throws DataStorageException;

    void updateAccount(int id, Account account) throws DataStorageException;

}
