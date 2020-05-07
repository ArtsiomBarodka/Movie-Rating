package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Genre;

import java.util.List;

public interface GenreDAO {
    Genre getGenreByMovieId(int idMovie) throws DataStorageException;

    List<Genre> listAllGenres() throws DataStorageException;

    void updateGenre(Genre genre, int idGenre) throws DataStorageException;
}
