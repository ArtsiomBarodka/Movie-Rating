package com.epam.mrating.dao;

import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.model.entity.Filmmaker;

import java.util.List;

/**
 * The interface Filmmaker dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface FilmmakerDAO {
    /**
     * List all filmmakers list.
     *
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Filmmaker> listAllFilmmakers() throws DataStorageException;
}
