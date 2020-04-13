package epam.my.project.dao.impl;

import epam.my.project.dao.GenreDAO;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {
    private static final ResultHandler<List<Genre>> GENRE_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.GENRE_RESULT_HANDLER);

    @Override
    public List<Genre> listAllGenres() {
        List<Genre> result = Collections.emptyList();
        String sql = "SELECT g.* FROM genre g ORDER BY id";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            result = GENRE_RESULT_LIST.handle(rs);
        } catch (SQLException e){

        }
        return result;
    }
}
