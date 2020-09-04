package epam.my.project.controller.command.impl;

import epam.my.project.configuration.SortMode;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.SearchMovieForm;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * The type Search movies command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class SearchMoviesCommand extends AbstractCommand {
    private static final long serialVersionUID = 8846796531505775888L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SearchMovieForm searchMovieForm = createSearchMovieForm(request);
        request.setAttribute(RequestAttributeNames.QUERY, buildUrlQuery(request));
        SortMode sortMode = getSortMode();
        request.setAttribute(RequestAttributeNames.SORT_MODE, sortMode.name().toLowerCase());
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesBySearchForm(searchMovieForm, sortMode, new Page(pageable));
        request.setAttribute(RequestAttributeNames.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesBySearchForm(searchMovieForm);
        request.setAttribute(RequestAttributeNames.TOTAL_MOVIES_COUNT, totalCount);
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToPage("page/movies.jsp",request,response);
    }

    private SearchMovieForm createSearchMovieForm(HttpServletRequest request) {
        return new SearchMovieForm(request.getParameter(RequestParameterNames.SEARCH_QUERY),
                request.getParameterValues(RequestParameterNames.SEARCH_CATEGORY),
                request.getParameterValues(RequestParameterNames.SEARCH_GENRE),
                request.getParameterValues(RequestParameterNames.SEARCH_COUNTRY));
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

}
