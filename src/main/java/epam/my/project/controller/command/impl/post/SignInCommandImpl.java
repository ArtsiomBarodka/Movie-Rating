package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.Command;
import epam.my.project.exception.AccessDeniedException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.AccountAuthToken;
import epam.my.project.model.form.SignInForm;
import epam.my.project.service.factory.ServiceFactory;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public class SignInCommandImpl implements Command, Serializable {
    private static final long serialVersionUID = 3070927532374653890L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory) throws IOException, ValidationException, AccessDeniedException {
        String email = request.getParameter(Constants.EMAIL_FORM_PARAMETER);
        String password = request.getParameter(Constants.PASSWORD_FORM_PARAMETER);
        boolean isRememberMe = "true".equals(request.getParameter(Constants.IS_REMEMBER_ME_FORM_PARAMETER));

        SignInForm signInForm = new SignInForm(email, password);
        AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signInByManually(signInForm);
        WebUtil.setCurrentAccountDetails(request, accountDetails);

        if(isRememberMe){
            AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().createAccountAuthToken(accountDetails);
            WebUtil.setSelectorCookie(response, accountAuthToken.getSelector());
            WebUtil.setValidatorCookie(response, accountAuthToken.getValidator());
        }
        ViewUtil.redirect("movies.jsp", response);
    }
}