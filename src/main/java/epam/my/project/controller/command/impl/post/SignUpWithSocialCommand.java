package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.model.form.SignUpWithSocialForm;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SignUpWithSocialCommand extends FrontCommand {
    private static final long serialVersionUID = 3521508763424145232L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException {
        if(WebUtil.isCurrentSocialAccountCreated(request)){
            try {
                SocialAccount socialAccount = WebUtil.getCurrentSocialAccount(request);
                AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().SignUpBySocial(socialAccount, fetchForm(request));
                WebUtil.setCurrentAccountDetails(request, accountDetails);
                forwardToPage("movies.jsp");
            } catch (ValidationException e) {
                WebUtil.setValidationException(request,e);
                forwardToPage("complete-sign-up.jsp");
            }
        } else {
            redirect("sign-in");
        }
    }

    private SignUpWithSocialForm fetchForm(HttpServletRequest request) {
        String name = request.getAttribute(Constants.NAME_FORM_PARAMETER).toString();
        SignUpWithSocialForm signUpWithSocialForm = new SignUpWithSocialForm();
        signUpWithSocialForm.setName(name);
        return signUpWithSocialForm;
    }
}