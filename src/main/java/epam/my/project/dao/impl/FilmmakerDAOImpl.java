package epam.my.project.dao.impl;

import epam.my.project.dao.FilmmakerDAO;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Filmmaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class FilmmakerDAOImpl implements FilmmakerDAO {
    private static final ResultHandler<List<Filmmaker>> FILMMAKER_RESULT =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.FILMMAKER_RESULT_HANDLER);

    @Override
    public List<Filmmaker> listAllFilmmakers() {
        List<Filmmaker> result = Collections.emptyList();
        String sql = "SELECT f.* FROM filmmaker f ORDER BY id";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            result = FILMMAKER_RESULT.handle(rs);
        } catch (SQLException e){

        }
        return result;
    }
}
