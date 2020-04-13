package epam.my.project.dao;

import epam.my.project.entity.Role;

import java.sql.SQLException;

public interface RoleDAO {
    void createRole(String name) throws SQLException;

    Role getRoleByName(String name) throws SQLException;
}
