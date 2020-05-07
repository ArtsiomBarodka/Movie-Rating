package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.controller.command.Command;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.service.factory.ServiceFactory;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class MoreMoviesByGenreCommandImpl implements Command, Serializable {
    private static final long serialVersionUID = -7974017732584365447L;
    private static final int SUBSTRING_INDEX = "/movies".length();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory) throws IOException, ServletException {
        SortMode sortMode = SortMode.of(request.getAttribute(Constants.SORT_MODE).toString());
        String genre = request.getRequestURI().substring(SUBSTRING_INDEX);
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesByGenre(genre, sortMode, new Page(Constants.MAX_MOVIES_PER_HTML_PAGE));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesByGenre(genre);
        request.setAttribute(Constants.PAGE_COUNT, totalCount);
        ViewUtil.forwardToFragment("movies-list.jsp", request, response);
    }
}
