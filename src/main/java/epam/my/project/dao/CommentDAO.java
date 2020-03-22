package epam.my.project.dao;

import epam.my.project.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
    Comment getCommentById(long id) throws SQLException;

    long createComment(Comment comment) throws SQLException;

    boolean deleteComment(long id) throws SQLException;

    void updateComment(long id, Comment comment) throws SQLException;

    List<Comment> listAllCommentsByMovie(int movieId, int page, int limit) throws SQLException;

    List<Comment> listAllCommentsByUser(int userId, int page, int limit) throws SQLException;

}
