package epam.my.project.dao;

import epam.my.project.entity.User;

import java.sql.SQLException;

public interface UserDAO {
    User getUserByAccountId(int accountId) throws SQLException;

    User getUserById(int id) throws SQLException;

    User getUserByName(String name) throws SQLException;

    int createUser(long accountId) throws SQLException;

    void updateUser(int id, User user) throws SQLException;
}
