package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> listAllCategories() throws DataStorageException;
}
