package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ValidationException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.configuration.SecurityConfiguration;
import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.model.domain.AccountDetails;
import com.epam.mrating.model.entity.User;
import com.epam.mrating.model.form.UserForm;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Save edit user command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class SaveEditUserCommand extends AbstractCommand {
    private static final long serialVersionUID = -829502032717158191L;
    private static final int SUBSTRING_INDEX = "/app/user/edit/save/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        try {
            User currentUser = serviceFactory.getUserService().getUserByUId(uid);
            AccountDetails currentAccountDetails = WebUtil.getSessionCurrentAccountDetails(request);
            UserForm userForm = fetchForm(request);
            if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(request.getParameter(RequestParameterNames.USER_NAME)) &&
                    !currentAccountDetails.getRole().equalsIgnoreCase(SecurityConfiguration.ROLE_ADMIN) &&
                    !currentAccountDetails.getName().equalsIgnoreCase(request.getParameter(RequestParameterNames.USER_NAME))){

                WebUtil.setRequestMessage(request, "User with this name already exist!");
                ViewUtil.forwardToServlet("/app/user/edit/" + uid, request,response);
            } else {
                User updatedUser = serviceFactory.getUserService().updateUser(userForm, currentUser.getId());
                currentAccountDetails.setName(updatedUser.getAccount().getName());
                currentAccountDetails.setUid(updatedUser.getUid());
                WebUtil.setSessionCurrentAccountDetails(request, currentAccountDetails);
                ViewUtil.redirect("/app/user/" + updatedUser.getUid(),request,response);
            }
        } catch (ValidationException e) {
            WebUtil.setRequestViolations(request,e.getViolations());
            ViewUtil.forwardToServlet("/app/user/edit/" + uid, request,response);
        }
    }

    private UserForm fetchForm(HttpServletRequest request) {
        String name = request.getParameter(RequestParameterNames.USER_NAME);
        String rating = request.getParameter(RequestParameterNames.USER_RATING);
        String imageLink = request.getParameter(RequestParameterNames.USER_IMAGE_LINK);
        String isBanned = request.getParameter(RequestParameterNames.USER_IS_BANNED);
        UserForm userForm = new UserForm();
        userForm.setName(name);
        userForm.setRating(rating);
        userForm.setBanned(isBanned);
        userForm.setImageLink(imageLink);
        return userForm;
    }

}
