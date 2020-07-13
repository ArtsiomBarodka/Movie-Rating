package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class MoreMoviesCommand extends FrontCommand {
    private static final long serialVersionUID = 3668038885225385899L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(Constants.PAGE));
        int pageable = getPageable();
        request.setAttribute(Constants.PAGEABLE, pageable);
        SortMode sortMode = getSortMode();
        request.setAttribute(Constants.SORT_MODE, sortMode.name().toLowerCase());
        List<Movie> movies = serviceFactory.getViewMovieService().listAllMovies(sortMode,new Page(page, pageable));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countAllMovies();
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, pageable));
        viewFactory.getForwardToFragment().init(request,response).render("movies-list.jsp");
    }
}
