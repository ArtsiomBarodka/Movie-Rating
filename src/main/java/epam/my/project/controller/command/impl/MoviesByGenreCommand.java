package epam.my.project.controller.command.impl;

import epam.my.project.configuration.SortMode;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type Movies by genre command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 public final class MoviesByGenreCommand extends AbstractCommand {
    private static final long serialVersionUID = -8475381801154244024L;
    private static final int SUBSTRING_INDEX = "/app/movies/genres/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = getSortMode();
        request.setAttribute(RequestAttributeNames.SORT_MODE, sortMode.name().toLowerCase());
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        String genre = request.getRequestURI().substring(SUBSTRING_INDEX);
        request.setAttribute(RequestAttributeNames.GENRE, genre);
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesByGenre(genre, sortMode, new Page(pageable));
        request.setAttribute(RequestAttributeNames.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesByGenre(genre);
        request.setAttribute(RequestAttributeNames.TOTAL_MOVIES_COUNT, totalCount);
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToPage("page/movies.jsp",request,response);
    }


}
