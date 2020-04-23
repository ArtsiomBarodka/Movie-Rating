package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Country;

import java.util.List;

public interface CountryDAO {
    List<Country> listAllCountries() throws DataStorageException;
}
