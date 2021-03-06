package com.epam.mrating.controller.command.impl;

import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

/**
 * The type Delete comment command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class DeleteCommentCommand extends AbstractCommand {
    private static final long serialVersionUID = 8213066987208506460L;

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        Optional<String> returnUrl = Optional.ofNullable(request.getParameter(RequestParameterNames.RETURN_URL));
        Optional<String> id = Optional.ofNullable(request.getParameter(RequestParameterNames.COMMENT_ID));
        if(returnUrl.isPresent() && id.isPresent() && isNumber(id.get())){
            serviceFactory.getCommentService().deleteComment(Long.parseLong(id.get()));
            ViewUtil.redirect(returnUrl.get(),request,response);
        } else {
            ViewUtil.redirect("/app/movies",request,response);
        }
    }

}

