package epam.my.project.dao.impl;

import epam.my.project.dao.CommentDAO;
import epam.my.project.jdbc.handler.InsertParametersHandler;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Comment;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    private static final ResultHandler<Comment> COMMENT_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.COMMENT_RESULT_HANDLER);
    private static final ResultHandler<List<Comment>> COMMENT_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.COMMENT_RESULT_HANDLER);

    @Override
    public Comment getCommentById(long id) throws SQLException {
        Comment result = null;
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.id=?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            ResultSet rs = ps.executeQuery();
            result = COMMENT_RESULT_ROW.handle(rs);
        }
        return result;
    }

    @Override
    public long createComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO comment (`content`, `rating`, `fk_user_id`, `fk_movie_id`) VALUES (?, ?, ?, ?)";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            InsertParametersHandler.handle(ps,
                    comment.getContent(),
                    comment.getRating(),
                    comment.getUser().getId(),
                    comment.getMovie().getId());

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new SQLException("Can't insert row to database. Result=" + result);
            }

            long id = -1;

            ResultSet generatedValues = ps.getGeneratedKeys();
            if(generatedValues.next()){
                id = generatedValues.getLong(1);
            }
            if (id < 0 ) {
                throw new SQLException("Can't generate values in database.");
            }

            return id;
        }
    }

    @Override
    public boolean deleteComment(long id) throws SQLException {
        String sql = "DELETE FROM comment WHERE id=?";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            int result = ps.executeUpdate();
            return (result > 0);
        }
    }

    @Override
    public void updateComment(long id, Comment comment) throws SQLException {
        String sql = "UPDATE comment " +
                "SET content=? " +
                "WHERE id=?";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, comment.getContent(), id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Comment> listAllCommentsByMovie(int movieId, int page, int limit) throws SQLException {
        List<Comment> result = Collections.emptyList();
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.fk_movie_id=? ORDER BY c.created LIMIT ? OFFSET ? ";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, movieId, page, limit);
            ResultSet rs = ps.executeQuery();
            result = COMMENT_RESULT_LIST.handle(rs);
        }
        return result;
    }

    @Override
    public List<Comment> listAllCommentsByUser(int userId, int page, int limit) throws SQLException {
        List<Comment> result = Collections.emptyList();
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.fk_user_id=? ORDER BY c.created LIMIT ? OFFSET ?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, userId, page, limit);
            ResultSet rs = ps.executeQuery();
            result = COMMENT_RESULT_LIST.handle(rs);
        }
        return result;
    }
}
