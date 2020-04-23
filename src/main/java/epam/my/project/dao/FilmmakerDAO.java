package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Filmmaker;

import java.util.List;

public interface FilmmakerDAO {
    List<Filmmaker> listAllFilmmakers() throws DataStorageException;
}
