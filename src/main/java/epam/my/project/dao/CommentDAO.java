package epam.my.project.dao;

import epam.my.project.exception.DataStorageException;
import epam.my.project.model.entity.Comment;

import java.util.List;

public interface CommentDAO {
    Comment getCommentById(long id) throws DataStorageException;

    void createComment(Comment comment) throws DataStorageException;

    boolean deleteComment(long id) throws DataStorageException;

    void updateComment(long id, Comment comment) throws DataStorageException;

    List<Comment> listAllCommentsByMovie(int movieId, int offset, int limit) throws DataStorageException;

    int countAllCommentsByMovie(int movieId) throws DataStorageException;

    List<Comment> listAllCommentsByUser(int userId, int offset, int limit) throws DataStorageException;

    int countAllCommentsByUser(int userId) throws DataStorageException;

}
