package epam.my.project.controller.command.impl.get;

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

public class AllMoviesCommand extends FrontCommand {
    private static final long serialVersionUID = -7548633165561956009L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = getSortMode();
        request.setAttribute(Constants.SORT_MODE, sortMode.name().toLowerCase());
        int pageable = getPageable();
        request.setAttribute(Constants.PAGEABLE, pageable);
        List<Movie> movies = serviceFactory.getViewMovieService().listAllMovies(sortMode,new Page(pageable));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countAllMovies();
        request.setAttribute(Constants.TOTAL_MOVIES_COUNT, totalCount);
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, pageable));
        forwardToPage("page/movies.jsp");
    }

}
