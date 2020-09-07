package com.epam.mrating.dao;

import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.model.entity.Country;

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
