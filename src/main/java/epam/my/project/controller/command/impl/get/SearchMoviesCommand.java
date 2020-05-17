package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.SearchMovieForm;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class SearchMoviesCommand extends FrontCommand {
    private static final long serialVersionUID = 8846796531505775888L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SearchMovieForm searchMovieForm = createSearchMovieForm(request);
        SortMode sortMode = getSortMode();
        request.setAttribute(Constants.SORT_MODE, sortMode.name().toLowerCase());
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesBySearchForm(searchMovieForm, sortMode, new Page(Constants.MAX_MOVIES_PER_HTML_PAGE));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesBySearchForm(searchMovieForm);
        request.setAttribute(Constants.PAGE_COUNT, totalCount);
        forwardToPage("search-result.jsp");
    }

    private SearchMovieForm createSearchMovieForm(HttpServletRequest request) {
        return new SearchMovieForm(request.getParameter(Constants.SEARCH_QUERY),
                request.getParameterValues(Constants.SEARCH_CATEGORY),
                request.getParameterValues(Constants.SEARCH_GENRE),
                request.getParameterValues(Constants.SEARCH_COUNTRY));
    }

}
