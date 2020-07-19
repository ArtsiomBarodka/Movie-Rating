package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.entity.User;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Show edit user command.
 */
public class ShowEditUserCommand extends FrontCommand {
    private static final long serialVersionUID = 897207187978943000L;
    private static final int SUBSTRING_INDEX = "/app/user/edit/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        AccountDetails currentAccountDetails = WebUtil.getCurrentAccountDetails(request);
        User user = null;
        if(currentAccountDetails.getRole().equalsIgnoreCase(SecurityConfiguration.ROLE_ADMIN)){
            String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
            user = serviceFactory.getUserService().getUserByUId(uid);
        } else {
            user = serviceFactory.getUserService().getUserByAccountId(currentAccountDetails.getId());
        }
        request.setAttribute(RequestAttributeNames.USER, user);
        ViewUtil.forwardToPage("page/edit-user.jsp",request,response);
    }


}
