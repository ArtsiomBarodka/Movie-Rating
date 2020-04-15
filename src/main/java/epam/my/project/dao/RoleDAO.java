package epam.my.project.dao;

import epam.my.project.entity.Role;

public interface RoleDAO {
    void createRole(String name);

    Role getRoleByName(String name);
}
