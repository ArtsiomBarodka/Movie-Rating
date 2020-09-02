package epam.my.project.controller.command.impl;

import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.service.exception.AccessDeniedException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.model.form.SignInForm;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Sign in command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 public final class SignInCommand extends AbstractCommand {
    private static final long serialVersionUID = 3070927532374653890L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, AccessDeniedException {
        try {
            if(!WebUtil.isSessionCurrentAccountDetailsCreated(request)){
                SignInForm signInForm = fetchForm(request);
                AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signInByManually(signInForm);
                WebUtil.setSessionCurrentAccountDetails(request, accountDetails);

                boolean isRememberMe = "on".equals(request.getParameter(RequestParameterNames.SIGN_IN_REMEMBER_ME));
                if(isRememberMe){
                    AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().createAccountAuthToken(accountDetails);
                    WebUtil.setSelectorCookie(response, accountAuthToken.getSelector());
                    WebUtil.setValidatorCookie(response, accountAuthToken.getValidator());
                }
            }
            if(WebUtil.isSessionRedirectUrlAfterAuthenticateCreated(request)){
                ViewUtil.redirect(WebUtil.getSessionRedirectUrlAfterAuthenticate(request),request,response);
            } else {
                ViewUtil.redirect("/app/movies", request,response);
            }
        } catch (ValidationException ex){
            WebUtil.setRequestViolations(request,ex.getViolations());
            ViewUtil.forwardToPage("page/sign-in.jsp", request,response);
        } catch (AccessDeniedException ex){
            WebUtil.setRequestMessage(request, "Account with this parameters is not exist!");
            ViewUtil.forwardToPage("page/sign-in.jsp",request,response);
        }
    }

    private SignInForm fetchForm(HttpServletRequest request) {
        String email = request.getParameter(RequestParameterNames.SIGN_IN_EMAIL);
        String password = request.getParameter(RequestParameterNames.SIGN_IN_PASSWORD);
        SignInForm signInForm = new SignInForm();
        signInForm.setEmail(email);
        signInForm.setPassword(password);
        return signInForm;
    }
}

