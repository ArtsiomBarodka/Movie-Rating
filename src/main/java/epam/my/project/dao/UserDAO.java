package epam.my.project.dao;

import epam.my.project.entity.Account;
import epam.my.project.entity.User;

public interface UserDAO {
    int createUser(Account account);

    void updateUser(int id, User user);
}
