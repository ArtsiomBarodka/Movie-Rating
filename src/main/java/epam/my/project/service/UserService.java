package epam.my.project.service;

import epam.my.project.entity.User;
import epam.my.project.form.UserForm;

public interface UserService {
    User getUserById(int userId);

    User getUserByName(String name);

    User getUserByAccountId(int accountId);

    User createUser(UserForm userForm);

    User updateUser(UserForm userForm, int userId);

    boolean deleteUser(int accountId);
}
