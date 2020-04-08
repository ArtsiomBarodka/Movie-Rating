package epam.my.project.main;

import epam.my.project.dao.CommentDAO;
import epam.my.project.dao.impl.CommentDAOImpl;
import epam.my.project.db.pool.impl.DataSource;


import java.sql.*;

public class MainTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
        CommentDAO commentDAO = new CommentDAOImpl();
        System.out.println(commentDAO.listAllCommentsByMovie(1,5,0));
        DataSource.CONNECTION_POOL_INSTANCE.shutdown();


    }
}
