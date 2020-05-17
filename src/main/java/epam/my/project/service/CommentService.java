package epam.my.project.service;

import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.form.CommentForm;
import epam.my.project.model.domain.Page;

import java.util.List;

public interface CommentService {
    List<Comment> listAllCommentsByMovie(int movieId, Page page) throws ObjectNotFoundException, InternalServerErrorException;

    int countAllCommentsByMovie(int movieId) throws InternalServerErrorException;

    List<Comment> listAllCommentsByUser(int userId, Page page) throws ObjectNotFoundException, InternalServerErrorException;

    int countAllCommentsByUser(int userId) throws InternalServerErrorException;

    void createComment(CommentForm commentForm) throws InternalServerErrorException, ValidationException;

    void updateComment(long commentId, CommentForm commentForm) throws InternalServerErrorException, ObjectNotFoundException;

    void deleteComment(long commentId) throws InternalServerErrorException;
}
