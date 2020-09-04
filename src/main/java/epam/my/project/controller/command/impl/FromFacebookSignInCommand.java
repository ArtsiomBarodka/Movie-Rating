package epam.my.project.controller.command.impl;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.service.exception.AccessDeniedException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.RetrieveSocialAccountFailedException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

/**
 * The type From facebook sign in command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class FromFacebookSignInCommand extends AbstractCommand {
    private static final long serialVersionUID = -4368225099798427833L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, RetrieveSocialAccountFailedException, AccessDeniedException {
        Optional<String> code = Optional.ofNullable(request.getParameter(RequestParameterNames.SOCIAL_CODE));
        if (code.isPresent()) {
            SocialAccount socialAccount = serviceFactory.getSocialService(Constants.FACEBOOK_SOCIAL).getSocialAccount(code.get());
            if (serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountEmail(socialAccount.getEmail())) {
                AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signInBySocial(socialAccount);
                WebUtil.setSessionCurrentAccountDetails(request, accountDetails);
                if(WebUtil.isSessionRedirectUrlAfterAuthenticateCreated(request)){
                    ViewUtil.redirect(WebUtil.getSessionRedirectUrlAfterAuthenticate(request),request,response);
                } else {
                    ViewUtil.redirect("/app/movies", request,response);
                }
            } else {
               WebUtil.setSessionCurrentSocialAccount(request, socialAccount);
               ViewUtil.forwardToPage("page/complete-sign-up.jsp",request,response);
            }
        } else {
            ViewUtil.redirect("/app/show/sign-in",request,response);
        }
    }

}
