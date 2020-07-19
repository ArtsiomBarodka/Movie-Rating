package epam.my.project.service;

import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.form.CommentForm;
import epam.my.project.model.domain.Page;

import java.util.List;

/**
 * The interface Comment service.
 */
public interface CommentService {
    /**
     * List all comments by movie list.
     *
     * @param movieId the movie id
     * @param page    the page
     * @return the list
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    List<Comment> listAllCommentsByMovie(int movieId, Page page) throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * Comment already exist boolean.
     *
     * @param movieId the movie id
     * @param userId  the user id
     * @return the boolean
     * @throws InternalServerErrorException the internal server error exception
     */
    boolean commentAlreadyExist(int movieId, int userId) throws InternalServerErrorException;

    /**
     * Count all comments by movie int.
     *
     * @param movieId the movie id
     * @return the int
     * @throws InternalServerErrorException the internal server error exception
     */
    int countAllCommentsByMovie(int movieId) throws InternalServerErrorException;

    /**
     * List all comments by user list.
     *
     * @param userId the user id
     * @param page   the page
     * @return the list
     * @throws ObjectNotFoundException      the object not found exception
     * @throws InternalServerErrorException the internal server error exception
     */
    List<Comment> listAllCommentsByUser(int userId, Page page) throws ObjectNotFoundException, InternalServerErrorException;

    /**
     * Count all comments by user int.
     *
     * @param userId the user id
     * @return the int
     * @throws InternalServerErrorException the internal server error exception
     */
    int countAllCommentsByUser(int userId) throws InternalServerErrorException;

    /**
     * Create comment comment.
     *
     * @param commentForm the comment form
     * @return the comment
     * @throws InternalServerErrorException the internal server error exception
     * @throws ValidationException          the validation exception
     * @throws ObjectNotFoundException      the object not found exception
     */
    Comment createComment(CommentForm commentForm) throws InternalServerErrorException, ValidationException, ObjectNotFoundException;

    /**
     * Update comment.
     *
     * @param commentId   the comment id
     * @param commentForm the comment form
     * @throws InternalServerErrorException the internal server error exception
     * @throws ObjectNotFoundException      the object not found exception
     */
    void updateComment(long commentId, CommentForm commentForm) throws InternalServerErrorException, ObjectNotFoundException;

    /**
     * Delete comment.
     *
     * @param commentId the comment id
     * @throws InternalServerErrorException the internal server error exception
     */
    void deleteComment(long commentId) throws InternalServerErrorException;
}
