package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.configuration.SecurityConfiguration;
import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.domain.AccountDetails;
import com.epam.mrating.model.entity.User;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Show edit user command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ShowEditUserCommand extends AbstractCommand {
    private static final long serialVersionUID = 897207187978943000L;
    private static final int SUBSTRING_INDEX = "/app/user/edit/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        AccountDetails currentAccountDetails = WebUtil.getSessionCurrentAccountDetails(request);
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
