package epam.my.project.controller.command.impl;

import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.User;
import epam.my.project.util.ViewUtil;
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
