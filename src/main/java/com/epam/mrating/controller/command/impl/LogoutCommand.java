package com.epam.mrating.controller.command.impl;


import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.util.WebUtil;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Logout command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class LogoutCommand extends AbstractCommand {
    private static final long serialVersionUID = -9000519776357049601L;

    @Override
    public void execute() throws IOException, ServletException {
        WebUtil.clearCurrentAccountDetails(request, response);
        ViewUtil.redirect("/app/movies", request,response);
    }
}
