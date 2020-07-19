package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
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

public class FromGoogleSignInCommand extends FrontCommand {
    private static final long serialVersionUID = 6173794796188599055L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, RetrieveSocialAccountFailedException, AccessDeniedException {
        Optional<String> code = Optional.ofNullable(request.getParameter("code"));
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
