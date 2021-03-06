package com.epam.mrating.controller.command.impl;

import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.service.exception.ValidationException;
import com.epam.mrating.model.domain.AccountDetails;
import com.epam.mrating.model.domain.SocialAccount;
import com.epam.mrating.model.form.SignUpWithSocialForm;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Sign up with social command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class SignUpWithSocialCommand extends AbstractCommand {
    private static final long serialVersionUID = 3521508763424145232L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        if(!WebUtil.isSessionCurrentAccountDetailsCreated(request)){
            if(WebUtil.isSessionCurrentSocialAccountCreated(request)){
                try {
                    SocialAccount socialAccount = WebUtil.getSessionCurrentSocialAccount(request);
                    SignUpWithSocialForm form = fetchForm(request);
                    if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(request.getParameter(RequestParameterNames.SIGN_UP_SOCIAL_NAME))){
                        WebUtil.setRequestMessage(request, "Account with this name already exist!");
                        ViewUtil.forwardToPage("page/complete-sign-up.jsp",request,response);
                    } else {
                        AccountDetails accountDetails = serviceFactory.getAuthenticateAndAuthorizationService().signUpBySocial(socialAccount, form);
                        serviceFactory.getUserService().createUser(accountDetails.getId(), form.getName());
                        WebUtil.setSessionCurrentAccountDetails(request, accountDetails);
                        if(WebUtil.isSessionRedirectUrlAfterAuthenticateCreated(request)){
                            ViewUtil.redirect(WebUtil.getSessionRedirectUrlAfterAuthenticate(request),request,response);
                        } else {
                            ViewUtil.redirect("/app/movies", request,response);
                        }
                    }
                } catch (ValidationException e) {
                    WebUtil.setRequestViolations(request, e.getViolations());
                    ViewUtil.forwardToPage("page/complete-sign-up.jsp",request,response);
                }
            } else {
                WebUtil.setRequestMessage(request, "Something wrong with social service!");
                ViewUtil.forwardToPage("page/sign-in.jsp",request,response);
            }
        } else {
            ViewUtil.redirect("/app/movies",request,response);
        }

    }

    private SignUpWithSocialForm fetchForm(HttpServletRequest request) {
        String name = request.getParameter(RequestParameterNames.SIGN_UP_SOCIAL_NAME);
        SignUpWithSocialForm signUpWithSocialForm = new SignUpWithSocialForm();
        signUpWithSocialForm.setName(name);
        return signUpWithSocialForm;
    }
}