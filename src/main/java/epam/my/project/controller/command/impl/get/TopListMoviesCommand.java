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

public class TopListMoviesCommand extends FrontCommand {
    private static final long serialVersionUID = -2738688950069204294L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = getSortMode();
        request.setAttribute(Constants.SORT_MODE, sortMode.name().toLowerCase());
        List<Movie> topMovies = serviceFactory.getViewMovieService().listAllMovies(sortMode,new Page(Constants.MAX_MOVIES_PER_TOP));
        request.setAttribute(Constants.MOVIE_TOP_LIST, topMovies);
        forwardToPage("page/top.jsp");
    }

}