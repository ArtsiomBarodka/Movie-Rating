package epam.my.project.service.impl;

import epam.my.project.configuration.Constants;
import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.UserDAO;
import epam.my.project.dao.factory.DAOFactory;
import epam.my.project.exception.DataStorageException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Account;
import epam.my.project.model.entity.User;
import epam.my.project.model.form.UserForm;
import epam.my.project.service.UserService;
import epam.my.project.util.DataUtil;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private AccountDAO accountDAO;

    public UserServiceImpl(DAOFactory daoFactory) {
        this.userDAO = daoFactory.getUserDAO();
        this.accountDAO = daoFactory.getAccountDAO();
    }

    @Override
    public User getUserById(int userId) throws ObjectNotFoundException, InternalServerErrorException {
        try {
            User user = userDAO.getUserById(userId);
            if(Objects.isNull(user)){
                throw new ObjectNotFoundException("User not found");
            }
            return user;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get user from dao layer.", e);
        }
    }

    @Override
    public User getUserByUId(String userUId) throws ObjectNotFoundException, InternalServerErrorException {
        if(Objects.isNull(userUId)) throw new InternalServerErrorException("Users uid is null.");
        try {
            User user = userDAO.getUserByUId(userUId);
            if(Objects.isNull(user)){
                throw new ObjectNotFoundException("User not found");
            }
            return user;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get user from dao layer.", e);
        }
    }

    @Override
    public User getUserByName(String name) throws ObjectNotFoundException, InternalServerErrorException {
        if(Objects.isNull(name) || name.isEmpty()){
            throw new InternalServerErrorException("Invalid name value.");
        }
        try{
            User user =  userDAO.getUserByName(name);
            if(Objects.isNull(user)){
                throw new ObjectNotFoundException("User not found");
            }
            return user;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get user from dao layer.", e);
        }
    }

    @Override
    public User getUserByAccountId(int accountId) throws ObjectNotFoundException, InternalServerErrorException {
        try {
            User user =  userDAO.getUserByAccountId(accountId);
            if(Objects.isNull(user)){
                throw new ObjectNotFoundException("User not found");
            }
            return user;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get user from dao layer.", e);
        }
    }

    @Override
    public User createUser(int accountId, String accountName) throws InternalServerErrorException {
        try{
            User user = new User();
            user.setUid(DataUtil.generateUId(accountName));
            user.setImageLink(Constants.DEFAULT_IMAGE_LINK);
            Account account = new Account();
            account.setId(accountId);
            user.setAccount(account);
            userDAO.createUser(user);
            return userDAO.getUserByAccountId(accountId);
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
            User user = getUserById(userId);
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
