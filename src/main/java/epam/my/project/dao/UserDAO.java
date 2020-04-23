package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.User;

public interface UserDAO {
    User getUserByAccountId(int accountId) throws DataStorageException;

    User getUserById(int id) throws DataStorageException;

    User getUserByName(String name) throws DataStorageException;

    int createUser(User user) throws DataStorageException;

    void updateUser(int id, User user) throws DataStorageException;
}
