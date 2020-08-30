package epam.my.project.controller.command.impl;

import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;


/**
 * The type Delete user command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 public final class DeleteUserCommand extends AbstractCommand {
    private static final long serialVersionUID = -1936968024800051518L;

    @Override
    public void execute() throws IOException, InternalServerErrorException, ServletException {
        AccountDetails currentAccountDetails = WebUtil.getCurrentAccountDetails(request);
        serviceFactory.getUserService().deleteUser(currentAccountDetails.getId());
        ViewUtil.redirect("/app/logout",request,response);
    }

}
