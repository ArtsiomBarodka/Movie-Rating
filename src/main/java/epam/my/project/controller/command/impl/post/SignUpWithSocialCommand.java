package epam.my.project.controller.command.impl.post;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.model.form.SignUpWithSocialForm;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SignUpWithSocialCommand extends FrontCommand {
    private static final long serialVersionUID = 3521508763424145232L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        if(!WebUtil.isCurrentAccountDetailsCreated(request)){
            if(WebUtil.isCurrentSocialAccountCreated(request)){
                try {
                    SocialAccount socialAccount = WebUtil.getCurrentSocialAccount(request);
                    SignUpWithSocialForm form = fetchForm(request);
                    if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(request.getParameter("name"))){
                        WebUtil.setMessage(request, "Account with this name already exist!");
                        ViewUtil.forwardToPage("page/complete-sign-up.jsp",request,response);
                    } else {
                        AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signUpBySocial(socialAccount, form);
                        serviceFactory.getUserService().createUser(accountDetails.getId(), form.getName());
                        WebUtil.setCurrentAccountDetails(request, accountDetails);
                        ViewUtil.redirect("/app/movies",request,response);
                    }
                } catch (ValidationException e) {
                    WebUtil.setViolations(request, e.getViolations());
                    ViewUtil.forwardToPage("page/complete-sign-up.jsp",request,response);
                }
            } else {
                WebUtil.setMessage(request, "Something wrong with social service!");
                ViewUtil.forwardToPage("page/sign-in.jsp",request,response);
            }
        } else {
            ViewUtil.redirect("/app/movies",request,response);
        }

    }

    private SignUpWithSocialForm fetchForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        SignUpWithSocialForm signUpWithSocialForm = new SignUpWithSocialForm();
        signUpWithSocialForm.setName(name);
        return signUpWithSocialForm;
    }
}