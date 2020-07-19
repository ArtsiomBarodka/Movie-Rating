package epam.my.project.controller.command.impl.post;

import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.CommentForm;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * The type Add comment command.
 */
public class AddCommentCommand extends FrontCommand {
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
            WebUtil.setViolations(request,ex.getViolations());
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
