package epam.my.project.controller.command.impl;

import epam.my.project.configuration.SortMode;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type More movies by genre command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 final class MoreMoviesByGenreCommand extends AbstractCommand {
    private static final long serialVersionUID = -7974017732584365447L;

    private static final int SUBSTRING_INDEX = "/app/ajax/html/movies/genres/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(RequestParameterNames.PAGE));
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        SortMode sortMode = getSortMode();
        request.setAttribute(RequestAttributeNames.SORT_MODE, sortMode.name().toLowerCase());
        String genre = request.getRequestURI().substring(SUBSTRING_INDEX);
        request.setAttribute(RequestAttributeNames.GENRE, genre);
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesByGenre(genre, sortMode, new Page(page, pageable));
        request.setAttribute(RequestAttributeNames.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesByGenre(genre);
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToFragment("movies-list.jsp",request,response);
    }
}
