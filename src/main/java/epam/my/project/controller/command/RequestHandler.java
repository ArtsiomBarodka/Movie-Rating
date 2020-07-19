package epam.my.project.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface RequestHandler {
    FrontCommand getCommand(HttpServletRequest request);
}
