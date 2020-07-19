package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Comment;
import java.util.List;
import java.util.Optional;

/**
 * The interface Comment dao.
 */
public interface CommentDAO {
    /**
     * Gets comment by id.
     *
     * @param id the id
     * @return the comment by id
     * @throws DataStorageException the data storage exception
     */
    Optional<Comment> getCommentById(long id) throws DataStorageException;

    /**
     * Gets comment by user id and movie id.
     *
     * @param userId  the user id
     * @param movieId the movie id
     * @return the comment by user id and movie id
     * @throws DataStorageException the data storage exception
     */
    Optional<Comment> getCommentByUserIdAndMovieId(int userId, int movieId) throws DataStorageException;

    /**
     * Create comment long.
     *
     * @param comment the comment
     * @return the long
     * @throws DataStorageException the data storage exception
     */
    long createComment(Comment comment) throws DataStorageException;

    /**
     * Delete comment boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DataStorageException the data storage exception
     */
    boolean deleteComment(long id) throws DataStorageException;

    /**
     * Update comment.
     *
     * @param id      the id
     * @param comment the comment
     * @throws DataStorageException the data storage exception
     */
    void updateComment(long id, Comment comment) throws DataStorageException;

    /**
     * List all comments by movie list.
     *
     * @param movieId the movie id
     * @param offset  the offset
     * @param limit   the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Comment> listAllCommentsByMovie(int movieId, int offset, int limit) throws DataStorageException;

    /**
     * Count all comments by movie int.
     *
     * @param movieId the movie id
     * @return the int
     * @throws DataStorageException the data storage exception
     */
    int countAllCommentsByMovie(int movieId) throws DataStorageException;

    /**
     * List all comments by user list.
     *
     * @param userId the user id
     * @param offset the offset
     * @param limit  the limit
     * @return the list
     * @throws DataStorageException the data storage exception
     */
    List<Comment> listAllCommentsByUser(int userId, int offset, int limit) throws DataStorageException;

    /**
     * Count all comments by user int.
     *
     * @param userId the user id
     * @return the int
     * @throws DataStorageException the data storage exception
     */
    int countAllCommentsByUser(int userId) throws DataStorageException;

}
