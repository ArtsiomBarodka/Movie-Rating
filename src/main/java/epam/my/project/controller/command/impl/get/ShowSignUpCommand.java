package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;

public class ShowSignUpCommand extends FrontCommand {
    private static final long serialVersionUID = 2522312836892523547L;

    @Override
    public void execute() throws IOException, ServletException {
        if(WebUtil.isCurrentAccountDetailsCreated(request)){
            redirect("/app/movies");
        } else {
            forwardToPage("page/sign-up.jsp");
        }
    }

}
