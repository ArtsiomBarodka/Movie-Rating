package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.User;
import epam.my.project.model.form.UserForm;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SaveEditUserCommand extends FrontCommand {
    private static final long serialVersionUID = -829502032717158191L;
    private static final int SUBSTRING_INDEX = "/app/user/edit/save/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        try {
            User currentUser = serviceFactory.getUserService().getUserByUId(uid);
            AccountDetails currentAccountDetails = WebUtil.getCurrentAccountDetails(request);
            UserForm userForm = fetchForm(request);
            if(serviceFactory.getAuthenticateAndAuthorizationService().alreadyExistAccountName(request.getParameter("name")) &&
                    !currentAccountDetails.getRole().equalsIgnoreCase(SecurityConfiguration.ROLE_ADMIN) &&
                    !currentAccountDetails.getName().equals(request.getParameter("name"))){
                WebUtil.setMessage(request, "User with this name already exist!");
                viewFactory.getForwardToCommand().init(request,response).render("/app/user/edit/" + uid);
            } else {
                User updatedUser = serviceFactory.getUserService().updateUser(userForm, currentUser.getId());
                currentAccountDetails.setName(updatedUser.getAccount().getName());
                currentAccountDetails.setUid(updatedUser.getUid());
                WebUtil.setCurrentAccountDetails(request, currentAccountDetails);
                viewFactory.getRedirect().init(request,response).render("/app/user/" + updatedUser.getUid());
            }
        } catch (ValidationException e) {
            WebUtil.setViolations(request,e.getViolations());
            viewFactory.getForwardToCommand().init(request,response).render("/app/user/edit/" + uid);
        }
    }

    private UserForm fetchForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        String rating = request.getParameter("rating");
        String imageLink = request.getParameter("imageLink");
        String isBanned = request.getParameter("isBanned");
        UserForm userForm = new UserForm();
        userForm.setName(name);
        userForm.setRating(rating);
        userForm.setBanned(isBanned);
        userForm.setImageLink(imageLink);
        return userForm;
    }

}
