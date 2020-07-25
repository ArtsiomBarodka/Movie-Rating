package epam.my.project.controller.command.impl;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type Top list movies command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 final class TopListMoviesCommand extends AbstractCommand {
    private static final long serialVersionUID = -2738688950069204294L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = getSortMode();
        request.setAttribute(RequestAttributeNames.SORT_MODE, sortMode.name().toLowerCase());
        List<Movie> topMovies = serviceFactory.getViewMovieService().listAllMovies(sortMode,new Page(Constants.MAX_MOVIES_PER_TOP));
        request.setAttribute(RequestAttributeNames.MOVIE_TOP_LIST, topMovies);
        ViewUtil.forwardToPage("page/top.jsp",request,response);
    }

}
