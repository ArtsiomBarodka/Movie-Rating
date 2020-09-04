package epam.my.project.controller.command.impl;

import epam.my.project.configuration.Constants;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;


/**
 * The type Sign in with facebook command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class SignInWithFacebookCommand extends AbstractCommand {
    private static final long serialVersionUID = -5527614422060543437L;

    @Override
    public void execute() throws IOException, ServletException {
        if(WebUtil.isSessionCurrentAccountDetailsCreated(request)){
            ViewUtil.redirect("/app/movies",request,response);
        } else {
            String redirectUrl = serviceFactory.getSocialService(Constants.FACEBOOK_SOCIAL).getAuthorizeUrl();
            ViewUtil.redirect(redirectUrl,request,response);
        }
    }
}
