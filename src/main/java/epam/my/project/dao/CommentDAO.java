package epam.my.project.dao;

import epam.my.project.entity.Comment;

import java.util.List;

public interface CommentDAO {
    Comment getCommentById(long id);

    Comment createComment(Comment comment);

    boolean deleteComment(long id);

    void updateComment(long id, Comment comment);

    List<Comment> listAllCommentsByMovie(int movieId);

    List<Comment> listAllCommentsByUser(int userId);

}
