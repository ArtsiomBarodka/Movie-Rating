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

public class MoreMoviesCommandImpl implements Command, Serializable {
    private static final long serialVersionUID = 3668038885225385899L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory) throws IOException, ServletException {
        int page = Integer.parseInt(request.getParameter(Constants.PAGE));
        SortMode sortMode = SortMode.of(request.getAttribute(Constants.SORT_MODE).toString());
        List<Movie> movies = serviceFactory.getViewMovieService().listAllMovies(sortMode,new Page(page, Constants.MAX_MOVIES_PER_HTML_PAGE));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countAllMovies();
        request.setAttribute(Constants.PAGE_COUNT, totalCount);
        ViewUtil.forwardToFragment("movies-list.jsp", request, response);
    }
}
