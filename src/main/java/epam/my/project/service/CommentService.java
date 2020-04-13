package epam.my.project.service;

import epam.my.project.entity.Comment;
import epam.my.project.form.CommentForm;
import epam.my.project.model.Page;

import java.util.List;

public interface CommentService {
    List<Comment> listAllCommentsByMovie(int movieId, Page page);

    int countAllCommentsByMovie(int movieId);

    List<Comment> listAllCommentsByUser(int userId, Page page);

    int countAllCommentsByUser(int userId);

    boolean createComment(CommentForm commentForm);

    void updateComment(long commentId, CommentForm commentForm);

    void deleteComment(long commentId);
}
