package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.domain.Page;
import com.epam.mrating.model.entity.Comment;
import com.epam.mrating.model.entity.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type Show user command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ShowUserCommand extends AbstractCommand {
    private static final long serialVersionUID = 1269437451928000893L;
    private static final int SUBSTRING_INDEX = "/app/user/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        User user = serviceFactory.getUserService().getUserByUId(uid);
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByUser(user.getId(), new Page(pageable));
        user.setComments(comments);
        request.setAttribute(RequestAttributeNames.USER, user);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByUser(user.getId());
        request.setAttribute(RequestAttributeNames.TOTAL_COMMENTS_COUNT, totalCount);
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToPage("page/user.jsp",request,response);
    }
}
