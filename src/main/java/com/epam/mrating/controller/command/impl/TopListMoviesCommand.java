package com.epam.mrating.controller.command.impl;

import com.epam.mrating.configuration.Constants;
import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.model.domain.Page;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.configuration.SortMode;
import com.epam.mrating.model.entity.Movie;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type Top list movies command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class TopListMoviesCommand extends AbstractCommand {
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
