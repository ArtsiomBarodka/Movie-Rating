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

public class MoreMoviesByGenreCommand extends FrontCommand {
    private static final long serialVersionUID = -7974017732584365447L;
    private static final int SUBSTRING_INDEX = "/app/ajax/html/movies/genres/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(Constants.PAGE));
        int pageable = getPageable();
        request.setAttribute(Constants.PAGEABLE, pageable);
        SortMode sortMode = getSortMode();
        request.setAttribute(Constants.SORT_MODE, sortMode.name().toLowerCase());
        String genre = request.getRequestURI().substring(SUBSTRING_INDEX);
        request.setAttribute(Constants.GENRE, genre);
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesByGenre(genre, sortMode, new Page(page, pageable));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesByGenre(genre);
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, pageable));
        forwardToFragment("movies-list.jsp");
    }
}
