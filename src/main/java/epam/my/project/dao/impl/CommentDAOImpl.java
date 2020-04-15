package epam.my.project.dao.impl;

import epam.my.project.dao.CommentDAO;
import epam.my.project.exception.logic.DataStorageException;
import epam.my.project.jdbc.handler.InsertParametersHandler;
import epam.my.project.jdbc.handler.ResultHandler;
import epam.my.project.jdbc.handler.ResultHandlerFactory;
import epam.my.project.jdbc.pool.impl.DataSource;
import epam.my.project.entity.Comment;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class CommentDAOImpl implements CommentDAO {
    private static final Logger logger = getLogger(CategoryDAOImpl.class);
    private static final ResultHandler<Comment> COMMENT_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.COMMENT_RESULT_HANDLER);
    private static final ResultHandler<List<Comment>> COMMENT_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.COMMENT_RESULT_HANDLER);

    @Override
    public Comment getCommentById(long id) {
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.id=?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
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
    public long createComment(Comment comment) {
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
                logger.warn("Can't insert row to database. Result = " + result);
                throw new DataStorageException("Can't insert row to database. Result = " + result);
            }

            long id = -1;

            ResultSet generatedValues = ps.getGeneratedKeys();
            if(generatedValues.next()){
                id = generatedValues.getLong(1);
            }
            if (id < 0 ) {
                logger.warn("Can't generate id in database. id = " + id);
                throw new DataStorageException("Can't generate id in database. id = " + id);
            }
            return id;
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteComment(long id) {
        String sql = "DELETE FROM comment WHERE id=?";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
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
    public void updateComment(long id, Comment comment) {
        String sql = "UPDATE comment " +
                "SET content=? " +
                "WHERE id=?";
        try (Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, comment.getContent(), id);
            ps.executeUpdate();
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Comment> listAllCommentsByMovie(int movieId, int page, int limit) {
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.fk_movie_id=? ORDER BY c.created LIMIT ? OFFSET ? ";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, movieId, page, limit);
            ResultSet rs = ps.executeQuery();
            return COMMENT_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public List<Comment> listAllCommentsByUser(int userId, int page, int limit) {
        String sql = "SELECT c.id, c.content, c.created, c.rating, u.id, u.rating, a.name, m.id, m.name, m.rating FROM comment c " +
                "JOIN user u ON u.id=c.fk_user_id " +
                "JOIN account a ON a.id=u.fk_account_id " +
                "JOIN movie m ON m.id=c.fk_movie_id " +
                "WHERE c.fk_user_id=? ORDER BY c.created LIMIT ? OFFSET ?";

        try(Connection connection = DataSource.CONNECTION_POOL_INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, userId, page, limit);
            ResultSet rs = ps.executeQuery();
            return COMMENT_RESULT_LIST.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
