package com.epam.mrating.dao;

import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.model.entity.Category;

import java.util.List;

/**
 * The interface Category dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface CategoryDAO {
    /**
     * List all categories list.
     *
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Category> listAllCategories() throws DataStorageException;
}
