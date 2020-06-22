package epam.my.project.controller.command.impl.post;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.model.entity.AccountAuthToken;
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
                SignUpWithSocialForm form = fetchForm(request);
                if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(request.getParameter("name"))){
                    WebUtil.setMessage(request, "Account with this name already exist!");
                    forwardToPage("page/complete-sign-up.jsp");
                } else {
                    AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().SignUpBySocial(socialAccount, form);
                    serviceFactory.getUserService().createUser(accountDetails.getId(), form.getName());
                    WebUtil.setCurrentAccountDetails(request, accountDetails);
                    redirect("/app/movies");
                }
            } catch (ValidationException e) {
                WebUtil.setViolations(request, e.getViolations());
                forwardToPage("page/complete-sign-up.jsp");
            }
        } else {
            WebUtil.setMessage(request, "Something wrong with social service!");
            forwardToPage("page/sign-in.jsp");
        }
    }

    private SignUpWithSocialForm fetchForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        SignUpWithSocialForm signUpWithSocialForm = new SignUpWithSocialForm();
        signUpWithSocialForm.setName(name);
        return signUpWithSocialForm;
    }
}