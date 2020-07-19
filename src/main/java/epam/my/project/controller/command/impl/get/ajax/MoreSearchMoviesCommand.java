package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
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

public class MoreSearchMoviesCommand extends FrontCommand {
    private static final long serialVersionUID = 6478931621735269687L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(Constants.PAGE));
        SearchMovieForm searchMovieForm = createSearchMovieForm(request);
        int pageable = getPageable();
        request.setAttribute(Constants.PAGEABLE, pageable);
        request.setAttribute(Constants.QUERY, buildUrlQuery(request));
        SortMode sortMode = getSortMode();
        request.setAttribute(Constants.SORT_MODE, sortMode.name().toLowerCase());
        List<Movie> movies = serviceFactory.getViewMovieService().listMoviesBySearchForm(searchMovieForm, sortMode, new Page(page, pageable));
        request.setAttribute(Constants.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countMoviesBySearchForm(searchMovieForm);
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToFragment("movies-list.jsp",request,response);
    }

    private String buildUrlQuery(HttpServletRequest request){
        String query = request.getQueryString();
        StringBuilder urlQuery = new StringBuilder();
        String[] params = query.split("&");
        for (String p : params) {
            if(p.contains("page") || p.contains("sort") || p.contains("pageable")){
                continue;
            }
            urlQuery.append(p).append("&");
        }
        return urlQuery.toString();
    }

    private SearchMovieForm createSearchMovieForm(HttpServletRequest request) {
        return new SearchMovieForm(request.getParameter(Constants.SEARCH_QUERY),
                request.getParameterValues(Constants.SEARCH_CATEGORY),
                request.getParameterValues(Constants.SEARCH_GENRE),
                request.getParameterValues(Constants.SEARCH_COUNTRY));
    }
}
