package com.epam.mrating.controller.request;

import com.epam.mrating.controller.command.FrontCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Request handler.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@FunctionalInterface
public interface RequestHandler {
    /**
     * Gets command.
     *
     * @param request the request
     * @return the command
     */
    FrontCommand getCommand(HttpServletRequest request);
}
