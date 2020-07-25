package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Category;

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
