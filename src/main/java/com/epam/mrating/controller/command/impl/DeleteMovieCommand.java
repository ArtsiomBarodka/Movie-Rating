package com.epam.mrating.controller.command.impl;

import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.model.entity.Genre;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type Delete movie command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class DeleteMovieCommand extends AbstractCommand {
    private static final long serialVersionUID = 7534284914787908135L;
    private static final int SUBSTRING_INDEX = "/app/movie/delete/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        if(serviceFactory.getEditMovieService().deleteMovie(uid)){
            List<Genre> genres = serviceFactory.getViewMovieService().listAllGenres();
            request.getServletContext().setAttribute(RequestAttributeNames.GENRES, genres);
            int allMoviesCount = serviceFactory.getViewMovieService().countAllMovies();
            request.getServletContext().setAttribute(RequestAttributeNames.ALL_MOVIES_COUNT, allMoviesCount);
        }
        ViewUtil.redirect("/app/movies",request,response);
    }

}

