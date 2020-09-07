package com.epam.mrating.controller.command.impl;

import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.model.domain.Page;
import com.epam.mrating.model.entity.Movie;
import com.epam.mrating.model.form.SearchMovieForm;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.configuration.SortMode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * The type More search movies command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class MoreSearchMoviesCommand extends AbstractCommand {
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
