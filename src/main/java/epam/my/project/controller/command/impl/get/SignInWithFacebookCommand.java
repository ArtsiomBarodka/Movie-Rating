package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;

import java.io.IOException;


public class SignInWithFacebookCommand extends FrontCommand {
    private static final long serialVersionUID = -5527614422060543437L;

    @Override
    public void execute() throws IOException {
        redirect(serviceFactory.getSocialService(Constants.FACEBOOK_SOCIAL).getAuthorizeUrl());
    }
}
