package com.epam.mrating.controller.command.impl;

import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Show create movie command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ShowCreateMovieCommand extends AbstractCommand {
    private static final long serialVersionUID = -2455271623581103434L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        ViewUtil.forwardToPage("page/create-movie.jsp",request,response);
    }
}
