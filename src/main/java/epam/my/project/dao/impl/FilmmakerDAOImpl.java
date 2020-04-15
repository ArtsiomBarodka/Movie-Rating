package epam.my.project.dao.impl;

import epam.my.project.dao.FilmmakerDAO;
import epam.my.project.exception.logic.DataStorageException;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Filmmaker;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class FilmmakerDAOImpl implements FilmmakerDAO {
    private static final Logger logger = getLogger(FilmmakerDAOImpl.class);
    private static final ResultHandler<List<Filmmaker>> FILMMAKER_RESULT =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.FILMMAKER_RESULT_HANDLER);

    @Override
    public List<Filmmaker> listAllFilmmakers() {
        String sql = "SELECT f.* FROM filmmaker f ORDER BY id";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return FILMMAKER_RESULT.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
