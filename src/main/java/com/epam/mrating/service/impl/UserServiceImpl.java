package com.epam.mrating.service.impl;

import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.model.form.UserForm;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.service.exception.ValidationException;
import com.epam.mrating.util.DataUtil;
import com.epam.mrating.configuration.Constants;
import com.epam.mrating.dao.AccountDAO;
import com.epam.mrating.dao.UserDAO;
import com.epam.mrating.dao.factory.DAOFactory;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.entity.Account;
import com.epam.mrating.model.entity.User;
import com.epam.mrating.service.UserService;

import java.util.Objects;
import java.util.Optional;

/**
 * The type User service.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private AccountDAO accountDAO;

    /**
     * Instantiates a new User service.
     *
     * @param daoFactory the dao factory
     */
    UserServiceImpl(DAOFactory daoFactory) {
        this.userDAO = daoFactory.getUserDAO();
        this.accountDAO = daoFactory.getAccountDAO();
    }

    /**
     * Instantiates a new User service.
     *
     * @param userDAO    the user dao
     * @param accountDAO the account dao
     */
    UserServiceImpl(UserDAO userDAO, AccountDAO accountDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public User getUserById(int userId) throws ObjectNotFoundException, InternalServerErrorException {
        try {
            Optional<User> user = userDAO.getUserById(userId);
            return user.orElseThrow(
                    ()->new ObjectNotFoundException("User not found"));
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get user from dao layer.", e);
        }
    }

    @Override
    public User getUserByUId(String userUId) throws ObjectNotFoundException, InternalServerErrorException {
        if(Objects.isNull(userUId)) throw new InternalServerErrorException("Users uid is null.");
        try {
            Optional<User> user = userDAO.getUserByUId(userUId);
            return user.orElseThrow(
                    ()->new ObjectNotFoundException("User not found"));
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get user from dao layer.", e);
        }
    }

    @Override
    public User getUserByName(String name) throws ObjectNotFoundException, InternalServerErrorException {
        if(Objects.isNull(name)) throw new InternalServerErrorException("User name is null.");
        try{
            Optional<User> user =  userDAO.getUserByName(name);
            return user.orElseThrow(
                    ()->new ObjectNotFoundException("User not found"));
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get user from dao layer.", e);
        }
    }

    @Override
    public User getUserByAccountId(int accountId) throws ObjectNotFoundException, InternalServerErrorException {
        try {
            Optional<User> user =  userDAO.getUserByAccountId(accountId);
            return user.orElseThrow(
                    ()->new ObjectNotFoundException("User not found"));
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get user from dao layer.", e);
        }
    }

    @Override
    public User createUser(int accountId, String accountName) throws InternalServerErrorException {
        if(Objects.isNull(accountName)) throw new InternalServerErrorException("Account name is null");
        try{
            User user = new User();
            user.setUid(DataUtil.generateUId(accountName));
            user.setImageLink(Constants.DEFAULT_IMAGE_LINK);
            Account account = new Account();
            account.setId(accountId);
            user.setAccount(account);
            userDAO.createUser(user);

            Optional<User> newUser = userDAO.getUserByAccountId(accountId);
            return newUser.orElseThrow(()->new InternalServerErrorException("User is null"));
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t create user from dao layer.", e);
        }
    }

    @Override
    public User updateUser(UserForm userForm, int userId) throws InternalServerErrorException, ObjectNotFoundException, ValidationException {
        if(Objects.isNull(userForm)) throw new InternalServerErrorException("Users form is null.");
        if(userForm.getViolations().hasErrors()){
            throw new ValidationException("User form has invalid inputs", userForm.getViolations());
        }
        try {
            Optional<User> optionalUser = userDAO.getUserById(userId);
            if(!optionalUser.isPresent()) throw new InternalServerErrorException("User is null.");
            User user = optionalUser.get();

            compareUserWithForm(userForm, user);
            userDAO.updateUser(userId, user);
            accountDAO.updateAccount(user.getAccount().getId(), user.getAccount());
            return user;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t update user from dao layer.", e);
        }
    }

    @Override
    public boolean deleteUser(int accountId) throws InternalServerErrorException {
        try {
            return accountDAO.deleteAccount(accountId);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t delete user from dao layer.", e);
        }
    }

    private void compareUserWithForm(UserForm userForm, User user){
        if(Objects.nonNull(userForm.getName()) &&
                !user.getAccount().getName().equals(userForm.getName())){

            user.getAccount().setName(userForm.getName());
            user.setUid(DataUtil.generateUId(userForm.getName()));
        }
        if(Objects.nonNull(userForm.getRating()) &&
                !user.getRating().equals(userForm.getRating())){

            user.setRating(userForm.getRating());
        }
        if(Objects.nonNull(userForm.getImageLink())
                && !user.getImageLink().equals(userForm.getImageLink())){

            user.setImageLink(userForm.getImageLink());
        }
        if(Objects.nonNull(userForm.isBanned()) &&
                !user.getBanned().equals(userForm.isBanned())){

            user.setBanned(userForm.isBanned());
        }
    }
}
