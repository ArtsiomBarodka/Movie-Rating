package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.Command;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.Movie;
import epam.my.project.service.factory.ServiceFactory;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class MoreShowMovieCommandImpl implements Command, Serializable {
    private static final long serialVersionUID = -3249206833276562574L;
    private static final int SUBSTRING_INDEX = "/movie".length();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory) throws IOException, ServletException {
        int page = Integer.parseInt(request.getParameter(Constants.PAGE));
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        Movie movie = serviceFactory.getEditMovieService().getMovieByUId(uid);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByMovie(movie.getId(), new Page(page, Constants.MAX_COMMENTS_PER_HTML_PAGE));
        movie.setComments(comments);
        request.setAttribute(Constants.MOVIE, movie);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByMovie(movie.getId());
        request.setAttribute(Constants.PAGE_COUNT, totalCount);
        ViewUtil.forwardToFragment("comments-list.jsp", request, response);
    }
}