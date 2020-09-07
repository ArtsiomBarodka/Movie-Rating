package com.epam.mrating.controller.command.impl;

import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Show sign up command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ShowSignUpCommand extends AbstractCommand {
    private static final long serialVersionUID = 2522312836892523547L;

    @Override
    public void execute() throws IOException, ServletException {
        if(WebUtil.isSessionCurrentAccountDetailsCreated(request)){
            ViewUtil.redirect("/app/movies",request,response);
        } else {
            ViewUtil.forwardToPage("page/sign-up.jsp",request,response);
        }
    }

}
