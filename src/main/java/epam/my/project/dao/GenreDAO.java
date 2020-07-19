package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Genre;
import java.util.List;
import java.util.Optional;

/**
 * The interface Genre dao.
 */
public interface GenreDAO {
    /**
     * Gets genre by movie uid.
     *
     * @param uidMovie the uid movie
     * @return the genre by movie uid
     * @throws DataStorageException the data storage exception
     */
    Optional<Genre> getGenreByMovieUid(String uidMovie) throws DataStorageException;

    /**
     * Gets genre by movie id.
     *
     * @param idMovie the id movie
     * @return the genre by movie id
     * @throws DataStorageException the data storage exception
     */
    Optional<Genre> getGenreByMovieId(int idMovie) throws DataStorageException;

    /**
     * List all genres list.
     *
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Genre> listAllGenres() throws DataStorageException;

    /**
     * Update genre.
     *
     * @param genre   the genre
     * @param idGenre the id genre
     * @throws DataStorageException the data storage exception
     */
    void updateGenre(Genre genre, int idGenre) throws DataStorageException;
}
