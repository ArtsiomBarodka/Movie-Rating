package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class ErrorHandlerCommand extends FrontCommand {
    private static final long serialVersionUID = 6305238247976827375L;

    @Override
    public void execute() throws ServletException, IOException {
        forwardToPage("page/error.jsp");
    }
}
