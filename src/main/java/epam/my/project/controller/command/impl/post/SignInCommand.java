package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.model.form.SignInForm;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SignInCommand extends FrontCommand {
    private static final long serialVersionUID = 3070927532374653890L;

    @Override
    public void execute() throws IOException, ServletException {

        try {
            SignInForm signInForm = fetchForm(request);
            AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signInByManually(signInForm);
            WebUtil.setCurrentAccountDetails(request, accountDetails);

            boolean isRememberMe = "true".equals(request.getParameter(Constants.IS_REMEMBER_ME_FORM_PARAMETER));
            if(isRememberMe){
                AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().createAccountAuthToken(accountDetails);
                WebUtil.setSelectorCookie(response, accountAuthToken.getSelector());
                WebUtil.setValidatorCookie(response, accountAuthToken.getValidator());
            }
            redirect("movies.jsp");
        } catch (ValidationException ex){
            WebUtil.setValidationException(request,ex);
            forwardToPage("sign-in.jsp");
        } catch (AccessDeniedException e) {
            e.printStackTrace();
        } catch (InternalServerErrorException e) {
            e.printStackTrace();
        }
    }

    private SignInForm fetchForm(HttpServletRequest request) throws ValidationException {
        String email = request.getParameter(Constants.EMAIL_FORM_PARAMETER);
        String password = request.getParameter(Constants.PASSWORD_FORM_PARAMETER);
        SignInForm signInForm = new SignInForm();
        signInForm.setEmail(email);
        signInForm.setPassword(password);
        return signInForm;
    }
}

