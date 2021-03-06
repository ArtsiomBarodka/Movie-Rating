package com.epam.mrating.service;

import com.epam.mrating.model.domain.Page;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.service.exception.ValidationException;
import com.epam.mrating.model.entity.Comment;
import com.epam.mrating.model.form.CommentForm;

import java.util.List;

/**
 * The interface Comment service.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
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
