package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.Command;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.User;
import epam.my.project.service.factory.ServiceFactory;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ShowUserCommandImpl implements Command, Serializable {
    private static final long serialVersionUID = 1269437451928000893L;
    private static final int SUBSTRING_INDEX = "/user".length();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory) throws IOException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        User user = serviceFactory.getUserService().getUserByUId(uid);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByUser(user.getId(), new Page(Constants.MAX_COMMENTS_PER_HTML_PAGE));
        user.setComments(comments);
        request.setAttribute(Constants.USER, user);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByUser(user.getId());
        request.setAttribute(Constants.PAGE_COUNT, totalCount);
        ViewUtil.forwardToPage("user.jsp", request, response);
    }
}
