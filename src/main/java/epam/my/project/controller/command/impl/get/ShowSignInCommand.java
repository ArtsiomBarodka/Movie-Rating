package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Show sign in command.
 */
public class ShowSignInCommand extends FrontCommand {
    private static final long serialVersionUID = -17189556053234545L;

    @Override
    public void execute() throws IOException, ServletException {
        if(WebUtil.isCurrentAccountDetailsCreated(request)){
            ViewUtil.redirect("/app/movies",request,response);
        } else {
            ViewUtil.forwardToPage("page/sign-in.jsp",request,response);
        }
    }

}
