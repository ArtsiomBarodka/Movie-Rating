package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.entity.Movie;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Show edit movie command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ShowEditMovieCommand extends AbstractCommand {
    private static final long serialVersionUID = 6482141577718818609L;
    private static final int SUBSTRING_INDEX = "/app/movie/edit/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        Movie movie = serviceFactory.getEditMovieService().getMovieByUId(uid);
        request.setAttribute(RequestAttributeNames.MOVIE, movie);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByMovie(movie.getId());
        request.setAttribute(RequestAttributeNames.TOTAL_COMMENTS_COUNT, totalCount);
        ViewUtil.forwardToPage("page/edit-movie.jsp",request,response);
    }
}
