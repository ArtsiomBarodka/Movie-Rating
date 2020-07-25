package epam.my.project.controller.command.impl;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.User;
import epam.my.project.model.form.UserForm;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Save edit user command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 final class SaveEditUserCommand extends AbstractCommand {
    private static final long serialVersionUID = -829502032717158191L;
    private static final int SUBSTRING_INDEX = "/app/user/edit/save/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        try {
            User currentUser = serviceFactory.getUserService().getUserByUId(uid);
            AccountDetails currentAccountDetails = WebUtil.getCurrentAccountDetails(request);
            UserForm userForm = fetchForm(request);
            if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(request.getParameter(RequestParameterNames.USER_NAME)) &&
                    !currentAccountDetails.getRole().equalsIgnoreCase(SecurityConfiguration.ROLE_ADMIN) &&
                    !currentAccountDetails.getName().equalsIgnoreCase(request.getParameter(RequestParameterNames.USER_NAME))){

                WebUtil.setMessage(request, "User with this name already exist!");
                ViewUtil.forwardToServlet("/app/user/edit/" + uid, request,response);
            } else {
                User updatedUser = serviceFactory.getUserService().updateUser(userForm, currentUser.getId());
                currentAccountDetails.setName(updatedUser.getAccount().getName());
                currentAccountDetails.setUid(updatedUser.getUid());
                WebUtil.setCurrentAccountDetails(request, currentAccountDetails);
                ViewUtil.redirect("/app/user/" + updatedUser.getUid(),request,response);
            }
        } catch (ValidationException e) {
            WebUtil.setViolations(request,e.getViolations());
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
