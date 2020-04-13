package epam.my.project.dao.impl;

import epam.my.project.dao.CategoryDAO;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final ResultHandler<List<Category>> CATEGORY_RESULT =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.CATEGORY_RESULT_HANDLER);

    @Override
    public List<Category> listAllCategories() {
        List<Category> result = Collections.emptyList();
        String sql = "SELECT cat.* FROM category cat ORDER BY id";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            result = CATEGORY_RESULT.handle(rs);
        } catch (SQLException e){

        }
        return result;
    }
}
