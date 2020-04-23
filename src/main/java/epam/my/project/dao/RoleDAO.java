package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Role;

public interface RoleDAO {
    void createRole(String name) throws DataStorageException;

    Role getRoleByName(String name) throws DataStorageException;
}
