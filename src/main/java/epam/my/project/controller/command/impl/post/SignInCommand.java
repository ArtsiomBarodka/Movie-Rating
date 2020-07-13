package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
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
    public void execute() throws IOException, ServletException, InternalServerErrorException, AccessDeniedException {

        try {
            if(!WebUtil.isCurrentAccountDetailsCreated(request)){
                SignInForm signInForm = fetchForm(request);
                AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signInByManually(signInForm);
                WebUtil.setCurrentAccountDetails(request, accountDetails);

                boolean isRememberMe = "on".equals(request.getParameter("rememberMe"));
                if(isRememberMe){
                    AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().createAccountAuthToken(accountDetails);
                    WebUtil.setSelectorCookie(response, accountAuthToken.getSelector());
                    WebUtil.setValidatorCookie(response, accountAuthToken.getValidator());
                }
            }
            viewFactory.getRedirect().init(request,response).render("/app/movies");
        } catch (ValidationException ex){
            WebUtil.setViolations(request,ex.getViolations());
            viewFactory.getForwardToPage().init(request,response).render("page/sign-in.jsp");
        } catch (AccessDeniedException ex){
            WebUtil.setMessage(request, "Account with this parameters is not exist!");
            viewFactory.getForwardToPage().init(request,response).render("page/sign-in.jsp");
        }
    }

    private SignInForm fetchForm(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SignInForm signInForm = new SignInForm();
        signInForm.setEmail(email);
        signInForm.setPassword(password);
        return signInForm;
    }
}

