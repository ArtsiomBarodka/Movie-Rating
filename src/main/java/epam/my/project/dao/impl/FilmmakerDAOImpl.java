package epam.my.project.dao.impl;

import epam.my.project.dao.FilmmakerDAO;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Filmmaker;
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
    private ConnectionPool connectionPool;

    public FilmmakerDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Filmmaker> listAllFilmmakers() throws DataStorageException {
        String sql = "SELECT f.* FROM filmmaker f ORDER BY id";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return FILMMAKER_RESULT.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
