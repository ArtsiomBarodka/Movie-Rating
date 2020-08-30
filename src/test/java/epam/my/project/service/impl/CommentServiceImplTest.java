package epam.my.project.service.impl;

import epam.my.project.dao.CommentDAO;
import epam.my.project.dao.exception.DataStorageException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.service.exception.ValidationException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.form.CommentForm;
import epam.my.project.component.validator.model.Violations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CommentDAO.class})
public class CommentServiceImplTest {
    private CommentDAO commentDAO;
    private CommentServiceImpl commentService;

    @Before
    public void setUp() throws Exception {
        commentDAO = mock(CommentDAO.class);
        commentService = new CommentServiceImpl(commentDAO);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllCommentsByMovie_NULL_PAGE_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        int movieId = 1;
        Page page = null;
        commentService.listAllCommentsByMovie(movieId, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllCommentsByMovie_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        int movieId = 1;
        Page page = mock(Page.class);
        when(page.getOffset()).thenReturn(1);
        when(page.getLimit()).thenReturn(1);
        when(commentDAO.listAllCommentsByMovie(anyInt(),anyInt(),anyInt())).thenThrow(new DataStorageException(""));
        commentService.listAllCommentsByMovie(movieId, page);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllCommentsByMovie_DAO_LAYER_RETURN_NULL() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        int movieId = 1;
        Page page = mock(Page.class);
        when(page.getOffset()).thenReturn(1);
        when(page.getLimit()).thenReturn(1);
        when(commentDAO.listAllCommentsByMovie(anyInt(),anyInt(),anyInt())).thenReturn(null);
        commentService.listAllCommentsByMovie(movieId, page);
    }

    @Test
    public void testListAllCommentsByMovie_SHOULD_RETURN_LIST() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        int movieId = 1;
        Page page = mock(Page.class);
        when(page.getOffset()).thenReturn(1);
        when(page.getLimit()).thenReturn(1);
        when(commentDAO.listAllCommentsByMovie(1,1,1)).thenReturn(Collections.emptyList());
        List<Comment> result = commentService.listAllCommentsByMovie(movieId, page);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCommentAlreadyExist_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException {
        int movieId = 1;
        int userId = 1;
        when(commentDAO.getCommentByUserIdAndMovieId(anyInt(),anyInt())).thenThrow(new DataStorageException(""));
        commentService.commentAlreadyExist(movieId, userId);
    }

    @Test
    public void testCommentAlreadyExist_DAO_LAYER_RETURN_NULL() throws DataStorageException, InternalServerErrorException {
        int movieId = 1;
        int userId = 1;
        when(commentDAO.getCommentByUserIdAndMovieId(anyInt(),anyInt())).thenReturn(Optional.ofNullable(null));
        boolean result = commentService.commentAlreadyExist(movieId, userId);

        assertFalse(result);
    }

    @Test
    public void testCommentAlreadyExist_SHOULD_RETURN_TRUE() throws DataStorageException, InternalServerErrorException {
        int movieId = 1;
        int userId = 1;
        when(commentDAO.getCommentByUserIdAndMovieId(1,1))
                .thenReturn(Optional.ofNullable(mock(Comment.class)));
        boolean result = commentService.commentAlreadyExist(movieId, userId);

        assertTrue(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCountAllCommentsByMovie_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException {
        int movieId = 1;
        when(commentDAO.countAllCommentsByMovie(anyInt())).thenThrow(new DataStorageException(""));
        commentService.countAllCommentsByMovie(movieId);
    }

    @Test
    public void testCountAllCommentsByMovie_SHOULD_RETURN_INT() throws DataStorageException, InternalServerErrorException {
        int movieId = 1;
        int expected = 10;
        when(commentDAO.countAllCommentsByMovie(1)).thenReturn(10);
        int result = commentService.countAllCommentsByMovie(movieId);

        assertEquals(expected, result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllCommentsByUser_NULL_PAGE_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        int userId = 1;
        Page page = null;
        commentService.listAllCommentsByUser(userId, page);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testListAllCommentsByUser_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        int userId = 1;
        Page page = mock(Page.class);
        when(page.getOffset()).thenReturn(1);
        when(page.getLimit()).thenReturn(1);
        when(commentDAO.listAllCommentsByUser(anyInt(),anyInt(),anyInt())).thenThrow(new DataStorageException(""));
        commentService.listAllCommentsByUser(userId, page);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testListAllCommentsByUser_DAO_LAYER_RETURN_NULL() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        int userId = 1;
        Page page = mock(Page.class);
        when(page.getOffset()).thenReturn(1);
        when(page.getLimit()).thenReturn(1);
        when(commentDAO.listAllCommentsByUser(anyInt(),anyInt(),anyInt())).thenReturn(null);
        commentService.listAllCommentsByUser(userId, page);
    }

    @Test
    public void testListAllCommentsByUser_SHOULD_RETURN_LIST() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        int userId = 1;
        Page page = mock(Page.class);
        when(page.getOffset()).thenReturn(1);
        when(page.getLimit()).thenReturn(1);
        when(commentDAO.listAllCommentsByUser(1,1,1)).thenReturn(Collections.emptyList());
        List<Comment> result = commentService.listAllCommentsByUser(userId, page);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCountAllCommentsByUser_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException {
        int userId = 1;
        when(commentDAO.countAllCommentsByUser(anyInt())).thenThrow(new DataStorageException(""));
        commentService.countAllCommentsByUser(userId);
    }

    @Test
    public void testCountAllCommentsByUser_SHOULD_RETURN_INT() throws DataStorageException, InternalServerErrorException {
        int userId = 1;
        int expected = 10;
        when(commentDAO.countAllCommentsByUser(1)).thenReturn(10);
        int result = commentService.countAllCommentsByUser(userId);

        assertEquals(expected, result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateComment_NULL_COMMENT_FORM_PARAMETER() throws InternalServerErrorException, ValidationException, ObjectNotFoundException {
        CommentForm commentForm = null;
        commentService.createComment(commentForm);
    }

    @Test(expected = ValidationException.class)
    public void testCreateComment_COMMENT_FORM_HAS_ERROR() throws InternalServerErrorException, ValidationException, ObjectNotFoundException {
        CommentForm commentForm = mock(CommentForm.class);
        when(commentForm.getViolations()).thenReturn(mock(Violations.class));
        when(commentForm.getViolations().hasErrors()).thenReturn(true);
        commentService.createComment(commentForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateComment_DAO_LAYER_THROW_EXCEPTION() throws Exception {
        CommentForm commentForm = spy(new CommentForm());
        when(commentForm.getViolations()).thenReturn(mock(Violations.class));
        when(commentForm.getViolations().hasErrors()).thenReturn(false);
        when(commentDAO.createComment(any(Comment.class))).thenThrow(new DataStorageException(""));
        commentService.createComment(commentForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testCreateComment_DAO_LAYER_RETURN_NULL() throws Exception {
        CommentForm commentForm = spy(new CommentForm());
        when(commentForm.getViolations()).thenReturn(mock(Violations.class));
        when(commentForm.getViolations().hasErrors()).thenReturn(false);
        when(commentDAO.createComment(any(Comment.class))).thenReturn(1L);
        when(commentDAO.getCommentById(anyLong())).thenReturn(Optional.ofNullable(null));
        commentService.createComment(commentForm);
    }

    @Test
    public void testCreateComment_SHOULD_RETURN_NEW_COMMENT() throws Exception {
        CommentForm commentForm = spy(new CommentForm());
        when(commentForm.getViolations())
                .thenReturn(mock(Violations.class));
        when(commentForm.getViolations().hasErrors())
                .thenReturn(false);
        when(commentDAO.createComment(any(Comment.class)))
                .thenReturn(1L);
        when(commentDAO.getCommentById(anyLong()))
                .thenReturn(Optional.ofNullable(mock(Comment.class)));
        Comment result = commentService.createComment(commentForm);

        assertNotNull(result);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateComment_NULL_COMMENT_FORM_PARAMETER() throws InternalServerErrorException, ObjectNotFoundException {
        long commentId = 1L;
        CommentForm commentForm = null;
        commentService.updateComment(commentId, commentForm);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testUpdateComment_DAO_LAYER_RETURN_NULL_COMMENT() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        long commentId = 1L;
        CommentForm commentForm = mock(CommentForm.class);
        when(commentDAO.getCommentById(anyLong())).thenReturn(Optional.ofNullable(null));
        commentService.updateComment(commentId, commentForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testUpdateComment_DAO_LAYER_THROW_EXCEPTION() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        long commentId = 1L;
        CommentForm commentForm = mock(CommentForm.class);
        when(commentDAO.getCommentById(anyLong())).thenThrow(new DataStorageException(""));
        commentService.updateComment(commentId, commentForm);
    }

    @Test
    public void testUpdateComment_SHOULD_UPDATE_COMMENT() throws InternalServerErrorException, ObjectNotFoundException, DataStorageException {
        long commentId = 1L;
        CommentForm commentForm = spy(new CommentForm());
        when(commentDAO.getCommentById(anyLong()))
                .thenReturn(Optional.ofNullable(spy(new Comment())));
        doNothing().when(commentDAO).updateComment(anyLong(), any(Comment.class));
        commentService.updateComment(commentId, commentForm);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDeleteComment_DAO_LAYER_THROW_EXCEPTION() throws DataStorageException, InternalServerErrorException {
        long commentId = 1L;
        when(commentDAO.deleteComment(anyLong())).thenThrow(new DataStorageException(""));
        commentService.deleteComment(commentId);
    }

    @Test
    public void testDeleteComment_SHOULD_DELETE_COMMENT() throws DataStorageException, InternalServerErrorException {
        long commentId = 1L;
        when(commentDAO.deleteComment(anyLong())).thenReturn(true);
        commentService.deleteComment(commentId);
    }
}