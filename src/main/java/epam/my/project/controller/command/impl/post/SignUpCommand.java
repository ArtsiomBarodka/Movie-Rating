package epam.my.project.controller.command.impl.post;

import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.model.form.SignUpForm;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Sign up command.
 */
public class SignUpCommand extends FrontCommand {
    private static final long serialVersionUID = -7958711545875002791L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        if(!WebUtil.isCurrentAccountDetailsCreated(request)){
            String email = request.getParameter(RequestParameterNames.SIGN_UP_EMAIL);
            String name = request.getParameter(RequestParameterNames.SIGN_UP_NAME);

            if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountEmail(email) ||
                    serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(name)){

                WebUtil.setMessage(request, "Account with this name or email already exist!");
                ViewUtil.forwardToPage("page/sign-up.jsp",request,response);
            } else {
                try {
                    SignUpForm signUpForm = fetchForm(request);
                    AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signUpByManually(signUpForm);
                    serviceFactory.getUserService().createUser(accountDetails.getId(), signUpForm.getName());
                    WebUtil.setCurrentAccountDetails(request, accountDetails);

                    boolean isRememberMe = "on".equals(request.getParameter(RequestParameterNames.SIGN_UP_REMEMBER_ME));
                    if(isRememberMe){
                        AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().createAccountAuthToken(accountDetails);
                        WebUtil.setSelectorCookie(response, accountAuthToken.getSelector());
                        WebUtil.setValidatorCookie(response, accountAuthToken.getValidator());
                    }
                    ViewUtil.redirect("/app/movies", request,response);
                } catch (ValidationException e){
                    WebUtil.setViolations(request,e.getViolations());
                    ViewUtil.forwardToPage("page/sign-up.jsp",request,response);
                }
            }
        } else {
            ViewUtil.redirect("/app/movies",request,response);
        }
    }

    private SignUpForm fetchForm(HttpServletRequest request) {
        String email = request.getParameter(RequestParameterNames.SIGN_UP_EMAIL);
        String password = request.getParameter(RequestParameterNames.SIGN_UP_PASSWORD);
        String name = request.getParameter(RequestParameterNames.SIGN_UP_NAME);
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setName(name);
        signUpForm.setEmail(email);
        signUpForm.setPassword(password);
        return signUpForm;
    }

}
