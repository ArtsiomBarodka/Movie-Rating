package epam.my.project.main;

import epam.my.project.dao.CommentDAO;
import epam.my.project.dao.MovieDAO;
import epam.my.project.dao.impl.CommentDAOImpl;
import epam.my.project.dao.impl.MovieDAOImpl;
import epam.my.project.db.handler.insert.InsertParametersHandler;
import epam.my.project.db.pool.impl.ConnectionPoolImpl;
import epam.my.project.entity.Comment;
import epam.my.project.entity.Movie;
import epam.my.project.entity.User;


import java.sql.*;

public class MainTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.getConnection();
//        System.out.println(connection);
//        String sql = "SELECT m.id, m.name, m.image_link, m.description, m.year, m.budget, m.fees, m.duration, f.* , g.*, cat.*, c.*  " +
//                "FROM movie m " +
//                "JOIN filmmaker f ON f.id=m.fk_filmmaker_id " +
//                "JOIN genre g ON g.id=m.fk_genre_id " +
//                "JOIN category cat ON cat.id=m.fk_category_id " +
//                "JOIN country c ON c.id=m.fk_country_id " +
//                "ORDER BY m.id LIMIT ? OFFSET ?";
//        PreparedStatement pr = connection.prepareStatement(sql);
//        pr.setInt(1,1);
//        pr.setInt(2,0);
//
//        ResultSet rs = pr.executeQuery();
//        while (rs.next()){
//            System.out.println(rs.getInt("m.id"));
//            System.out.println(rs.getString("m.name"));
//            System.out.println(rs.getString("m.image_link"));
//            System.out.println(rs.getString("m.description"));
//            System.out.println(rs.getDate("m.year"));
//            System.out.println(rs.getLong("m.budget"));
//            System.out.println(rs.getLong("m.fees"));
//            System.out.println(rs.getTime("m.duration"));
//            System.out.println(rs.getInt("f.id"));
//            System.out.println(rs.getString("f.first_name"));
//            System.out.println(rs.getString("f.last_name"));
//            System.out.println(rs.getInt("g.id"));
//            System.out.println(rs.getString("g.name"));
//            System.out.println(rs.getInt("cat.id"));
//            System.out.println(rs.getString("cat.name"));
//            System.out.println(rs.getInt("c.id"));
//            System.out.println(rs.getString("c.name"));
//        }
//        String sql = "INSERT INTO movie (`image_link`) VALUES (?)";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        InsertParametersHandler.handle(ps,"image-link");
//        ps.executeUpdate();

        CommentDAO commentDAO = new CommentDAOImpl();
        Comment comment = commentDAO.getCommentById(144);
        System.out.println(comment);
        ConnectionPoolImpl.CONNECTION_POOL_INSTANCE.shutdown();

    }
}
