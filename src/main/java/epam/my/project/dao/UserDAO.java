package epam.my.project.dao;

import epam.my.project.entity.User;

public interface UserDAO {
    User getUserByAccountId(int accountId);

    User getUserById(int id);

    User getUserByName(String name);

    int createUser(User user);

    void updateUser(int id, User user);
}
