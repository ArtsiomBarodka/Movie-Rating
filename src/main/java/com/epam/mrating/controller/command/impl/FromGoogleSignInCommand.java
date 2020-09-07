package com.epam.mrating.controller.command.impl;

import com.epam.mrating.configuration.Constants;
import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.service.exception.AccessDeniedException;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.RetrieveSocialAccountFailedException;
import com.epam.mrating.model.domain.AccountDetails;
import com.epam.mrating.model.domain.SocialAccount;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

/**
 * The type From google sign in command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class FromGoogleSignInCommand extends AbstractCommand {
    private static final long serialVersionUID = 6173794796188599055L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, RetrieveSocialAccountFailedException, AccessDeniedException {
        Optional<String> code = Optional.ofNullable(request.getParameter(RequestParameterNames.SOCIAL_CODE));
        if (code.isPresent()) {
            SocialAccount socialAccount = serviceFactory.getSocialService(Constants.GOOGLE_SOCIAL).getSocialAccount(code.get());
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
