package epam.my.project.controller.request;

import epam.my.project.controller.command.FrontCommand;

import javax.servlet.http.HttpServletRequest;

public interface RequestHandler {
    FrontCommand getCommand(HttpServletRequest request);
}
