package epam.my.project.dao.impl;

import epam.my.project.dao.CommentDAO;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
import epam.my.project.exception.DataStorageException;
import epam.my.project.dao.jdbc.handler.InsertParametersHandler;
import epam.my.project.dao.jdbc.handler.ResultHandler;
import epam.my.project.dao.jdbc.handler.ResultHandlerFactory;
import epam.my.project.model.entity.Comment;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;

public class CommentDAOImpl implements CommentDAO {
    private static final Logger logger = getLogger(CategoryDAOImpl.class);
    private static final ResultHandler<Comment> COMMENT_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.COMMENT_RESULT_HANDLER);
    private static final ResultHandler<List<Comment>> COMMENT_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.COMMENT_RESULT_HANDLER);
    private ConnectionPool connectionPool;

    public CommentDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Comment getCommentById(long id) throws DataStorageException {
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.id=?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            ResultSet rs = ps.executeQuery();
            return COMMENT_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public void createComment(Comment comment) throws DataStorageException {
        if(Objects.isNull(comment)) throw new DataStorageException("Comment can`t be null");
        String sql = "INSERT INTO comment (`content`, `rating`, `fk_user_id`, `fk_movie_id`) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps,
                    comment.getContent(),
                    comment.getRating(),
                    comment.getUser().getId(),
                    comment.getMovie().getId());

            int result = ps.executeUpdate();
            if (result != 1) {
                logger.warn("Can't insert row to database. Result = " + result);
                throw new DataStorageException("Can't insert row to database. Result = " + result);
            }
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteComment(long id) throws DataStorageException {
        String sql = "DELETE FROM comment WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, id);
            int result = ps.executeUpdate();
            return (result > 0);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public void updateComment(long id, Comment comment) throws DataStorageException {
        if(Objects.isNull(comment)) throw new DataStorageException("Comment can`t be null");
        String sql = "UPDATE comment " +
                "SET content=? " +
                "WHERE id=?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, comment.getContent(), id);
            ps.executeUpdate();
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Comment> listAllCommentsByMovie(int movieId, int offset, int limit) throws DataStorageException {
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.fk_movie_id=? ORDER BY c.created LIMIT ? OFFSET ? ";

        return getListComments(sql, movieId, limit, offset);
    }

    @Override
    public int countAllCommentsByMovie(int movieId) throws DataStorageException {
        String sql = "SELECT count(*) FROM comment c " +
                "JOIN movie m ON m.id=c.fk_movie_id" +
                " WHERE c.fk_movie_id=?";

        return countAllComments(sql, movieId);
    }

    @Override
    public List<Comment> listAllCommentsByUser(int userId, int offset, int limit) throws DataStorageException {
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.fk_user_id=? ORDER BY c.created LIMIT ? OFFSET ?";

        return getListComments(sql, userId, limit, offset);
    }

    @Override
    public int countAllCommentsByUser(int userId) throws DataStorageException {
        String sql = "SELECT count(*) FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "WHERE c.fk_user_id=?";

        return countAllComments(sql, userId);
    }

    private List<Comment> getListComments(String sql, Object ...params) throws DataStorageException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, params);
            ResultSet rs = ps.executeQuery();
            return COMMENT_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    private int countAllComments(String sql, Object ...params) throws DataStorageException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, params);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
