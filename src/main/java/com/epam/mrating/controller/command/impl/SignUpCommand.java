package com.epam.mrating.controller.command.impl;

import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.model.form.SignUpForm;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.service.exception.ValidationException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.model.domain.AccountDetails;
import com.epam.mrating.model.entity.AccountAuthToken;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Sign up command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class SignUpCommand extends AbstractCommand {
    private static final long serialVersionUID = -7958711545875002791L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        if(!WebUtil.isSessionCurrentAccountDetailsCreated(request)){
            String email = request.getParameter(RequestParameterNames.SIGN_UP_EMAIL);
            String name = request.getParameter(RequestParameterNames.SIGN_UP_NAME);

            if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountEmail(email) ||
                    serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(name)){

                WebUtil.setRequestMessage(request, "Account with this name or email already exist!");
                ViewUtil.forwardToPage("page/sign-up.jsp",request,response);
            } else {
                try {
                    SignUpForm signUpForm = fetchForm(request);
                    AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signUpByManually(signUpForm);
                    serviceFactory.getUserService().createUser(accountDetails.getId(), signUpForm.getName());
                    WebUtil.setSessionCurrentAccountDetails(request, accountDetails);

                    boolean isRememberMe = "on".equals(request.getParameter(RequestParameterNames.SIGN_UP_REMEMBER_ME));
                    if(isRememberMe){
                        AccountAuthToken accountAuthToken = serviceFactory.getAuthenticateAndAuthorizationService().createAccountAuthToken(accountDetails);
                        WebUtil.setSelectorCookie(response, accountAuthToken.getSelector());
                        WebUtil.setValidatorCookie(response, accountAuthToken.getValidator());
                    }
                    if(WebUtil.isSessionRedirectUrlAfterAuthenticateCreated(request)){
                        ViewUtil.redirect(WebUtil.getSessionRedirectUrlAfterAuthenticate(request),request,response);
                    } else {
                        ViewUtil.redirect("/app/movies", request,response);
                    }
                } catch (ValidationException e){
                    WebUtil.setRequestViolations(request,e.getViolations());
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
