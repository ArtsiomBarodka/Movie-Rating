package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.SortMode;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type All movies command.
 */
public class AllMoviesCommand extends FrontCommand {
    private static final long serialVersionUID = -7548633165561956009L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = getSortMode();
        request.setAttribute(RequestAttributeNames.SORT_MODE, sortMode.name().toLowerCase());
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        List<Movie> movies = serviceFactory.getViewMovieService().listAllMovies(sortMode,new Page(pageable));
        request.setAttribute(RequestAttributeNames.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countAllMovies();
        request.setAttribute(RequestAttributeNames.TOTAL_MOVIES_COUNT, totalCount);
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToPage("page/movies.jsp",request,response);
    }

}
