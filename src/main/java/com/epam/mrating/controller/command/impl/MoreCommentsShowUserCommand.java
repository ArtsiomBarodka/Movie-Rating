package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.domain.Page;
import com.epam.mrating.model.entity.Comment;
import com.epam.mrating.model.entity.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type More comments show user command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class MoreCommentsShowUserCommand extends AbstractCommand {
    private static final long serialVersionUID = -6486030870356929704L;
    private static final int SUBSTRING_INDEX = "/app/ajax/html/user/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(RequestParameterNames.PAGE));
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        User user = serviceFactory.getUserService().getUserByUId(uid);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByUser(user.getId(), new Page(page ,pageable));
        user.setComments(comments);
        request.setAttribute(RequestAttributeNames.USER, user);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByUser(user.getId());
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        ViewUtil.forwardToFragment("comments-list.jsp",request,response);
    }
}