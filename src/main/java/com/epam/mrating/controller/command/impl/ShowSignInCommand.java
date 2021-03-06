package com.epam.mrating.controller.command.impl;

import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.util.WebUtil;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Show sign in command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ShowSignInCommand extends AbstractCommand {
    private static final long serialVersionUID = -17189556053234545L;

    @Override
    public void execute() throws IOException, ServletException {
        if(WebUtil.isSessionCurrentAccountDetailsCreated(request)){
            ViewUtil.redirect("/app/movies",request,response);
        } else {
            ViewUtil.forwardToPage("page/sign-in.jsp",request,response);
        }
    }

}
