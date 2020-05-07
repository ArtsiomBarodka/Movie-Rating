package epam.my.project.service;

import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.User;
import epam.my.project.model.form.UserForm;

public interface UserService {
    User getUserById(int userId) throws ObjectNotFoundException, InternalServerErrorException;

    User getUserByUId(String userUId) throws ObjectNotFoundException, InternalServerErrorException;

    User getUserByName(String name) throws ObjectNotFoundException, InternalServerErrorException;

    User getUserByAccountId(int accountId) throws ObjectNotFoundException, InternalServerErrorException;

    User createUser(int accountId, String accountName) throws InternalServerErrorException;

    User updateUser(UserForm userForm, int userId) throws InternalServerErrorException, ObjectNotFoundException;

    boolean deleteUser(int accountId) throws InternalServerErrorException;
}
