package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotFoundCommand extends FrontCommand {
    private static final long serialVersionUID = 3976717553386963417L;

    @Override
    public void execute() throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}