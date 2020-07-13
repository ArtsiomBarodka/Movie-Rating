package epam.my.project.controller.command.impl.get;

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

public class ShowUserCommand extends FrontCommand {
    private static final long serialVersionUID = 1269437451928000893L;
    private static final int SUBSTRING_INDEX = "/app/user/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        User user = serviceFactory.getUserService().getUserByUId(uid);
        int pageable = getPageable();
        request.setAttribute(Constants.PAGEABLE, pageable);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByUser(user.getId(), new Page(pageable));
        user.setComments(comments);
        request.setAttribute(Constants.USER, user);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByUser(user.getId());
        request.setAttribute(Constants.TOTAL_COMMENTS_COUNT, totalCount);
        request.setAttribute(Constants.PAGE_COUNT, getPageCount(totalCount, pageable));
        viewFactory.getForwardToPage().init(request,response).render("page/user.jsp");
    }
}
