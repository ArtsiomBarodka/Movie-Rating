package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.entity.User;
import epam.my.project.model.form.CommentForm;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class AddCommentCommand extends FrontCommand {
    private static final long serialVersionUID = 3179163005572732642L;
    private static final int SUBSTRING_INDEX = "/app/comment/add/movie/".length();
    @Override
    public void execute() throws IOException, InternalServerErrorException, ServletException, ObjectNotFoundException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        CommentForm commentForm = fetchForm(request);
        try {
            serviceFactory.getCommentService().createComment(commentForm);
            redirect("/app/movie/" + uid);
        } catch (ValidationException ex) {
            WebUtil.setViolations(request,ex.getViolations());
            sendCommentsListWithViolations(commentForm);
        }
    }

    private CommentForm fetchForm(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String movieId = request.getParameter("movieId");
        String rating = request.getParameter("rating");
        String content = request.getParameter("content");
        CommentForm commentForm = new CommentForm();
        commentForm.setMovieId(movieId);
        commentForm.setUserId(userId);
        commentForm.setRating(rating);
        commentForm.setContent(content);
        return commentForm;
    }

    private void sendCommentsListWithViolations(CommentForm commentForm) throws InternalServerErrorException, ServletException, IOException, ObjectNotFoundException {
        Movie movie = serviceFactory.getEditMovieService().getMovieById(commentForm.getMovieId());
        int pageable = getPageable();
        request.setAttribute(Constants.PAGEABLE, pageable);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByMovie(commentForm.getMovieId(), new Page(pageable));
        movie.setComments(comments);
        request.setAttribute(Constants.MOVIE, movie);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByMovie(movie.getId());
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, pageable));
        int currentAccountId = WebUtil.getCurrentAccountDetails(request).getId();
        User user = serviceFactory.getUserService().getUserByAccountId(currentAccountId);
        request.setAttribute(Constants.USER, user);
        boolean isAlreadyExistComment = serviceFactory.getCommentService().commentAlreadyExist(movie.getId(), user.getId());
        request.setAttribute(Constants.ALREADY_EXIST_COMMENT, isAlreadyExistComment);
        forwardToPage("page/movie.jsp");
    }

}
