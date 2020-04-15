package epam.my.project.dao.impl;

import epam.my.project.dao.GenreDAO;
import epam.my.project.exception.logic.DataStorageException;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Genre;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class GenreDAOImpl implements GenreDAO {
    private static final Logger logger = getLogger(GenreDAOImpl.class);
    private static final ResultHandler<List<Genre>> GENRE_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.GENRE_RESULT_HANDLER);

    @Override
    public List<Genre> listAllGenres() {
        String sql = "SELECT g.* FROM genre g ORDER BY id";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return GENRE_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
