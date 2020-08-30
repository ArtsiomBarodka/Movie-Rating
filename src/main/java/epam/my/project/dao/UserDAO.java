package epam.my.project.dao;

import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.model.entity.User;
import java.util.Optional;

/**
 * The interface User dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface UserDAO {
    /**
     * Gets user by account id.
     *
     * @param accountId the account id
     * @return the user by account id
     * @throws DataStorageException the data storage exception
     */
    Optional<User> getUserByAccountId(int accountId) throws DataStorageException;

    /**
     * Gets user by u id.
     *
     * @param uid the uid
     * @return the user by u id
     * @throws DataStorageException the data storage exception
     */
    Optional<User> getUserByUId(String uid) throws DataStorageException;

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     * @throws DataStorageException the data storage exception
     */
    Optional<User> getUserById(int id) throws DataStorageException;

    /**
     * Gets user by name.
     *
     * @param name the name
     * @return the user by name
     * @throws DataStorageException the data storage exception
     */
    Optional<User> getUserByName(String name) throws DataStorageException;

    /**
     * Create user int.
     *
     * @param user the user
     * @return the int
     * @throws DataStorageException the data storage exception
     */
    int createUser(User user) throws DataStorageException;

    /**
     * Update user.
     *
     * @param id   the id
     * @param user the user
     * @throws DataStorageException the data storage exception
     */
    void updateUser(int id, User user) throws DataStorageException;
}
