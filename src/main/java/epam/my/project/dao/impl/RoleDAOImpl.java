package epam.my.project.dao.impl;

import epam.my.project.dao.RoleDAO;
import epam.my.project.jdbc.handler.InsertParametersHandler;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl implements RoleDAO {
    private static final ResultHandler<Role> ROLE_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.ROLE_RESULT_HANDLER);

    @Override
    public void createRole(String name) throws SQLException {
        String sql = "INSERT INTO role (`name`) VALUES (?)";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, name);
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new SQLException("Can't insert row to database. Result=" + result);
            }
        }
    }

    @Override
    public Role getRoleByName(String name) throws SQLException {
        Role result = null;
        String sql = "SELECT r.* " +
                "FROM role r " +
                "WHERE r.name=?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, name);
            ResultSet rs = ps.executeQuery();
            result = ROLE_RESULT_ROW.handle(rs);
        }
        return result;
    }
}
