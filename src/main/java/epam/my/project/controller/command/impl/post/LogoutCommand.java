package epam.my.project.controller.command.impl.post;

import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;

public class LogoutCommand extends FrontCommand {
    private static final long serialVersionUID = -9000519776357049601L;

    @Override
    public void execute() throws IOException {
        WebUtil.clearCurrentAccountDetails(request, response);
        redirect("movies.jsp");
    }

}
