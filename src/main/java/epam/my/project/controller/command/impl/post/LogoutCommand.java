package epam.my.project.controller.command.impl.post;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Logout command.
 */
public class LogoutCommand extends FrontCommand {
    private static final long serialVersionUID = -9000519776357049601L;

    @Override
    public void execute() throws IOException, ServletException {
        WebUtil.clearCurrentAccountDetails(request, response);
        ViewUtil.redirect("/app/movies", request,response);
    }
}
