package epam.my.project.controller.command.impl.post;

import epam.my.project.controller.command.FrontCommand;
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
        if(!WebUtil.isCurrentAccountDetailsCreated(request)){
            String email = request.getParameter("email");
            String name = request.getParameter("name");

            if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountEmail(email) ||
                    serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(name)){
                WebUtil.setMessage(request, "Account with this name or email already exist!");
                forwardToPage("page/sign-up.jsp");
            } else {
                try {
                    SignUpForm signUpForm = fetchForm(request);
                    AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signUpByManually(signUpForm);
                    serviceFactory.getUserService().createUser(accountDetails.getId(), signUpForm.getName());
                    WebUtil.setCurrentAccountDetails(request, accountDetails);

                    boolean isRememberMe = "on".equals(request.getParameter("rememberMe"));
                    if(isRememberMe){
                        AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().createAccountAuthToken(accountDetails);
                        WebUtil.setSelectorCookie(response, accountAuthToken.getSelector());
                        WebUtil.setValidatorCookie(response, accountAuthToken.getValidator());
                    }

                    redirect("/app/movies");
                } catch (ValidationException e){
                    WebUtil.setViolations(request,e.getViolations());
                    forwardToPage("page/sign-up.jsp");
                }
            }
        } else {
            redirect("/app/movies");
        }
    }

    private SignUpForm fetchForm(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setName(name);
        signUpForm.setEmail(email);
        signUpForm.setPassword(password);
        return signUpForm;
    }

}
