package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.util.WebUtil;

import java.io.IOException;


public class SignInWithFacebookCommand extends FrontCommand {
    private static final long serialVersionUID = -5527614422060543437L;

    @Override
    public void execute() throws IOException {
        if(WebUtil.isCurrentAccountDetailsCreated(request)){
            redirect("/app/movies");
        } else {
            redirect(serviceFactory.getSocialService(Constants.FACEBOOK_SOCIAL).getAuthorizeUrl());
        }
    }
}
