package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.util.ViewUtil;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class MoviesByGenreCommand extends FrontCommand {
    private static final long serialVersionUID = -8475381801154244024L;
    private static final int SUBSTRING_INDEX = "/app/movies/genres/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = getSortMode();
        request.setAttribute(Constants.SORT_MODE, sortMode.name().toLowerCase());
        int pageable = getPageable();
        request.setAttribute(Constants.PAGEABLE, pageable);
        String genre = request.getRequestURI().substring(SUBSTRING_INDEX);
        request.setAttribute(Constants.GENRE, genre);
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesByGenre(genre, sortMode, new Page(pageable));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesByGenre(genre);
        request.setAttribute(Constants.TOTAL_MOVIES_COUNT, totalCount);
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToPage("page/movies.jsp",request,response);
    }


}
