package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.entity.User;
import epam.my.project.util.WebUtil;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class ShowMovieCommand extends FrontCommand {
    private static final long serialVersionUID = 3137112415525853722L;
    private static final int SUBSTRING_INDEX = "/movie".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        Movie movie = serviceFactory.getEditMovieService().getMovieByUId(uid);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByMovie(movie.getId(), new Page(Constants.MAX_COMMENTS_PER_HTML_PAGE));
        movie.setComments(comments);
        request.setAttribute(Constants.MOVIE, movie);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByMovie(movie.getId());
        request.setAttribute(Constants.TOTAL_COMMENTS_COUNT, totalCount);
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, Constants.MAX_MOVIES_PER_HTML_PAGE));
        int currentAccountId = WebUtil.getCurrentAccountDetails(request).getId();
        User user = serviceFactory.getUserService().getUserByAccountId(currentAccountId);
        request.setAttribute(Constants.USER, user);
        forwardToPage("movie.jsp");
    }
}
