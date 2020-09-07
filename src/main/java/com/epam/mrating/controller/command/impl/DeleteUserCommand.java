package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.model.domain.AccountDetails;
import com.epam.mrating.util.WebUtil;
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
        AccountDetails currentAccountDetails = WebUtil.getSessionCurrentAccountDetails(request);
        serviceFactory.getUserService().deleteUser(currentAccountDetails.getId());
        ViewUtil.redirect("/app/logout",request,response);
    }

}
