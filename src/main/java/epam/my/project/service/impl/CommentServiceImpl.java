package epam.my.project.service.impl;

import epam.my.project.dao.CommentDAO;
import epam.my.project.dao.impl.jdbc.DAOFactory;
import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.service.exception.ValidationException;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.entity.User;
import epam.my.project.model.form.CommentForm;
import epam.my.project.model.domain.Page;
import epam.my.project.service.CommentService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Comment service.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class CommentServiceImpl implements CommentService {
    private CommentDAO commentDAO;

    /**
     * Instantiates a new Comment service.
     *
     * @param daoFactory the dao factory
     */
     CommentServiceImpl(DAOFactory daoFactory) {
        this.commentDAO = daoFactory.getCommentDAO();
    }

    /**
     * Instantiates a new Comment service.
     *
     * @param commentDAO the comment dao
     */
    CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public List<Comment> listAllCommentsByMovie(int movieId, Page page) throws ObjectNotFoundException, InternalServerErrorException {
        if (Objects.isNull(page)) throw new InternalServerErrorException("Page is null.");
        try {
            List<Comment> commentList = commentDAO.listAllCommentsByMovie(movieId, page.getOffset(), page.getLimit());
            if(Objects.isNull(commentList)) throw new ObjectNotFoundException("Comments not found");
            return commentList;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get comments from dao layer.", e);
        }
    }

    @Override
    public boolean commentAlreadyExist(int movieId, int userId) throws InternalServerErrorException {
        try {
            Optional<Comment> comment = commentDAO.getCommentByUserIdAndMovieId(userId, movieId);
            return comment.isPresent();
        } catch (DataStorageException e) {
            throw new InternalServerErrorException("Can`t get comment from dao layer.", e);
        }
    }

    @Override
    public int countAllCommentsByMovie(int movieId) throws InternalServerErrorException {
        try{
            return commentDAO.countAllCommentsByMovie(movieId);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get count of comments from dao layer.", e);
        }
    }

    @Override
    public List<Comment> listAllCommentsByUser(int userId, Page page) throws ObjectNotFoundException, InternalServerErrorException {
        if (Objects.isNull(page)) throw new InternalServerErrorException("Page is null.");
        try {
            List<Comment> commentList =  commentDAO.listAllCommentsByUser(userId, page.getOffset(), page.getLimit());
            if(Objects.isNull(commentList)) throw new ObjectNotFoundException("Comments not found");
            return commentList;
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get comments from dao layer.", e);
        }
    }

    @Override
    public int countAllCommentsByUser(int userId) throws InternalServerErrorException {
        try {
            return commentDAO.countAllCommentsByUser(userId);
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t get count of comments from dao layer.", e);
        }
    }

    @Override
    public Comment createComment(CommentForm commentForm) throws InternalServerErrorException, ValidationException, ObjectNotFoundException {
        if (Objects.isNull(commentForm)) throw new InternalServerErrorException("Comments form is null.");
        if(commentForm.getViolations().hasErrors()){
            throw new ValidationException("Comment form has invalid inputs", commentForm.getViolations());
        }
        try {
            Comment comment = new Comment();
            comment.setMovie(new Movie());
            comment.setUser(new User());
            compareCommentWithForm(commentForm, comment);
            long id = commentDAO.createComment(comment);

            Optional<Comment> newComment = commentDAO.getCommentById(id);
            if(newComment.isPresent()){
                return newComment.get();
            }
            throw new InternalServerErrorException("Can`t get created comment");
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t create comment from dao layer.", e);
        }
    }

    @Override
    public void updateComment(long commentId, CommentForm commentForm) throws InternalServerErrorException, ObjectNotFoundException {
        if (Objects.isNull(commentForm)) throw new InternalServerErrorException("Comments form is null.");
        try {
            Optional<Comment> comment = commentDAO.getCommentById(commentId);
            if(!comment.isPresent()) throw new ObjectNotFoundException("Comment not found");
            compareCommentWithForm(commentForm, comment.get());
            commentDAO.updateComment(commentId, comment.get());
        } catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t update comment from dao layer.", e);
        }

    }

    @Override
    public void deleteComment(long commentId) throws InternalServerErrorException {
        try {
            commentDAO.deleteComment(commentId);
        }catch (DataStorageException e){
            throw new InternalServerErrorException("Can`t delete comment from dao layer.", e);
        }
    }

    private void compareCommentWithForm(CommentForm commentForm, Comment comment){
        if(Objects.nonNull(commentForm.getContent()) &&
                !commentForm.getContent().equals(comment.getContent())){

            comment.setContent(commentForm.getContent());
        }
        if(Objects.nonNull(commentForm.getRating()) &&
                commentForm.getRating() != comment.getRating()){

            comment.setRating(commentForm.getRating());
        }
        if(Objects.nonNull(commentForm.getMovieId()) &&
                !commentForm.getMovieId().equals(comment.getMovie().getId())){

            comment.getMovie().setId(commentForm.getMovieId());
        }
        if(Objects.nonNull(commentForm.getUserId()) &&
                !commentForm.getUserId().equals(comment.getUser().getId())){

            comment.getUser().setId(commentForm.getUserId());
        }
    }
}
