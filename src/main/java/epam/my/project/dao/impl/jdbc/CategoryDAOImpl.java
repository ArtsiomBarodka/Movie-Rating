package epam.my.project.dao.impl.jdbc;

import epam.my.project.dao.CategoryDAO;
import epam.my.project.dao.pool.ConnectionPool;
import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.dao.impl.jdbc.handler.ResultHandler;
import epam.my.project.dao.impl.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Category;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Category dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class CategoryDAOImpl implements CategoryDAO {
    private static final Logger logger = getLogger(CategoryDAOImpl.class);

    private static final ResultHandler<List<Category>> CATEGORY_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.CATEGORY_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new Category dao.
     *
     * @param connectionPool the connection pool
     */
     CategoryDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Category> listAllCategories() throws DataStorageException {
        String sql = "SELECT cat.* FROM category cat ORDER BY id";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            return CATEGORY_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn(String.format("Can't execute SQL request: %s", e.getMessage()), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
