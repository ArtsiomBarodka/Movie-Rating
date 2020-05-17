package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class MoreMoviesByGenreCommand extends FrontCommand {
    private static final long serialVersionUID = -7974017732584365447L;
    private static final int SUBSTRING_INDEX = "/movies".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = getSortMode();
        request.setAttribute(Constants.SORT_MODE, sortMode.name().toLowerCase());
        String genre = request.getRequestURI().substring(SUBSTRING_INDEX);
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesByGenre(genre, sortMode, new Page(Constants.MAX_MOVIES_PER_HTML_PAGE));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesByGenre(genre);
        request.setAttribute(Constants.PAGE_COUNT, totalCount);
        forwardToFragment("movies-list.jsp");
    }
}
