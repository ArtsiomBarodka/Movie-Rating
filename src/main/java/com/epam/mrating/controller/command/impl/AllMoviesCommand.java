package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.configuration.SortMode;
import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.domain.Page;
import com.epam.mrating.model.entity.Movie;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type All movies command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class AllMoviesCommand extends AbstractCommand {
    private static final long serialVersionUID = -7548633165561956009L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        SortMode sortMode = getSortMode();
        request.setAttribute(RequestAttributeNames.SORT_MODE, sortMode.name().toLowerCase());
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        List<Movie> movies = serviceFactory.getViewMovieService().listAllMovies(sortMode,new Page(pageable));
        request.setAttribute(RequestAttributeNames.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countAllMovies();
        request.setAttribute(RequestAttributeNames.TOTAL_MOVIES_COUNT, totalCount);
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToPage("page/movies.jsp",request,response);
    }

}
