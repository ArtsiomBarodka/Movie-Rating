package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.impl.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.User;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class MoreCommentsShowUserCommand extends FrontCommand {
    private static final long serialVersionUID = 7828565617944354848L;
    private static final int SUBSTRING_INDEX = "/user".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(Constants.PAGE));
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        User user = serviceFactory.getUserService().getUserByUId(uid);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByUser(user.getId(), new Page(page ,Constants.MAX_COMMENTS_PER_HTML_PAGE));
        user.setComments(comments);
        request.setAttribute(Constants.USER, user);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByUser(user.getId());
        request.setAttribute(Constants.PAGE_COUNT, totalCount);
        forwardToFragment("comments-list.jsp");
    }
}