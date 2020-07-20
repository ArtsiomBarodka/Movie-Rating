package epam.my.project.controller.request;

import epam.my.project.controller.command.FrontCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Request handler.
 */
public interface RequestHandler {
    /**
     * Gets command.
     *
     * @param request the request
     * @return the command
     */
    FrontCommand getCommand(HttpServletRequest request);
}
