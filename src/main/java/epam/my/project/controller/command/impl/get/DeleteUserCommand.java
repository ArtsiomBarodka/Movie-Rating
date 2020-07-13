package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.util.WebUtil;

import javax.servlet.ServletException;
import java.io.IOException;


public class DeleteUserCommand extends FrontCommand {
    private static final long serialVersionUID = -1936968024800051518L;

    @Override
    public void execute() throws IOException, InternalServerErrorException, ServletException {
        AccountDetails currentAccountDetails = WebUtil.getCurrentAccountDetails(request);
        serviceFactory.getUserService().deleteUser(currentAccountDetails.getId());
        viewFactory.getRedirect().init(request,response).render("/app/logout");
    }

}
