package epam.my.project.controller.command.impl;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.RetrieveSocialAccountFailedException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

/**
 * The type From google sign in command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 final class FromGoogleSignInCommand extends AbstractCommand {
    private static final long serialVersionUID = 6173794796188599055L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, RetrieveSocialAccountFailedException, AccessDeniedException {
        Optional<String> code = Optional.ofNullable(request.getParameter(RequestParameterNames.SOCIAL_CODE));
        if (code.isPresent()) {
            SocialAccount socialAccount = serviceFactory.getSocialService(Constants.GOOGLE_SOCIAL).getSocialAccount(code.get());
            if (serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountEmail(socialAccount.getEmail())) {
                AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signInBySocial(socialAccount);
                WebUtil.setCurrentAccountDetails(request, accountDetails);
                ViewUtil.redirect("/app/movies",request,response);
            } else {
                WebUtil.setCurrentSocialAccount(request, socialAccount);
                ViewUtil.forwardToPage("page/complete-sign-up.jsp",request,response);
            }
        } else {
            ViewUtil.redirect("/app/show/sign-in",request,response);
        }
    }

}
