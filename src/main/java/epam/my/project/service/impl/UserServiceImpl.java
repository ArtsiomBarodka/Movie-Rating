package epam.my.project.service.impl;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.ImageCategory;
import epam.my.project.dao.AccountDAO;
import epam.my.project.dao.UserDAO;
import epam.my.project.dao.impl.AccountDAOImp;
import epam.my.project.dao.impl.UserDAOImpl;
import epam.my.project.entity.Account;
import epam.my.project.entity.User;
import epam.my.project.form.UserForm;
import epam.my.project.service.UserService;
import epam.my.project.util.DataUtil;
import epam.my.project.validation.ServiceValidatorFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int userId) throws SQLException {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getUserByName(name);
    }

    @Override
    public User getUserByAccountId(int accountId) throws SQLException {
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.getUserByAccountId(accountId);
    }

    @Override
    public User createUser(Account account, String imageUrl) throws SQLException, IOException {
        //validate

        ImageServiceImpl imageService = new ImageServiceImpl();

        UserDAO userDAO = new UserDAOImpl();
        User user = new User();
        user.setUid(DataUtil.generateUId(account.getName()));
        if(Objects.isNull(imageUrl)){
            user.setImageLink(Constants.DEFAULT_USER_IMAGE_LINK);
        } else {
            user.setImageLink(imageService.downloadImageFromUrl(imageUrl, ImageCategory.USER_PHOTO));
        }

        user.setAccount(account);
        userDAO.createUser(user);
        return userDAO.getUserByAccountId(account.getId());
    }

    @Override
    public User updateUser(UserForm userForm, int userId) throws SQLException {
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUserById(userId);
        compareUserWithForm(userForm, user);
        return user;
    }

    @Override
    public boolean deleteUser(int accountId) throws SQLException {
        AccountDAO accountDAO = new AccountDAOImp();
        return accountDAO.deleteAccount(accountId);
    }

    private void compareUserWithForm(UserForm userForm, User user){
        if (ServiceValidatorFactory.NAME_VALIDATOR.validate(userForm.getName())){
            if(!user.getAccount().getName().equals(userForm.getName())){
                user.getAccount().setName(userForm.getName());
            }
        }

        if (ServiceValidatorFactory.USER_RATING_VALIDATOR.validate(userForm.getRating())){
            Integer rating = Integer.parseInt(userForm.getRating());
            if(!user.getRating().equals(rating)){
                user.setRating(rating);
            }
        }

        if (Objects.nonNull(userForm.getImageLink())){
            if(!user.getImageLink().equals(userForm.getImageLink())){
                user.setImageLink(userForm.getImageLink());
            }
        }

        if(!user.getBanned().equals(userForm.isBanned())){
            user.setBanned(userForm.isBanned());
        }
    }
}
