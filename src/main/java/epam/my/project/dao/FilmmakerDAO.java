package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Filmmaker;

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
