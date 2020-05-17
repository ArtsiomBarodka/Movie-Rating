package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.model.form.SignUpForm;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SignUpCommand extends FrontCommand {
    private static final long serialVersionUID = -7958711545875002791L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        String email = request.getParameter(Constants.EMAIL_FORM_PARAMETER);
        String name = request.getParameter(Constants.NAME_FORM_PARAMETER);
        if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountEmail(email)){
            forwardToPage("sign-up.jsp");
        } else if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(name)){
            forwardToPage("sign-up.jsp");
        } else {
            try {
                SignUpForm signUpForm = fetchForm(request);
                AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signUpByManually(signUpForm);
                WebUtil.setCurrentAccountDetails(request, accountDetails);

                boolean isRememberMe = "true".equals(request.getParameter(Constants.IS_REMEMBER_ME_FORM_PARAMETER));
                if(isRememberMe){
                    AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().createAccountAuthToken(accountDetails);
                    WebUtil.setSelectorCookie(response, accountAuthToken.getSelector());
                    WebUtil.setValidatorCookie(response, accountAuthToken.getValidator());
                }

                redirect("movies.jsp");
            } catch (ValidationException e){
                WebUtil.setValidationException(request,e);
                forwardToPage("sign-up.jsp");
            }
        }
    }

    private SignUpForm fetchForm(HttpServletRequest request) {
        String email = request.getParameter(Constants.EMAIL_FORM_PARAMETER);
        String password = request.getParameter(Constants.PASSWORD_FORM_PARAMETER);
        String name = request.getParameter(Constants.NAME_FORM_PARAMETER);
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setName(name);
        signUpForm.setEmail(email);
        signUpForm.setPassword(password);
        return signUpForm;
    }

}
