package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.User;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class MoreCommentsShowUserCommand extends FrontCommand {
    private static final long serialVersionUID = -6486030870356929704L;
    private static final int SUBSTRING_INDEX = "/app/ajax/html/user/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(Constants.PAGE));
        int pageable = getPageable();
        request.setAttribute(Constants.PAGEABLE, pageable);
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        User user = serviceFactory.getUserService().getUserByUId(uid);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByUser(user.getId(), new Page(page ,pageable));
        user.setComments(comments);
        request.setAttribute(Constants.USER, user);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByUser(user.getId());
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, pageable));
        viewFactory.getForwardToFragment().init(request,response).render("comments-list.jsp");
    }
}