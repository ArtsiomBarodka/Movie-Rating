package com.epam.mrating.controller.command.impl;

import com.epam.mrating.controller.request.RequestParameterNames;
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
 * The type More movies command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class MoreMoviesCommand extends AbstractCommand {
    private static final long serialVersionUID = 3668038885225385899L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(RequestParameterNames.PAGE));
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        SortMode sortMode = getSortMode();
        request.setAttribute(RequestAttributeNames.SORT_MODE, sortMode.name().toLowerCase());
        List<Movie> movies = serviceFactory.getViewMovieService().listAllMovies(sortMode,new Page(page, pageable));
        request.setAttribute(RequestAttributeNames.MOVIES, movies);
        int totalCount = serviceFactory.getViewMovieService().countAllMovies();
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToFragment("movies-list.jsp",request,response);
    }
}
