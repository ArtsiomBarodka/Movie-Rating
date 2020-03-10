package epam.my.project.dao;

import epam.my.project.entity.Comment;

import java.util.List;

public interface CommentDAO {
    Comment getCommentById(int id);

    int createComment(Comment comment);

    boolean deleteComment(int id);

    void updateComment(int id, Comment comment);

    List<Comment> listAllCommentsByMovie(String movieName);

    List<Comment> listAllCommentsByUser(String userName);

}
