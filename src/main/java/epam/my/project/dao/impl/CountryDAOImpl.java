package epam.my.project.dao.impl;

import epam.my.project.dao.CountryDAO;
import epam.my.project.db.handler.select.ResultHandler;
import epam.my.project.db.handler.select.ResultHandlerFactory;
import epam.my.project.db.pool.impl.DataSource;
import epam.my.project.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {
    private static final ResultHandler<List<Country>> COUNTRY_RESULT =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.COUNTRY_RESULT_HANDLER);

    @Override
    public List<Country> listAllCountries() {
        List<Country> result = Collections.emptyList();
        String sql = "SELECT c.* FROM country c ORDER BY id";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            result = COUNTRY_RESULT.handle(rs);
        } catch (SQLException e){

        }
        return result;
    }
}
