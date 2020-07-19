package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;


public class SignInWithFacebookCommand extends FrontCommand {
    private static final long serialVersionUID = -5527614422060543437L;

    @Override
    public void execute() throws IOException, ServletException {
        if(WebUtil.isCurrentAccountDetailsCreated(request)){
            ViewUtil.redirect("/app/movies",request,response);
        } else {
            String redirectUrl = serviceFactory.getSocialService(Constants.FACEBOOK_SOCIAL).getAuthorizeUrl();
            ViewUtil.redirect(redirectUrl,request,response);
        }
    }
}
