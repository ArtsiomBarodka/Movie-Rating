package epam.my.project.dao.impl;

import epam.my.project.dao.CountryDAO;
import epam.my.project.exception.logic.DataStorageException;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Country;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class CountryDAOImpl implements CountryDAO {
    private static final Logger logger = getLogger(CountryDAOImpl.class);
    private static final ResultHandler<List<Country>> COUNTRY_RESULT =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.COUNTRY_RESULT_HANDLER);

    @Override
    public List<Country> listAllCountries() {
        String sql = "SELECT c.* FROM country c ORDER BY id";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return COUNTRY_RESULT.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
