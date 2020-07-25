package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Country;

import java.util.List;

/**
 * The interface Country dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface CountryDAO {
    /**
     * List all countries list.
     *
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Country> listAllCountries() throws DataStorageException;
}
