package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.SortMode;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.SearchMovieForm;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * The type More search movies command.
 */
public class MoreSearchMoviesCommand extends FrontCommand {
    private static final long serialVersionUID = 6478931621735269687L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(RequestParameterNames.PAGE));
        SearchMovieForm searchMovieForm = createSearchMovieForm(request);
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        request.setAttribute(RequestAttributeNames.QUERY, buildUrlQuery(request));
        SortMode sortMode = getSortMode();
        request.setAttribute(RequestAttributeNames.SORT_MODE, sortMode.name().toLowerCase());
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesBySearchForm(searchMovieForm, sortMode, new Page(page, pageable));
        request.setAttribute(RequestAttributeNames.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesBySearchForm(searchMovieForm);
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToFragment("movies-list.jsp",request,response);
    }

    private String buildUrlQuery(HttpServletRequest request){
        String query = request.getQueryString();
        StringBuilder urlQuery = new StringBuilder();
        String[] params = query.split("&");
        for (String p : params) {
            if(p.contains(RequestParameterNames.PAGE)
                    || p.contains(RequestParameterNames.SORT)
                    || p.contains(RequestParameterNames.PAGEABLE)){
                continue;
            }
            urlQuery.append(p).append("&");
        }
        return urlQuery.toString();
    }

    private SearchMovieForm createSearchMovieForm(HttpServletRequest request) {
        return new SearchMovieForm(request.getParameter(RequestParameterNames.SEARCH_QUERY),
                request.getParameterValues(RequestParameterNames.SEARCH_CATEGORY),
                request.getParameterValues(RequestParameterNames.SEARCH_GENRE),
                request.getParameterValues(RequestParameterNames.SEARCH_COUNTRY));
    }
}
