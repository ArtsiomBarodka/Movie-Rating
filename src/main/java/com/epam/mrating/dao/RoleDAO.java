package com.epam.mrating.dao;

import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.model.entity.Role;

import java.util.Optional;

/**
 * The interface Role dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface RoleDAO {
    /**
     * Create role.
     *
     * @param name the name
     * @throws DataStorageException the data storage exception
     */
    void createRole(String name) throws DataStorageException;

    /**
     * Gets role by name.
     *
     * @param name the name
     * @return the role by name
     * @throws DataStorageException the data storage exception
     */
    Optional<Role> getRoleByName(String name) throws DataStorageException;
}
