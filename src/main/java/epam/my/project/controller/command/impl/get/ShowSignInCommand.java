package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;

public class ShowSignInCommand extends FrontCommand {
    private static final long serialVersionUID = -17189556053234545L;

    @Override
    public void execute() throws IOException, ServletException {
        if(WebUtil.isCurrentAccountDetailsCreated(request)){
            viewFactory.getRedirect().init(request,response).render("/app/movies");
        } else {
            viewFactory.getForwardToPage().init(request,response).render("page/sign-in.jsp");
        }
    }

}
