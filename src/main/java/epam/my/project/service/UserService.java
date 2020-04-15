package epam.my.project.service;

import epam.my.project.entity.Account;
import epam.my.project.entity.User;
import epam.my.project.form.UserForm;

import java.io.IOException;
import java.sql.SQLException;

public interface UserService {
    User getUserById(int userId) throws SQLException;

    User getUserByName(String name) throws SQLException;

    User getUserByAccountId(int accountId) throws SQLException;

    User createUser(Account account, String imageUrl) throws SQLException, IOException;

    User updateUser(UserForm userForm, int userId) throws SQLException;

    boolean deleteUser(int accountId) throws SQLException;
}
