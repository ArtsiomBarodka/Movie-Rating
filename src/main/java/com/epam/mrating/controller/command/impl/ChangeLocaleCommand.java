package com.epam.mrating.controller.command.impl;

import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

/**
 * The type Change locale command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ChangeLocaleCommand extends AbstractCommand {
    private static final long serialVersionUID = -3809362932565779661L;

    @Override
    public void execute() throws IOException, ServletException {
        Optional<String> locale = Optional.ofNullable(request.getParameter(RequestParameterNames.LOCALE));
        Optional<String> returnUrl = Optional.ofNullable(request.getParameter(RequestParameterNames.RETURN_URL));
        if (locale.isPresent() && returnUrl.isPresent()){
            WebUtil.setSessionLocale(request, locale.get());
            ViewUtil.redirect(returnUrl.get(),request,response);
        } else {
            ViewUtil.redirect("/app/movies",request,response);
        }
    }
}
