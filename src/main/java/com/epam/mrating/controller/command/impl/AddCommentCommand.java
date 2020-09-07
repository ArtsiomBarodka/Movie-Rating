package com.epam.mrating.controller.command.impl;


import com.epam.mrating.service.exception.ValidationException;
import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.model.entity.Movie;
import com.epam.mrating.model.form.CommentForm;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * The type Add comment command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class AddCommentCommand extends AbstractCommand {
    private static final long serialVersionUID = 3179163005572732642L;
    private static final int SUBSTRING_INDEX = "/app/comment/add/movie/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ServletException, ObjectNotFoundException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        Movie movie = serviceFactory.getEditMovieService().getMovieByUId(uid);
        CommentForm commentForm = fetchForm(request);
        try {
            serviceFactory.getCommentService().createComment(commentForm);
            ViewUtil.redirect("/app/movie/" + movie.getUid(), request,response);
        } catch (ValidationException ex) {
            WebUtil.setRequestViolations(request,ex.getViolations());
            ViewUtil.forwardToServlet("/app/movie/" + movie.getUid(), request,response);
        }
    }

    private CommentForm fetchForm(HttpServletRequest request) {
        String userId = request.getParameter(RequestParameterNames.COMMENT_USER_ID);
        String movieId = request.getParameter(RequestParameterNames.COMMENT_MOVIE_ID);
        String rating = request.getParameter(RequestParameterNames.COMMENT_RATING);
        String content = request.getParameter(RequestParameterNames.COMMENT_CONTENT);
        CommentForm commentForm = new CommentForm();
        commentForm.setMovieId(movieId);
        commentForm.setUserId(userId);
        commentForm.setRating(rating);
        commentForm.setContent(content);
        return commentForm;
    }
}
