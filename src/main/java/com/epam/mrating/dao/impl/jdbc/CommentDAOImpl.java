package com.epam.mrating.dao.impl.jdbc;

import com.epam.mrating.dao.exception.DataStorageException;
import com.epam.mrating.dao.CommentDAO;
import com.epam.mrating.dao.pool.ConnectionPool;
import com.epam.mrating.dao.impl.jdbc.handler.InsertParametersHandler;
import com.epam.mrating.dao.impl.jdbc.handler.ResultHandler;
import com.epam.mrating.dao.impl.jdbc.handler.ResultHandlerFactory;
import com.epam.mrating.model.entity.Comment;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Comment dao.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class CommentDAOImpl implements CommentDAO {
    private static final String SELECT_COMMENT = "SELECT c.id, c.content, c.created, c.rating, u.id, u.uid, u.rating, u.image_link, a.name, a.id, m.id, m.uid, m.name, m.rating, m.image_link " +
            "FROM comment c JOIN user u ON u.id=c.fk_user_id JOIN account a ON a.id=u.fk_account_id JOIN movie m ON m.id=c.fk_movie_id ";

    private static final Logger logger = getLogger(CommentDAOImpl.class);

    private static final ResultHandler<Comment> COMMENT_RESULT_ROW =
            ResultHandlerFactory.getSingleResultHandler(ResultHandlerFactory.COMMENT_RESULT_HANDLER);

    private static final ResultHandler<List<Comment>> COMMENT_RESULT_LIST =
            ResultHandlerFactory.getListResultHandler(ResultHandlerFactory.COMMENT_RESULT_HANDLER);

    private ConnectionPool connectionPool;

    /**
     * Instantiates a new Comment dao.
     *
     * @param connectionPool the connection pool
     */
    CommentDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<Comment> getCommentById(long id) throws DataStorageException {
        String sql = SELECT_COMMENT + "WHERE c.id=?";

        return Optional.ofNullable(getComment(sql, id));
    }

    @Override
    public Optional<Comment> getCommentByUserIdAndMovieId(int userId, int movieId) throws DataStorageException {
        String sql = SELECT_COMMENT + "WHERE m.id=? AND u.id=?";
        return Optional.ofNullable(getComment(sql, movieId, userId));
    }

    private Comment getComment(String sql, Object ...params) throws DataStorageException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            InsertParametersHandler.handle(ps, params);
            ResultSet rs = ps.executeQuery();
            return COMMENT_RESULT_ROW.handle(rs);
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }

    @Override
    public long createComment(Comment comment) throws DataStorageException {
        if(Objects.isNull(comment)) throw new DataStorageException("Comment can`t be null");
        String sql = "INSERT INTO comment (`content`, `rating`, `fk_user_id`, `fk_movie_id`) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
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
            if (id < 0) {
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
        String sql = "UPDATE comment SET content=? WHERE id=?";
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
        String sql =  SELECT_COMMENT + "WHERE c.fk_movie_id=? ORDER BY c.created DESC LIMIT ? OFFSET ? ";

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
        String sql = SELECT_COMMENT + "WHERE c.fk_user_id=? ORDER BY c.created DESC LIMIT ? OFFSET ?";

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
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e){
            logger.warn("Can't execute SQL request: " + e.getMessage(), e);
            throw new DataStorageException("Can't execute SQL request: "+ e.getMessage(), e);
        }
    }
}
