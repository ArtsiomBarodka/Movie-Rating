package epam.my.project.service;

import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.User;
import epam.my.project.model.form.UserForm;

/**
 * The interface User service.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface UserService {
    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    User getUserById(int userId) throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * Gets user by u id.
     *
     * @param userUId the user u id
     * @return the user by u id
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    User getUserByUId(String userUId) throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * Gets user by name.
     *
     * @param name the name
     * @return the user by name
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    User getUserByName(String name) throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * Gets user by account id.
     *
     * @param accountId the account id
     * @return the user by account id
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    User getUserByAccountId(int accountId) throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * Create user user.
     *
     * @param accountId   the account id
     * @param accountName the account name
     * @return the user
     * @throws InternalServerErrorException the internal server error exception
     */
    User createUser(int accountId, String accountName) throws InternalServerErrorException;

    /**
     * Update user user.
     *
     * @param userForm the user form
     * @param userId   the user id
     * @return the user
     * @throws InternalServerErrorException the internal server error exception
     * @throws ObjectNotFoundException      the object not found exception
     * @throws ValidationException          the validation exception
     */
    User updateUser(UserForm userForm, int userId) throws InternalServerErrorException, ObjectNotFoundException, ValidationException;

    /**
     * Delete user boolean.
     *
     * @param accountId the account id
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     */
    boolean deleteUser(int accountId) throws InternalServerErrorException;
}
